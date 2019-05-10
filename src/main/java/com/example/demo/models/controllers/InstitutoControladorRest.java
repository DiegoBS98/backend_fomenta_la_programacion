package com.example.demo.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dao.IInstitutoDao;
import com.example.demo.models.entity.Competicion;
import com.example.demo.models.entity.Instituto;
import com.example.demo.models.services.IInstitutoService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/")
public class InstitutoControladorRest {

	
	@Autowired
	private IInstitutoService institutoService;
	
	@GetMapping("/institutos")
	public List<Instituto> index(){
		return institutoService.findAll();
	}
	
	@GetMapping("/institutos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Instituto show(@PathVariable Long id) {
		return institutoService.findById(id);
	}
	
	@PostMapping("/institutos")
	@ResponseStatus(HttpStatus.CREATED)
	public Instituto create(@RequestBody Instituto instituto) {
		return institutoService.save(instituto);
	}
	
	public Instituto update(@RequestBody Instituto instituto, @PathVariable Long id) {
		Instituto actual = new Instituto();
		actual.setNombre(instituto.getNombre());
		actual.setLocalizacion(instituto.getLocalizacion());
		actual.setTelefono_contacto(instituto.getTelefono_contacto());
		actual.setEmail_contacto(instituto.getEmail_contacto());
		actual.setCif_instituto(instituto.getCif_instituto());
		
		return institutoService.save(actual);
	}
}
