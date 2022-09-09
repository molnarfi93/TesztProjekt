package netbank;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import netbank.Tranzakcio.Statusz;

@Entity
@Table(name="TRANZAKCIO")
public class Tranzakcio {
	
	@Id
	@GeneratedValue
	final int szam;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "felado")
	private final Felhasznalo felado;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cimzett")
	private final Felhasznalo cimzett;
	@Column(name = "osszeg")
	private final int osszeg;
	@Column(name = "datum")
	private final LocalDate datum = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
	@Column(name = "kozlemeny")
	private final String kozlemeny;
	public enum Statusz{ELFOGADVA, FOLYAMATBAN, ELUTASÍTVA};
	@Column(name = "statusz", nullable = false)
	private Statusz statusz;
	
	public Tranzakcio(Felhasznalo felado, Felhasznalo cimzett, int osszeg, String kozlemeny) {
		super();
		this.felado = felado;
		this.cimzett = cimzett;
		this.osszeg = osszeg;
		this.kozlemeny = kozlemeny;
		Statusz statusz;
		LocalDateTime idopont = LocalDateTime.now();
		int hiany = ellEgyenleg();
		if (hiany > 0) statusz = Statusz.ELUTASÍTVA;
		else if (idopont.getHour()>8 && idopont.getHour()<16 && idopont.getDayOfWeek() != DayOfWeek.SATURDAY && idopont.getDayOfWeek() != DayOfWeek.SUNDAY) statusz = Statusz.ELFOGADVA;			
		else statusz = Statusz.FOLYAMATBAN;
	}

	public int getSzam() {
		return szam;
	}

	public Felhasznalo getFelado() {
		return felado;
	}

	public Felhasznalo getCimzett() {
		return cimzett;
	}

	public int getOsszeg() {
		return osszeg;
	}

	public Statusz getStatusz() {
		return statusz;
	}

	public void setStatusz(Statusz statusz) {
		this.statusz = statusz;
	}

	public String getKozlemeny() {
		return kozlemeny;
	}

	public LocalDate getDatum() {
		return datum;
	}
	
	public int ellEgyenleg() {
		int egyenleg = felado.getSzamla().getEgyenleg();
		if (egyenleg < osszeg) {
			return osszeg - egyenleg;
		}
		else return 0;
	}
	
	public void egyenlegModositas(boolean sztorno, Statusz statusz) {
		if (!sztorno) {
			if (statusz == Statusz.ELFOGADVA) {
				felado.getSzamla().setEgyenleg(felado.getSzamla().getEgyenleg() - osszeg);
				cimzett.getSzamla().setEgyenleg(cimzett.getSzamla().getEgyenleg() + osszeg);
			}
			else if (statusz == Statusz.FOLYAMATBAN) {
				felado.getSzamla().setEgyenleg(felado.getSzamla().getEgyenleg() - osszeg);
			}
		}
		else {
			felado.getSzamla().setEgyenleg(felado.getSzamla().getEgyenleg() + osszeg);
			cimzett.getSzamla().setEgyenleg(cimzett.getSzamla().getEgyenleg() - osszeg);
		}
		return;
	}
	
}
