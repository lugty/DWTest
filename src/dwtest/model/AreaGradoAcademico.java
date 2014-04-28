package dwtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AREA_GRADO_ACADEMICO")
public class AreaGradoAcademico {
	private int cve_area_grado_academico;
	private String descripcion;
	private boolean activo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CVE_AREA_GRADO_ACADEMICO")
	public int getCve_area_grado_academico() {
		return cve_area_grado_academico;
	}
	
	public void setCve_area_grado_academico(int cve_area_grado_academico) {
		this.cve_area_grado_academico = cve_area_grado_academico;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
}
