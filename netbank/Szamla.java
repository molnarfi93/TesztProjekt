package netbank;

import java.time.LocalDate;
import java.util.regex.*;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "SZAMLA")
public class Szamla {
	
	@Id
	@Column(name = "szam")
	final String szam;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tulajdonos")
	private Felhasznalo tulajdonos;
	@Column(name = "egyenleg", nullable = false)
	private int egyenleg;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tranzakciok")
	private ArrayList<Tranzakcio> tranzakciok;
	@Column(name = "letrehozas")
	private final LocalDate letrehozas = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
	
	public Szamla(String szam, int egyenleg) {
		super();
		this.szam = szam;
		this.egyenleg = egyenleg;
	}

	public String getSzam() {
		return szam;
	}
	
	public Felhasznalo getTulajdonos() {
		return tulajdonos;
	}

	public void setTulajdonos(Felhasznalo tulajdonos) {
		this.tulajdonos = tulajdonos;
	}

	public int getEgyenleg() {
		return egyenleg;
	}

	public void setEgyenleg(int egyenleg) {
		this.egyenleg = egyenleg;
	}
	
	public ArrayList<Tranzakcio> getTranzakciok() {
		return tranzakciok;
	}
	
	public void setTranzakciok(Tranzakcio tranzakcio) {
		tranzakciok.add(tranzakcio);
	}

	public LocalDate getLetrehozas() {
		return letrehozas;
	}
	
	public boolean ellSzamlaszam() {
		Pattern pattern = Pattern.compile("[0-9]{8}[-][0-9]{8}[-][0-9]{8}");
		Matcher matcher = pattern.matcher(szam);
		boolean ok = matcher.find();
		return ok;
	}
	
}
