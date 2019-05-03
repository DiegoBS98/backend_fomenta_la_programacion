package com.example.demo.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.entity.Competicion;



public interface ICompeticionDao extends JpaRepository<Competicion, Long >{

	
}
