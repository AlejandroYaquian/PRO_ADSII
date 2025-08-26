package com.adsii.pro_adsii.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.BitacoraAcceso;
import com.adsii.pro_adsii.Repository.BitacoraAccesoRepository;

import jakarta.servlet.http.HttpServletRequest;
import ua_parser.Client;
import ua_parser.Parser;

@Service
public class BitacoraAccesoService {
    private final BitacoraAccesoRepository bitacoraRepository;
    private final Parser uaParser;

    public BitacoraAccesoService(BitacoraAccesoRepository bitacoraRepository) {
        this.bitacoraRepository = bitacoraRepository;
        this.uaParser = new Parser();
    }

    public void registrarAcceso(String idUsuario, int tipoAcceso, HttpServletRequest request, String accion) {
        BitacoraAcceso bitacora = new BitacoraAcceso();
        bitacora.setIdUsuario(idUsuario);
        bitacora.setIdTipoAcceso(tipoAcceso);
        bitacora.setFechaAcceso(LocalDateTime.now());
        bitacora.setAccion(accion);

        if (request != null) {
            // Obtener IP
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            bitacora.setDireccionIp(ip);

            // User-Agent
            String userAgent = request.getHeader("User-Agent");
            bitacora.setHttpUserAgent(userAgent);

            if (userAgent != null) {
                Client client = uaParser.parse(userAgent);

                // Sistema operativo
                String os = client.os.family;
                if (client.os.major != null) {
                    os += " " + client.os.major;
                }
                bitacora.setSistemaOperativo(os);

                // Navegador
                String browser = client.userAgent.family;
                if (client.userAgent.major != null) {
                    browser += " " + client.userAgent.major;
                }
                bitacora.setBrowser(browser);

                // Dispositivo
                String device = client.device.family;
                if (device == null || device.isEmpty() || device.equalsIgnoreCase("Other")) {
                    bitacora.setDispositivo("PC/Escritorio");
                } else {
                    bitacora.setDispositivo(device);
                }
            }
        }

        bitacoraRepository.save(bitacora);
    }
}
