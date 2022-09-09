package netbank;

@Entity
@Table(name="ADMIN")
public class Admin {

	@Column(name = "nev", unique = true)
	private String nev;
	@Id
	@Column(name = "email")
	private String email;
	@Column(name = "jelszo")
	private String jelszo;
	
	public Admin(String nev, String email, String jelszo) {
		super();
		this.nev = nev;
		this.email = email;
		this.jelszo = jelszo;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJelszo() {
		return jelszo;
	}

	public void setJelszo(String jelszo) {
		this.jelszo = jelszo;
	}
	
}
