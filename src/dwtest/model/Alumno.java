package dwtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ALUMNO")
public class Alumno {

	private int cve_alumno;
	private String grado;
	private String grupo;
	private Persona cve_persona;
	private Universidad cve_universidad;
	private AreaGradoAcademico cve_area_grado_academico;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CVE_ALUMNO")
	public int getCve_alumno() {
		return cve_alumno;
	}
	
	public void setCve_alumno(int cve_alumno) {
		this.cve_alumno = cve_alumno;
	}
	
	public String getGrado() {
		return grado;
	}
	
	public void setGrado(String grado) {
		this.grado = grado;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	@OneToOne
	@PrimaryKeyJoinColumn
	public Persona getCve_persona() {
		return cve_persona;
	}
	
	public void setCve_persona(Persona cve_persona) {
		this.cve_persona = cve_persona;
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
	@JoinColumn(name="CVE_AREA_GRADO_ACADEMICO", referencedColumnName="CVE_AREA_GRADO_ACADEMICO")
	public AreaGradoAcademico getCve_area_grado_academico() {
		return cve_area_grado_academico;
	}
	
	public void setCve_area_grado_academico(
			AreaGradoAcademico cve_area_grado_academico) {
		this.cve_area_grado_academico = cve_area_grado_academico;
	}
	
}
