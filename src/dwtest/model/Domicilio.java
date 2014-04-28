package dwtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.CascadeType;

@Entity
@Table(name="DOMICILIOS")
public class Domicilio {

	private int cve_domicilio;
	private String codigo_postal;
	private int consecutivo_cp;
	private String calle;
	private String numero_interior;
	private String numero_exterior;
	private String numero_depto;
	private String comentario;
	private String activo;
	
	private Municipio cve_municipio;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CVE_DOMICILIO")
	public int getCve_domicilio() {
		return cve_domicilio;
	}

	public void setCve_domicilio(int cve_domicilio) {
		this.cve_domicilio = cve_domicilio;
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public int getConsecutivo_cp() {
		return consecutivo_cp;
	}

	public void setConsecutivo_cp(int consecutivo_cp) {
		this.consecutivo_cp = consecutivo_cp;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero_interior() {
		return numero_interior;
	}

	public void setNumero_interior(String numero_interior) {
		this.numero_interior = numero_interior;
	}

	public String getNumero_exterior() {
		return numero_exterior;
	}

	public void setNumero_exterior(String numero_exterior) {
		this.numero_exterior = numero_exterior;
	}

	public String getNumero_depto() {
		return numero_depto;
	}

	public void setNumero_depto(String numero_depto) {
		this.numero_depto = numero_depto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="CVE_MUNICIPIO", referencedColumnName = "CVE_MUNICIPIO")
	public Municipio getCve_municipio() {
		return cve_municipio;
	}

	public void setCve_municipio(Municipio cve_municipio) {
		this.cve_municipio = cve_municipio;
	}
	
	
	
}
