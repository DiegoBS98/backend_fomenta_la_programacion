package com.example.demo.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dao.IInstitutoDao;
import com.example.demo.models.entity.Instituto;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/")
public class InstitutoControladorRest {

	
	@Autowired
	private IInstitutoDao institutoDao;
	
	@GetMapping("/institutos")
	public List<Instituto> index(){
		return institutoDao.findAll();
	}
}
