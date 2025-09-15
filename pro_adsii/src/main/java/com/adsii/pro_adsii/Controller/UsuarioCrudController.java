package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.DTO.LoginRequest;
import com.adsii.pro_adsii.DTO.LoginResponse;
import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Service.UsuarioCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.adsii.pro_adsii.DTO.RecoveryStartRequest;
import com.adsii.pro_adsii.DTO.RecoveryStartResponse;
import com.adsii.pro_adsii.DTO.RecoveryFinishRequest;
import com.adsii.pro_adsii.DTO.SimpleResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioCrudController {

    @Autowired
    private UsuarioCrudService service;  // inyección simple para evitar error de constructor

    // LISTAR
    @GetMapping("/listar")
    public List<Usuario> listar() {
        return service.listar();
    }

    // OBTENER POR ID (String/UUID)
    @GetMapping("/obtener/{id}")
    public Usuario obtener(@PathVariable String id) {
        return service.obtener(id);
    }

    // GUARDAR (crea/edita). X-User puede venir vacío
    @PostMapping("/guardar")
    public ResponseEntity<Usuario> guardar(
            @RequestBody Usuario input,
            @RequestHeader(value = "X-User", required = false) String xUser) {
        Usuario saved = service.guardar(input, xUser);
        return ResponseEntity.ok(saved);
    }

    // ELIMINAR POR ID (String/UUID)
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
      // LOGIN 🔒
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Usuario user = service.obtener(request.getIdUsuario());
        if (user == null) {
            return ResponseEntity.badRequest()
                    .body(new LoginResponse(false, "Usuario no encontrado", null));
        }

        // Hasheamos el password que viene del request en texto plano
        String hashEntrada = sha256(request.getPassword());

        // Comparamos contra lo guardado en BD
        if (!hashEntrada.equalsIgnoreCase(user.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(new LoginResponse(false, "Contraseña incorrecta", user.getIdUsuario()));
        }

        return ResponseEntity.ok(
                new LoginResponse(true, "Login exitoso", user.getIdUsuario())
        );
    }

    // Helper privado para hash SHA-256 (puedes moverlo a tu service si prefieres)
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


    
    // ====== RECUPERAR: INICIO (regresa la pregunta de seguridad) ======
@PostMapping("/recuperar/inicio")
public ResponseEntity<RecoveryStartResponse> recuperarInicio(@RequestBody RecoveryStartRequest req) {
    String id = nz(req!=null ? req.getIdUsuario() : null);
    if (id == null) {
        return ResponseEntity.badRequest().body(new RecoveryStartResponse(false, null));
    }
    Usuario u = service.obtener(id);
    if (u == null || nz(u.getPregunta()) == null) {
        // No exponemos detalle por seguridad
        return ResponseEntity.ok(new RecoveryStartResponse(false, null));
    }
    return ResponseEntity.ok(new RecoveryStartResponse(true, u.getPregunta()));
}

// ====== RECUPERAR: VALIDAR RESPUESTA Y CAMBIAR PASSWORD ======
@PostMapping("/recuperar/validar")
public ResponseEntity<SimpleResponse> recuperarValidar(@RequestBody RecoveryFinishRequest req) {
    String id = nz(req!=null ? req.getIdUsuario() : null);
    String respIn = nz(req!=null ? req.getRespuesta() : null);
    String nueva = nz(req!=null ? req.getNuevaPassword() : null);

    if (id == null || respIn == null || nueva == null) {
        return ResponseEntity.badRequest().body(new SimpleResponse(false, "Datos incompletos"));
    }

    Usuario u = service.obtener(id);
    if (u == null || nz(u.getPregunta()) == null || nz(u.getRespuesta()) == null) {
        return ResponseEntity.badRequest().body(new SimpleResponse(false,
                "Tu pregunta y/o respuesta es invalida, revisa tus datos"));
    }

    // Comparación case-insensitive, con trim
    String rBD = nz(u.getRespuesta()).toLowerCase();
    String rIn = respIn.toLowerCase();
    if (!rBD.equals(rIn)) {
        return ResponseEntity.badRequest().body(new SimpleResponse(false,
                "Tu pregunta y/o respuesta es invalida, revisa tus datos"));
    }

    // Respuesta correcta → cambiar password (SHA-256)
    service.actualizarPasswordRecuperacion(u.getIdUsuario(), nueva, "recuperacion");
    return ResponseEntity.ok(new SimpleResponse(true, "Contraseña actualizada"));
}

// ===== helpers locales =====
private static String nz(String s){ return (s==null || s.isBlank()) ? null : s.trim(); }
}
