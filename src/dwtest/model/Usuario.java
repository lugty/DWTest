package dwtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario {
	
	private int cve_usuario;
	private String login;
	private String password;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CVE_USUARIO")
	public int getCve_usuario() {
		return cve_usuario;
	}
	
	public void setCve_usuario(int cve_usuario) {
		this.cve_usuario = cve_usuario;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
