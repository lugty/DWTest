package dwtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ESTADO_CIVIL")
public class EstadoCivil {
	
	private int cve_estado_civil;
	private String descripcion;
	private String abreviatura;
	private boolean activo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CVE_ESTADO_CIVIL")
	public int getCve_estado_civil() {
		return cve_estado_civil;
	}
	public void setCve_estado_civil(int cve_estado_civil) {
		this.cve_estado_civil = cve_estado_civil;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
}
