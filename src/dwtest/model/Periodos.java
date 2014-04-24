package dwtest.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PERIODOS")
public class Periodos {
	private int cve_periodos;
	private int numero_periodo;
	private int ano;
	private Timestamp fecha_inicio;
	private Timestamp fecha_fin;
	private boolean activo;
	
	private Universidad cve_universidad;
	private Ciclo cve_ciclo;
	private Persona cve_persona;
	private PadecimientosPersona cve_padecimeinto;
	private EstadoCivil cve_estado_civil;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CVE_PERIODOS")
	public int getCve_periodos() {
		return cve_periodos;
	}
	
	public void setCve_periodos(int cve_periodos) {
		this.cve_periodos = cve_periodos;
	}
	
	public int getNumero_periodo() {
		return numero_periodo;
	}
	
	public void setNumero_periodo(int numero_periodo) {
		this.numero_periodo = numero_periodo;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public Timestamp getFecha_inicio() {
		return fecha_inicio;
	}
	
	public void setFecha_inicio(Timestamp fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	
	public Timestamp getFecha_fin() {
		return fecha_fin;
	}
	
	public void setFecha_fin(Timestamp fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_UNIVERSIDAD")
	public Universidad getCve_universidad() {
		return cve_universidad;
	}
	
	public void setCve_universidad(Universidad cve_universidad) {
		this.cve_universidad = cve_universidad;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_CICLO", referencedColumnName = "CVE_CICLO")
	public Ciclo getCve_ciclo() {
		return cve_ciclo;
	}
	
	public void setCve_ciclo(Ciclo cve_ciclo) {
		this.cve_ciclo = cve_ciclo;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_PERSONA", referencedColumnName="CVE_PERSONA")
	public Persona getCve_persona() {
		return cve_persona;
	}
	
	public void setCve_persona(Persona cve_persona) {
		this.cve_persona = cve_persona;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_PADECIMIENTO", referencedColumnName="CVE_PADECIMIENTO")
	public PadecimientosPersona getCve_padecimeinto() {
		return cve_padecimeinto;
	}
	
	public void setCve_padecimeinto(PadecimientosPersona cve_padecimeinto) {
		this.cve_padecimeinto = cve_padecimeinto;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_ESTADO_CIVIL", referencedColumnName="CVE_ESTADO_CIVIL")
	public EstadoCivil getCve_estado_civil() {
		return cve_estado_civil;
	}
	
	public void setCve_estado_civil(EstadoCivil cve_estado_civil) {
		this.cve_estado_civil = cve_estado_civil;
	}
	
}
