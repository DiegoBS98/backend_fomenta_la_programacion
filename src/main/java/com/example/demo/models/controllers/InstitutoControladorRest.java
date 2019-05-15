package com.example.demo.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public ResponseEntity<?> show(@PathVariable Long id) {
		Instituto instituto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			instituto = institutoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (instituto == null) {
			response.put("mensaje",
					"El instituto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Instituto>(instituto, HttpStatus.OK);
	}
	
	@PostMapping("/institutos")
	//La etiqueta requesbody indica que como los datos vendran
	//en un json, lo mapee a objeto Competicion
	public ResponseEntity<?> create(@Valid @RequestBody Instituto instituto, BindingResult result) {
		//Utilizamos un hasmap para guardar los mensajes de error
		Instituto nuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		
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
			nuevo = institutoService.save(instituto);
		}catch( DataAccessException e) {
			
			response.put("mensaje", "error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El instituto ha sido añadido con exito!");
		response.put("instituto", nuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/*@PutMapping("/institutos")
	public Instituto update(@RequestBody Instituto instituto, @PathVariable Long id) {
		Instituto actual = new Instituto();
		actual.setNombre(instituto.getNombre());
		actual.setLocalizacion(instituto.getLocalizacion());
		actual.setTelefono_contacto(instituto.getTelefono_contacto());
		actual.setEmail_contacto(instituto.getEmail_contacto());
		actual.setCif_instituto(instituto.getCif_instituto());
		
		return institutoService.save(actual);
	}*/
	
	
	@PutMapping("/institutos/{id}")
	public  ResponseEntity<?> update(@Valid @RequestBody Instituto instituto,BindingResult result, @PathVariable Long id) {
		Instituto institutoActual = institutoService.findById(id);
		Instituto institutoActualizado = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors()
					.stream().
					map(
					err -> "El campo '"+ err.getField() +"' "+err.getDefaultMessage()
					).collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(institutoActual == null)
		{
			response.put("mensaje", "Error, no se pudo editar: El  instituto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			institutoActual.setNombre(instituto.getNombre());
			institutoActual.setLocalizacion(instituto.getLocalizacion());
			institutoActual.setCif_instituto(instituto.getCif_instituto());
			institutoActual.setTelefono_contacto(instituto.getTelefono_contacto());
			institutoActual.setEmail_contacto(instituto.getEmail_contacto());
			
			institutoActualizado = institutoService.save(institutoActual);
				
		}catch( DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El evento ha sido actualizado con exito!");
		response.put("instituto", institutoActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/institutos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try{
			institutoService.delete(id);
		}catch(DataAccessException e ) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El instituto ha sido eliminado con exito!");
		/**
		 * Spring data va revisar si existe en la base de datos con el crud repository
		 * por eso no lo comprobamos nosotros
		 */
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
}
