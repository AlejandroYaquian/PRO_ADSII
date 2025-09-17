package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.DTO.LoginRequest;
import com.adsii.pro_adsii.DTO.LoginResponse;
import com.adsii.pro_adsii.DTO.RecoveryStartResponse;
import com.adsii.pro_adsii.DTO.SimpleResponse;
import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Service.UsuarioCrudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioCrudController {

    @Autowired
    private UsuarioCrudService service;

    // LISTAR 
    @GetMapping("/listar")
    public List<Usuario> listar() {
        return service.listar();
    }

    // OBTENER 
    @GetMapping("/obtener/{id}")
    public Usuario obtener(@PathVariable String id) {
        return service.obtener(id);
    }

    // GUARDAR (crea/edita) 
    @PostMapping("/guardar")
    public ResponseEntity<Usuario> guardar(
            @RequestBody Usuario input,
            @RequestHeader(value = "X-User", required = false) String xUser) {
        Usuario saved = service.guardar(input, xUser);
        return ResponseEntity.ok(saved);
    }

    // ================== ELIMINAR ==================
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ================== LOGIN (MD5) ==================
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Usuario user = service.obtener(request.getIdUsuario());
        if (user == null) {
            return ResponseEntity.badRequest()
                    .body(new LoginResponse(false, "Usuario no encontrado", null));
        }

        // MD5 (32 hex)
        String hashEntrada = md5(request.getPassword());

        if (!hashEntrada.equalsIgnoreCase(user.getPassword())) {
            // ---------- Fallback OPCIONAL por si tienes usuarios viejos con SHA-256 (64 hex) ----------
            // Si no lo quieres, elimina este bloque y deja solo el "Contraseña incorrecta".
            if (user.getPassword() != null && user.getPassword().length() == 64) {
                String sha = sha256(request.getPassword());
                if (sha.equalsIgnoreCase(user.getPassword())) {
                    // Migración automática a MD5
                    service.actualizarPasswordRecuperacion(user.getIdUsuario(), request.getPassword(),
                            "autoconvert_sha256_to_md5");
                    return ResponseEntity.ok(
                            new LoginResponse(true, "Login exitoso", user.getIdUsuario())
                    );
                }
            }
            // -------------------------------------------------------------------------------------------
            return ResponseEntity.badRequest()
                    .body(new LoginResponse(false, "Contraseña incorrecta", user.getIdUsuario()));
        }

        return ResponseEntity.ok(
                new LoginResponse(true, "Login exitoso", user.getIdUsuario())
        );
    }

    // ================== RECUPERAR: INICIO (pregunta) ==================
    @PostMapping("/recuperar/inicio")
    public ResponseEntity<RecoveryStartResponse> recuperarInicio(@RequestBody Map<String, Object> req) {
        String id = null;
        if (req != null) {
            Object v = req.get("idUsuario");
            if (v == null) v = req.get("usuario");     // lo que manda tu modal
            if (v == null) v = req.get("usuarioId");   // si viniera como número
            if (v != null) id = v.toString();
        }

        if (nz(id) == null) {
            return ResponseEntity.badRequest().body(new RecoveryStartResponse(false, null));
        }

        Usuario u = service.obtener(id);
        if (u == null || nz(u.getPregunta()) == null) {
            return ResponseEntity.ok(new RecoveryStartResponse(false, null));
        }

        return ResponseEntity.ok(new RecoveryStartResponse(true, u.getPregunta()));
    }

    // ================== RECUPERAR: VALIDAR y CAMBIAR ==================
    @PostMapping("/recuperar/validar")
    public ResponseEntity<SimpleResponse> recuperarValidar(@RequestBody Map<String, Object> req) {
        // 1) Tomar id
        String id = null;
        if (req != null) {
            Object v = req.get("idUsuario");
            if (v == null) v = req.get("usuario");      // por si el front manda "usuario"
            if (v == null) v = req.get("usuarioId");    // por si llega numérico
            if (v != null) id = v.toString();
        }

        // 2) Tomar respuesta y nueva password (aceptamos alias)
        String respIn = null;
        String nueva  = null;
        if (req != null) {
            Object r = req.get("respuesta");
            if (r != null) respIn = r.toString();

            Object n = req.get("nuevaPassword");
            if (n == null) n = req.get("nuevaClave");
            if (n == null) n = req.get("passwordNueva");
            if (n != null) nueva = n.toString();
        }

        if (id == null || respIn == null || nueva == null) {
            return ResponseEntity.badRequest().body(new SimpleResponse(false, "Datos incompletos"));
        }

        Usuario u = service.obtener(id);
        if (u == null || u.getPregunta() == null || u.getPregunta().isBlank()
                     || u.getRespuesta() == null || u.getRespuesta().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new SimpleResponse(false, "Tu pregunta y/o respuesta es invalida, revisa tus datos"));
        }

        // Comparación case-insensitive + trim
        String rBD = u.getRespuesta().trim().toLowerCase();
        String rIn = respIn.trim().toLowerCase();
        if (!rBD.equals(rIn)) {
            return ResponseEntity.badRequest()
                    .body(new SimpleResponse(false, "Tu pregunta y/o respuesta es invalida, revisa tus datos"));
        }

        // OK → cambiar password (el service guarda en MD5)
        service.actualizarPasswordRecuperacion(u.getIdUsuario(), nueva, "recuperacion");
        return ResponseEntity.ok(new SimpleResponse(true, "Contraseña actualizada"));
    }

    // ================== helpers locales ==================
    private static String nz(String s) { return (s == null || s.isBlank()) ? null : s.trim(); }

    private static String md5(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] dig = md.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(32);
            for (byte b : dig) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generando hash MD5", e);
        }
    }

    // Solo para el fallback opcional (puedes borrarlo si no lo usas)
    private static String sha256(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] dig = md.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(dig.length * 2);
            for (byte b : dig) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generando hash SHA-256", e);
        }
    }
}
