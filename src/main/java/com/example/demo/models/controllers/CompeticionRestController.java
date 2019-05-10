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
import com.example.demo.models.services.ICompeticionService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/")
public class CompeticionRestController {

	@Autowired
	private ICompeticionService competicionService;
	
	
	@GetMapping("/competiciones")
	public List<Competicion> index(){
		return competicionService.findAll();
	}
	
	@GetMapping("/competiciones/{id}")
public ResponseEntity<?> show(@PathVariable Long id) {
		
		Competicion competicion = null;
		Map<String, Object> response = new HashMap<>();
		try {
			competicion = competicionService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(competicion == null) {
			response.put("mensaje", "La competicion ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Competicion>(competicion, HttpStatus.OK);
	}
	@PostMapping("/competiciones")
	@ResponseStatus(HttpStatus.CREATED)
	public Competicion create(@RequestBody Competicion competicion) {
		return competicionService.save(competicion);
	}
	
	@PostMapping("/competiciones/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Competicion update(@RequestBody Competicion competicion, @PathVariable Long id){
		Competicion actual = new Competicion();
		actual.setNombreCompeticion(competicion.getNombreCompeticion());
		actual.setLugarEvento(competicion.getLugarEvento());
		actual.setDescripcion(competicion.getDescripcion());
		actual.setPlazas(competicion.getPlazas());
		actual.setDificultad(competicion.getDificultad());
		
		return competicionService.save(actual);
	}
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		competicionService.delete(id);
	}
		
	}
	
	
	
	

