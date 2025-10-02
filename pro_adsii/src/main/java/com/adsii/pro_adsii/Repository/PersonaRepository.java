package com.adsii.pro_adsii.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adsii.pro_adsii.Entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}