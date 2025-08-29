package com.adsii.pro_adsii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsii.pro_adsii.entity.Opcion;

@Repository("opcionRepository")

public interface OpcionRepository extends JpaRepository<Opcion, Integer> {

}
