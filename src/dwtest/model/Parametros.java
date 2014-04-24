package dwtest.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Parametros {
	
	private int cve_parametro;
	private String nombre;
	private String descripcion;
	private int valor;
	
	private Universidad cve_universidad;
	private Periodos cve_periodo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CVE_PARAMETRO")
	public int getCve_parametro() {
		return cve_parametro;
	}
	
	public void setCve_parametro(int cve_parametro) {
		this.cve_parametro = cve_parametro;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_UNIVERSIDAD", referencedColumnName="CVE_UNIVERSIDAD")
	public Universidad getCve_universidad() {
		return cve_universidad;
	}
	
	public void setCve_universidad(Universidad cve_universidad) {
		this.cve_universidad = cve_universidad;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_PERIODO", referencedColumnName="CVE_PERIODO")
	public Periodos getCve_periodo() {
		return cve_periodo;
	}
	
	public void setCve_periodo(Periodos cve_periodo) {
		this.cve_periodo = cve_periodo;
	}
	
}
