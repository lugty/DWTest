package dwtest.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="PERSONA")
public class Persona {

	private int cve_persona;
	private String nombre;
	private String apellidos;
	private String edad;
	private String genero;
	private Date fecha_nacimiento;
	
	private EstadoCivil cve_estado_civil;
	private Domicilio cve_domicilio;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getCve_persona() {
		return cve_persona;
	}
	
	public void setCve_persona(int cve_persona) {
		this.cve_persona = cve_persona;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_ESTADO_CIVIL", referencedColumnName= "CVE_ESTADO_CIVIL")
	public EstadoCivil getCve_estado_civil() {
		return cve_estado_civil;
	}
	public void setCve_estado_civil(EstadoCivil cve_estado_civil) {
		this.cve_estado_civil = cve_estado_civil;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_DOMICILIO", referencedColumnName = "CVE_DOMICILIO")
	public Domicilio getCve_domicilio() {
		return cve_domicilio;
	}
	public void setCve_domicilio(Domicilio cve_domicilio) {
		this.cve_domicilio = cve_domicilio;
	}
	
}
