package com.example.demo.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.models.entity.Competicion;

public interface ICompeticionService {

	public List<Competicion> findAll();

	public Page<Competicion> findAll(Pageable paginado);

	public Competicion save(Competicion competicion);

	public void delete(Long id);

	public Competicion findById(Long id);
	
	public void insertarRegistro(long idCompeticion, int idUsuario);
}
