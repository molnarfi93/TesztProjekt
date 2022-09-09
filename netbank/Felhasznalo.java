package netbank;

@Entity
@Table(name="FELHASZNALO")
public class Felhasznalo {
	
	@Column(name = "szig_szam", unique = true)
	private String szigSzam;
	@Column(name = "nev", nullable = false)
	private String nev;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "szamla")
	private Szamla szamla;
	@Id
	@Column(name = "email")
	String email;
	@Column(name = "jelszo")
	private String jelszo;
	
	public Felhasznalo(String szigSzam, String nev, String email, String jelszo) {
		super();
		this.szigSzam = szigSzam;
		this.nev = nev;
		this.email = email;
		this.jelszo = jelszo;
	}
	
	public String getSzigSzam() {
		return szigSzam;
	}

	public void setSzigSzam(String szigSzam) {
		this.szigSzam = szigSzam;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public Szamla getSzamla() {
		return szamla;
	}

	public void setSzamla(Szamla szamla) {
		this.szamla = szamla;
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
