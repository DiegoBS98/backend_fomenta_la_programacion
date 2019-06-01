package com.example.demo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dao.ICompeticionDao;
import com.example.demo.models.entity.Competicion;

@Service
public class CompeticionServiceImpl implements ICompeticionService {
	/**
	 * Inyectamos dependencias del cliente sobre el que se van a realizar las
	 * acciones
	 */
	@Autowired
	private ICompeticionDao competicionDao;

	@Override
	@Transactional(readOnly = true)
	public List<Competicion> findAll() {
		// TODO Auto-generated method stub
		return (List<Competicion>) competicionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Competicion> findAll(Pageable paginado) {
		return competicionDao.findAll(paginado);
	}

	@Override
	@Transactional
	public Competicion save(Competicion competicion) {
		return competicionDao.save(competicion);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		competicionDao.deleteById(id);

	}

	@Override
	public Competicion findById(Long id) {
		// TODO Auto-generated method stub
		return competicionDao.findById(id).orElse(null);
	}

	@Override
	public void insertarRegistro(long idCompeticion, int idUsuario) {
	
		
		competicionDao.insertarRegistro(idCompeticion, idUsuario);
		
	}

	
}
