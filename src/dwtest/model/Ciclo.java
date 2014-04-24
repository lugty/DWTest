package dwtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CICLOS")
public class Ciclo {
	private int cve_ciclo;
	private String ciclo_escolar;
	private String descripcion;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CVE_CICLO")
	public int getCve_ciclo() {
		return cve_ciclo;
	}
	
	public void setCve_ciclo(int cve_ciclo) {
		this.cve_ciclo = cve_ciclo;
	}
	
	public String getCiclo_escolar() {
		return ciclo_escolar;
	}
	
	public void setCiclo_escolar(String ciclo_escolar) {
		this.ciclo_escolar = ciclo_escolar;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
