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
@Table(name="PADECIMIENTOS_PERSONA")
public class PadecimientosPersona {
	private int cve_padecimiento;
	private String notas;
	private Persona cve_persona;
	private Universidad cve_universidad;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CVE_PADECIMIENTO")
	public int getCve_padecimiento() {
		return cve_padecimiento;
	}
	
	public void setCve_padecimiento(int cve_padecimiento) {
		this.cve_padecimiento = cve_padecimiento;
	}
	
	public String getNotas() {
		return notas;
	}
	
	public void setNotas(String notas) {
		this.notas = notas;
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
	@JoinColumn(name="CVE_UNIVERSIDAD", referencedColumnName="CVE_UNIVERSIDAD")
	public Universidad getCve_universidad() {
		return cve_universidad;
	}
	
	public void setCve_universidad(Universidad cve_universidad) {
		this.cve_universidad = cve_universidad;
	}
	
}
