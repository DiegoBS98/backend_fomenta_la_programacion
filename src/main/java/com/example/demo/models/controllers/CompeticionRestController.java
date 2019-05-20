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
import org.springframework.security.access.annotation.Secured;
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

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/")
public class CompeticionRestController {

	@Autowired
	private ICompeticionService competicionService;
	
	@GetMapping("/competiciones")
	public List<Competicion> index() {
		return competicionService.findAll();
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/competiciones/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Competicion competicion = null;
		Map<String, Object> response = new HashMap<>();
		try {
			competicion = competicionService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (competicion == null) {
			response.put("mensaje",
					"La competicion ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Competicion>(competicion, HttpStatus.OK);
	}

	/*@PostMapping("/competiciones")
	@ResponseStatus(HttpStatus.CREATED)
	public Competicion create(@RequestBody Competicion competicion) {
		return competicionService.save(competicion);
	}*/
	
	@PostMapping("/competiciones")
	//La etiqueta requesbody indica que como los datos vendran
	//en un json, lo mapee a objeto Competicion
	//la etiqueta valid la utilizamos para que se validen los campos antes de ejecutar el metodo
	//El parametro bindingresult es que objeto que contiene todos los mensajes de errores
	
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> create(@Valid @RequestBody Competicion competicion, BindingResult result) {
		//Utilizamos un hasmap para guardar los mensajes de error
		Competicion nuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		//Ejemplo de programacion funcional
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors()
					.stream().
					map(
					err -> "El campo '"+ err.getField() +"' "+err.getDefaultMessage()
					).collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		try {
			nuevo = competicionService.save(competicion);
		}catch( DataAccessException e) {
			
			response.put("mensaje", "error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El evento ha sido creado con exito!");
		response.put("evento", nuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/*@PostMapping("/competiciones/{id}")
	public Competicion update(@RequestBody Competicion competicion, @PathVariable Long id) {
		Competicion actual = new Competicion();
		actual.setNombreCompeticion(competicion.getNombreCompeticion());
		actual.setLugarEvento(competicion.getLugarEvento());
		actual.setDescripcion(competicion.getDescripcion());
		actual.setPlazas(competicion.getPlazas());
		actual.setDificultad(competicion.getDificultad());

		return competicionService.save(actual);
	}*/
	/*
	@DeleteMapping("/competiciones/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		competicionService.delete(id);
	}*/
	@Secured("ROLE_ADMIN")
	@PutMapping("/competiciones/{id}")
	public  ResponseEntity<?> update(@Valid @RequestBody Competicion competicion,BindingResult result, @PathVariable Long id) {
		Competicion competicionActual = competicionService.findById(id);
		Competicion competicionActualizada = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors()
					.stream().
					map(
					err -> "El campo '"+err.getDefaultMessage()
					).collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(competicionActual == null)
		{
			response.put("mensaje", "Error, no se pudo editar: El  evento ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			competicionActual.setNombreCompeticion(competicion.getNombreCompeticion());
			competicionActual.setDescripcion(competicion.getDescripcion());
			competicionActual.setLugarEvento(competicion.getLugarEvento());
			competicionActual.setPlazas(competicion.getPlazas());
			competicionActual.setDificultad(competicion.getDificultad());
			
			competicionActualizada = competicionService.save(competicionActual);
				
		}catch( DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El evento ha sido actualizado con exito!");
		response.put("evento", competicionActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/competiciones/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try{
			competicionService.delete(id);
		}catch(DataAccessException e ) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El evento ha sido eliminado con exito!");
		/**
		 * Spring data va revisar si existe en la base de datos con el crud repository
		 * por eso no lo comprobamos nosotros
		 */
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}

}
