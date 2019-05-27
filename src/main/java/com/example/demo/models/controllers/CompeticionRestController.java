package com.example.demo.models.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.dao.ICompeticionDao;
import com.example.demo.models.dao.IInstitutoDao;
import com.example.demo.models.entity.Competicion;
import com.example.demo.models.entity.Instituto;
import com.example.demo.models.services.ICompeticionService;
import com.example.demo.models.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/")
public class CompeticionRestController {

	@Autowired
	private ICompeticionService competicionService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/competiciones")
	public List<Competicion> index() {
		return competicionService.findAll();
	}
	
	@GetMapping("/competiciones/page/{page}")
	public Page<Competicion> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return competicionService.findAll(pageable);
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/competiciones/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Competicion competicion = competicionService.findById(id);
		
		if(!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" +  archivo.getOriginalFilename().replace(" ", "");
			
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			//log.info(rutaArchivo.toString());
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = competicion.getFoto();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			competicion.setFoto(nombreArchivo);
			
			competicionService.save(competicion);
			
			response.put("evento", competicion);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
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

	/*
	 * @PostMapping("/competiciones")
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public Competicion create(@RequestBody
	 * Competicion competicion) { return competicionService.save(competicion); }
	 */

	@PostMapping("/competiciones")
	// La etiqueta requesbody indica que como los datos vendran
	// en un json, lo mapee a objeto Competicion
	// la etiqueta valid la utilizamos para que se validen los campos antes de
	// ejecutar el metodo
	// El parametro bindingresult es que objeto que contiene todos los mensajes de
	// errores

	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> create(@Valid @RequestBody Competicion competicion, BindingResult result) {
		// Utilizamos un hasmap para guardar los mensajes de error
		Competicion nuevo = null;
		Map<String, Object> response = new HashMap<>();

		// Ejemplo de programacion funcional
		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			nuevo = competicionService.save(competicion);
		} catch (DataAccessException e) {

			response.put("mensaje", "error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El evento ha sido creado con exito!");
		response.put("evento", nuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@PostMapping("/competiciones/{idCompeticion}/{idUsuario}")
	public void registrar(@PathVariable Long idCompeticion, @PathVariable Long idUsuario) {
		competicionService.insertarRegistro(idCompeticion, idUsuario);
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	/*
	 * @PostMapping("/competiciones/{id}") public Competicion update(@RequestBody
	 * Competicion competicion, @PathVariable Long id) { Competicion actual = new
	 * Competicion();
	 * actual.setNombreCompeticion(competicion.getNombreCompeticion());
	 * actual.setLugarEvento(competicion.getLugarEvento());
	 * actual.setDescripcion(competicion.getDescripcion());
	 * actual.setPlazas(competicion.getPlazas());
	 * actual.setDificultad(competicion.getDificultad());
	 * 
	 * return competicionService.save(actual); }
	 */
	/*
	 * @DeleteMapping("/competiciones/{id}")
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long
	 * id) { competicionService.delete(id); }
	 */
	@Secured("ROLE_ADMIN")
	@PutMapping("/competiciones/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Competicion competicion, BindingResult result,
			@PathVariable Long id) {
		Competicion competicionActual = competicionService.findById(id);
		Competicion competicionActualizada = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream().map(err -> "El campo '" + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (competicionActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El  evento ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			competicionActual.setNombreCompeticion(competicion.getNombreCompeticion());
			competicionActual.setDescripcion(competicion.getDescripcion());
			competicionActual.setLugarEvento(competicion.getLugarEvento());
			competicionActual.setPlazas(competicion.getPlazas());
			competicionActual.setDificultad(competicion.getDificultad());

			competicionActualizada = competicionService.save(competicionActual);

		} catch (DataAccessException e) {
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
		try {
			Competicion competicion = competicionService.findById(id);
			String nombreFotoAnterior = competicion.getFoto();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			competicionService.delete(id);
		} catch (DataAccessException e) {
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
