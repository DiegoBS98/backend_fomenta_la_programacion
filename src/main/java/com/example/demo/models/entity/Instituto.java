package com.example.demo.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "INSTITUTOS")
public class Instituto {
	@Id
	@NotNull(message = "El CIF del instituto no puede ser nulo")
	private String cif_instituto;
	@Column(name="nombre_instituto")
	@NotNull(message = "El nombre del instituto no puede ser nulo")
	private  String nombre;
	@Column(name="localizacion")
	@NotNull(message = "La localización del instituto no puede ser nula")
	private String localizacion;
	@Column(name="email_contacto")
	@NotNull(message = "El email del instituto no puede ser nulo")
	private String email_contacto;
	@Column(name="telefono_contacto")
	@NotNull(message = "El telefono del instituto no puede ser nulo")
	private int telefono_contacto;
	
	public Instituto() {
		
	}

	public Instituto(@NotNull(message = "El CIF del instituto no puede ser nulo") String cif_instituto,
			@NotNull(message = "El nombre del instituto no puede ser nulo") String nombre,
			@NotNull(message = "La localización del instituto no puede ser nula") String localizacion,
			@NotNull(message = "El email del instituto no puede ser nulo") String email_contacto,
			@NotNull(message = "El telefono del instituto no puede ser nulo") int telefono_contacto) {
		super();
		this.cif_instituto = cif_instituto;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.email_contacto = email_contacto;
		this.telefono_contacto = telefono_contacto;
	}

	public String getCif_instituto() {
		return cif_instituto;
	}

	public void setCif_instituto(String cif_instituto) {
		this.cif_instituto = cif_instituto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getEmail_contacto() {
		return email_contacto;
	}

	public void setEmail_contacto(String email_contacto) {
		this.email_contacto = email_contacto;
	}

	public int getTelefono_contacto() {
		return telefono_contacto;
	}

	public void setTelefono_contacto(int telefono_contacto) {
		this.telefono_contacto = telefono_contacto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif_instituto == null) ? 0 : cif_instituto.hashCode());
		result = prime * result + ((email_contacto == null) ? 0 : email_contacto.hashCode());
		result = prime * result + ((localizacion == null) ? 0 : localizacion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + telefono_contacto;
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
		Instituto other = (Instituto) obj;
		if (cif_instituto == null) {
			if (other.cif_instituto != null)
				return false;
		} else if (!cif_instituto.equals(other.cif_instituto))
			return false;
		if (email_contacto == null) {
			if (other.email_contacto != null)
				return false;
		} else if (!email_contacto.equals(other.email_contacto))
			return false;
		if (localizacion == null) {
			if (other.localizacion != null)
				return false;
		} else if (!localizacion.equals(other.localizacion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono_contacto != other.telefono_contacto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Instituto [cif_instituto=" + cif_instituto + ", nombre=" + nombre + ", localizacion=" + localizacion
				+ ", email_contacto=" + email_contacto + ", telefono_contacto=" + telefono_contacto + "]";
	}
	
	

	
	
	
	
	
}
