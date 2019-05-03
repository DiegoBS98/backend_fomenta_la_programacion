package com.example.demo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dao.IInstitutoDao;
import com.example.demo.models.entity.Instituto;
@Service
public class InstitutoServiceImpl implements IInstitutoService {

	@Autowired
	private IInstitutoDao institutoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Instituto> findAll() {
		// TODO Auto-generated method stub
		return institutoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Instituto> findAll(Pageable paginado) {
		// TODO Auto-generated method stub
		return institutoDao.findAll(paginado);
	}

	@Override
	@Transactional
	public Instituto save(Instituto instituto) {
		// TODO Auto-generated method stub
		return institutoDao.save(instituto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		institutoDao.deleteById(id);
	}

	@Override
	public Instituto findById(Long id) {
		// TODO Auto-generated method stub
		return institutoDao.findById(id).orElse(null);
	}

}
