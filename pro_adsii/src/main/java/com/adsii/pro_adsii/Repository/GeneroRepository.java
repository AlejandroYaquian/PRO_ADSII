package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
   
}
