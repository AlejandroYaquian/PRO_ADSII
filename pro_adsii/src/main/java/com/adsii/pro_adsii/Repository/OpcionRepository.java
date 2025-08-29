package com.adsii.pro_adsii.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsii.pro_adsii.Entity.Opcion;

@Repository

public interface OpcionRepository extends JpaRepository<Opcion, Integer> {

}
