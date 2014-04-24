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
@Table(name="MUNICIPIO")
public class Municipio {
	
	private int cve_municipio;
	private String municipio;
	private Estado estado;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CVE_MUNICIPIO")
	public int getCve_municipio() {
		return cve_municipio;
	}
	public void setCve_municipio(int cve_municipio) {
		this.cve_municipio = cve_municipio;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_ESTADO", referencedColumnName = "CVE_ESTADO")
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
