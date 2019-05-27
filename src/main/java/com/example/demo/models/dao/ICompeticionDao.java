package com.example.demo.models.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.entity.Competicion;



public interface ICompeticionDao extends JpaRepository<Competicion, Long >{
	@Modifying
	@Transactional
	@Query( value="insert into usuariosregistrados(id_competicion, id_usuario) values(:idCompeticion, :idUsuario)" , nativeQuery=true)
	public void insertarRegistro(long idCompeticion, long idUsuario);
}
