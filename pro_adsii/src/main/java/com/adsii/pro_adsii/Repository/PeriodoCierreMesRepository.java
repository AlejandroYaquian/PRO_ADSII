package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.PeriodoCierreMes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PeriodoCierreMesRepository extends JpaRepository<PeriodoCierreMes, PeriodoCierreMes.PK> {

    @Query("from PeriodoCierreMes p where p.anio = :anio and p.mes = :mes and p.fechaCierre is null")
    Optional<PeriodoCierreMes> findPeriodoAbierto(@Param("anio") int anio, @Param("mes") int mes);

    @Query("from PeriodoCierreMes p where p.fechaCierre is null order by p.anio desc, p.mes desc")
    List<PeriodoCierreMes> findAbiertos();
}
