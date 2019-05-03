package com.example.demo.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.models.entity.Instituto;


public interface IInstitutoService {

	public List<Instituto> findAll();
	public Page<Instituto> findAll(Pageable paginado);

	public Instituto save(Instituto instituto);
	
	public void delete(Long id);
	
	public Instituto findById(Long id);
}
