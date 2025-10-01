package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.SaldoCuentaHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaldoCuentaHistRepository extends JpaRepository<SaldoCuentaHist, SaldoCuentaHist.PK> {

    @Query("from SaldoCuentaHist h " +
           "where (:anio is null or h.anio = :anio) " +
           "and (:mes is null or h.mes = :mes) " +
           "and (:idPersona is null or h.idPersona = :idPersona)")
    List<SaldoCuentaHist> buscar(@Param("anio") Integer anio,
                                 @Param("mes") Integer mes,
                                 @Param("idPersona") Integer idPersona);
}
