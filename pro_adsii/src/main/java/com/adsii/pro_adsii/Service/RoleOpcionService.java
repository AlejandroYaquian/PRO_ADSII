package com.adsii.pro_adsii.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.DTO.OpcionDTO;
import com.adsii.pro_adsii.Entity.Opcion;
import com.adsii.pro_adsii.Entity.RoleOpcion;
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
            opcionDTO.setNombre(op.getNombre());
            opcionDTO.setAlta(roleOpcion.getAlta());
            opcionDTO.setBaja(roleOpcion.getBaja());
            opcionDTO.setImprimir(roleOpcion.getImprimir());
            opcionDTO.setExportar(roleOpcion.getExportar());
            ops.add(opcionDTO);
        } 
        return ops;
    }


}
