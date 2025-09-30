package com.adsii.pro_adsii.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.adsii.pro_adsii.Entity.TipoMovimientoCXC;

@Repository("tipoMovimientoCXCRepository")
public interface TipoMovimientoCXCRepository extends JpaRepository <TipoMovimientoCXC,Integer>{

}
