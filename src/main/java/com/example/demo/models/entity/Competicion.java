package com.example.demo.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "COMPETICIONES")
public class Competicion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCompeticion;
	
	@Column(name="nombre_competicion", nullable = false)
	@NotNull(message = "El nombre de la competición no puede ser nulo")
	@Size(min = 2)
	private String nombreCompeticion;
	
	@Column()
	@NotNull(message = "La descipcion de la competición no puede ser nula")
	@NotEmpty
	private String descripcion;
	
	@Column()
	@NotNull(message = "La cantidad de plazas no puede ser nula")
	private int plazas;
	
	@Column(name="lugar_evento")
	@NotNull(message = "El lugar del evento no puede ser nulo")
	private String lugarEvento;
	@Column()
	@NotNull(message = "La dificultad no puede ser nula")
	private int dificultad;
	
	public Competicion() {

	}

	
	public Competicion(long idCompeticion, String nombreCompeticion, String descripcion, int plazas,
			String lugarEvento, int dificultad) {

		this.idCompeticion = idCompeticion;
		this.nombreCompeticion = nombreCompeticion;
		this.descripcion = descripcion;
		this.plazas = plazas;
		this.lugarEvento = lugarEvento;
		this.dificultad = dificultad;
	}

	public long getIdCompeticion() {
		return idCompeticion;
	}

	public void setIdCompeticion(int idCompeticion) {
		this.idCompeticion = idCompeticion;
	}

	public String getNombreCompeticion() {
		return nombreCompeticion;
	}

	public void setNombreCompeticion(String nombreCompeticion) {
		this.nombreCompeticion = nombreCompeticion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public String getLugarEvento() {
		return lugarEvento;
	}

	public void setLugarEvento(String lugarEvento) {
		this.lugarEvento = lugarEvento;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	@Override
	public String toString() {
		return "Competicion [idCompeticion=" + idCompeticion + ", nombreCompeticion=" + nombreCompeticion
				+ ", descripcion=" + descripcion + ", plazas=" + plazas + ", lugar_evento=" + lugarEvento
				+ ", dificultad=" + dificultad + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + dificultad;
		result = prime * result + (int) (idCompeticion ^ (idCompeticion >>> 32));
		result = prime * result + ((lugarEvento == null) ? 0 : lugarEvento.hashCode());
		result = prime * result + ((nombreCompeticion == null) ? 0 : nombreCompeticion.hashCode());
		result = prime * result + plazas;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competicion other = (Competicion) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (dificultad != other.dificultad)
			return false;
		if (idCompeticion != other.idCompeticion)
			return false;
		if (lugarEvento == null) {
			if (other.lugarEvento != null)
				return false;
		} else if (!lugarEvento.equals(other.lugarEvento))
			return false;
		if (nombreCompeticion == null) {
			if (other.nombreCompeticion != null)
				return false;
		} else if (!nombreCompeticion.equals(other.nombreCompeticion))
			return false;
		if (plazas != other.plazas)
			return false;
		return true;
	}	

}
