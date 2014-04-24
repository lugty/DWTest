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
@Table(name="SALDO_ALUMNO")
public class SaldoAlumno {
	
	private int cve_saldo;
	private Alumno cve_alumno;
	private Universidad cve_universidad;
	
	private float saldo;
	private float adeudo;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CVE_SALDO")
	public int getCve_saldo() {
		return cve_saldo;
	}

	public void setCve_saldo(int cve_saldo) {
		this.cve_saldo = cve_saldo;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_ALUMNO", referencedColumnName="CVE_ALUMNO")
	public Alumno getCve_alumno() {
		return cve_alumno;
	}

	public void setCve_alumno(Alumno cve_alumno) {
		this.cve_alumno = cve_alumno;
	}
	
	@ManyToOne
	@JoinColumn(name="CVE_UNIVERSIDAD", referencedColumnName="CVE_UNIVERSIDAD")
	public Universidad getCve_universidad() {
		return cve_universidad;
	}
	
	public void setCve_universidad(Universidad cve_universidad) {
		this.cve_universidad = cve_universidad;
	}
	
	public float getSaldo() {
		return saldo;
	}
	
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public float getAdeudo() {
		return adeudo;
	}
	
	public void setAdeudo(float adeudo) {
		this.adeudo = adeudo;
	}
	
}
