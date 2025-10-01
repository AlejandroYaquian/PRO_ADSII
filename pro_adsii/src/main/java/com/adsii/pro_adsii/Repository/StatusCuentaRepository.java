package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.StatusCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusCuentaRepository extends JpaRepository<StatusCuenta, Integer> {
}