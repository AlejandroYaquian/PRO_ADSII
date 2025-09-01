package com.adsii.pro_adsii.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

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

    public RoleOpcion actualizarRoleOpcion(int id, OpcionDTO opcionDTOActualizado) {
        Optional<RoleOpcion> roleOpcion = Optional.ofNullable(roleOpcionRepository.findByIdIdRoleAndIdIdOpcion(opcionDTOActualizado.getIdRole(), opcionDTOActualizado.getIdOpcion()));
		if (roleOpcion.isPresent()) {
			RoleOpcion rop = roleOpcion.get();
			rop.setAlta(opcionDTOActualizado.getAlta() ? true :false);
            rop.setImprimir(opcionDTOActualizado.getImprimir() ? true :false);
            rop.setExportar(opcionDTOActualizado.getExportar() ? true :false);
			return roleOpcionRepository.save(rop);			
		}else {
			return null;
		}
    }

}
