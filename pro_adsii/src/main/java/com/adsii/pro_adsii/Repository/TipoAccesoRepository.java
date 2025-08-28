package com.adsii.pro_adsii.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsii.pro_adsii.Entity.TipoAcceso;

@Repository
public interface TipoAccesoRepository extends JpaRepository<TipoAcceso, Integer> {
}