package com.example.demo.models.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dao.ICompeticionDao;
import com.example.demo.models.dao.IInstitutoDao;
import com.example.demo.models.entity.Competicion;
import com.example.demo.models.entity.Instituto;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/")
public class CompeticionRestController {

	@Autowired
	private ICompeticionDao competicionDao;
	
	
	@GetMapping("/competiciones")
	public List<Competicion> index(){
		return competicionDao.findAll();
	}
	
	
	
	
}
