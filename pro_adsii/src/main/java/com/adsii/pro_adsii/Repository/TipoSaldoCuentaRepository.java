package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.TipoSaldoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tipoSaldoCuentaRepository")
public interface TipoSaldoCuentaRepository extends JpaRepository<TipoSaldoCuenta, Long> {
}
