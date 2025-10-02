package com.adsii.pro_adsii.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adsii.pro_adsii.Entity.DocumentoPersona;
import com.adsii.pro_adsii.Entity.DocumentoPersonaId;


public interface DocumentoPersonaRepository extends JpaRepository<DocumentoPersona, DocumentoPersonaId> {
}