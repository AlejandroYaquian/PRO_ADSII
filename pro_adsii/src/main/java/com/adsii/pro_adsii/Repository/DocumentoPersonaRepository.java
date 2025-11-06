package com.adsii.pro_adsii.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adsii.pro_adsii.Entity.DocumentoPersona;
import com.adsii.pro_adsii.Entity.DocumentoPersonaId;


public interface DocumentoPersonaRepository extends JpaRepository<DocumentoPersona, DocumentoPersonaId> {
    List<DocumentoPersona> findByPersona_IdPersona(int idPersona);
}