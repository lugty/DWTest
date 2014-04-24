package dwtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="UNIVERSIDAD")
public class Universidad {
	private int cve_universidad;
	private String nombre;
	private Domicilio cve_domicilio;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CVE_UNIVERSIDAD")
	public int getCve_universidad() {
		return cve_universidad;
	}
	
	public void setCve_universidad(int cve_universidad) {
		this.cve_universidad = cve_universidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_DOMICILIO", referencedColumnName="CVE_DOMICILIO")
	public Domicilio getCve_domicilio() {
		return cve_domicilio;
	}
	
	public void setCve_domicilio(Domicilio cve_domicilio) {
		this.cve_domicilio = cve_domicilio;
	}
	
}
