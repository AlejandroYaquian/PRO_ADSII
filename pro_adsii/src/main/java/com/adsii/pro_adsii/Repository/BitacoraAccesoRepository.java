package com.adsii.pro_adsii.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsii.pro_adsii.Entity.BitacoraAcceso;

@Repository
public interface BitacoraAccesoRepository extends JpaRepository<BitacoraAcceso, Long> {
}