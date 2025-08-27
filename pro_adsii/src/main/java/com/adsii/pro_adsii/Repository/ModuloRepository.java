package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {
   
}
