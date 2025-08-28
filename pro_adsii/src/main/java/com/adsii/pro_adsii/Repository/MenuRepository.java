package com.adsii.pro_adsii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsii.pro_adsii.entity.Menu;


@Repository("menuRepository")

public interface MenuRepository extends JpaRepository<Menu, Integer>{

}
