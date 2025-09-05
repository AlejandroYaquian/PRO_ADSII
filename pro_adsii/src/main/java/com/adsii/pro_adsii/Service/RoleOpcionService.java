package com.adsii.pro_adsii.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.adsii.pro_adsii.DTO.OpcionDTO;
import com.adsii.pro_adsii.Entity.Opcion;
import com.adsii.pro_adsii.Entity.RoleOpcion;
import com.adsii.pro_adsii.Entity.RoleOpcionId;
import com.adsii.pro_adsii.Repository.RoleOpcionRepository;


@Service
public class RoleOpcionService {

    @Autowired
    RoleOpcionRepository roleOpcionRepository;

    @Autowired
    OpcionService opcion;

    @Autowired
    RoleService roleR;



    public List<OpcionDTO> obtenerOpcionesPorRol(int idRole) {

        List<RoleOpcion> roleOpciones = roleOpcionRepository.findById_IdRole(idRole);
        List<OpcionDTO> ops = new ArrayList<>();

        Opcion op;        
        OpcionDTO opcionDTO;

        for (RoleOpcion roleOpcion : roleOpciones) {
            op = opcion.buscarOpcion(roleOpcion.getId().getIdOpcion());
            opcionDTO = new OpcionDTO();
            opcionDTO.setIdRole(roleOpcion.getId().getIdRole());
            opcionDTO.setIdOpcion(roleOpcion.getId().getIdOpcion());
            opcionDTO.setNombre(op.getNombre());
            opcionDTO.setAlta(roleOpcion.getAlta());
            opcionDTO.setBaja(roleOpcion.getBaja());
            opcionDTO.setImprimir(roleOpcion.getImprimir());
            opcionDTO.setExportar(roleOpcion.getExportar());
            ops.add(opcionDTO);
        } 
        return ops;
    }

    public RoleOpcion actualizarRoleOpcion(OpcionDTO opcionDTOActualizado) {
        Optional<RoleOpcion> roleOpcion = Optional.ofNullable(roleOpcionRepository.findByIdIdRoleAndIdIdOpcion(opcionDTOActualizado.getIdRole(), opcionDTOActualizado.getIdOpcion()));
		Date fecha = new Date(); // fecha actual
        if (roleOpcion.isPresent()) {
			RoleOpcion rop = roleOpcion.get();
			rop.setAlta(opcionDTOActualizado.getAlta() ? true : false);
            rop.setImprimir(opcionDTOActualizado.getImprimir() ? true : false);
            rop.setExportar(opcionDTOActualizado.getExportar() ? true : false);
            rop.setFechaModificacion(fecha);
            rop.setUsuarioModificacion("admin01");
			return roleOpcionRepository.save(rop);			
		}else {
			return null;
		}
    }


   public List<Opcion> obtenerOpcionesDiponibles(int idRole) {
        // Lista de opciones ya asignadas al rol
        List<OpcionDTO> ops = obtenerOpcionesPorRol(idRole);

        // Lista de todas las opciones disponibles en el sistema
        List<Opcion> opciones = opcion.listarTodos();

        // Lista para almacenar las opciones que NO están en ops
        List<Opcion> opcionesDisponibles = new ArrayList<>();

        // Convertir lista de OpcionDTO a conjunto de IDs para facilitar búsqueda
        Set<Integer> idsAsignados = ops.stream()
                                    .map(OpcionDTO::getIdOpcion)
                                    .collect(Collectors.toSet());

        // Recorrer todas las opciones y quedarnos solo con las que no están asignadas
        for (Opcion opcion : opciones) {
            if (!idsAsignados.contains(opcion.getIdOpcion())) {
                opcionesDisponibles.add(opcion);
            }
        }

        return opcionesDisponibles;
        
    }


    
	public void agregarRoleOpcion(Integer rolID) {

        Date fecha = new Date(); // fecha actual
        List<Opcion> opciones = opcion.listarTodos() ;

        for (Opcion opcion : opciones) {
            RoleOpcion roleOpcion = new RoleOpcion();
            RoleOpcionId roleOpcionId = new RoleOpcionId();
            roleOpcionId.setIdRole(rolID);
            roleOpcionId.setIdOpcion(opcion.getIdOpcion());

            roleOpcion.setId(roleOpcionId);
            roleOpcion.setAlta(false);
            roleOpcion.setBaja(true);
            roleOpcion.setCambio(false);
            roleOpcion.setImprimir(false);
            roleOpcion.setExportar(false);
            roleOpcion.setFechaCreacion(fecha);
            roleOpcion.setUsuarioCreacion("admin01");
            roleOpcion.setFechaModificacion(null);
            roleOpcion.setUsuarioModificacion(null);
            roleOpcionRepository.save(roleOpcion);
        }
	}


}
