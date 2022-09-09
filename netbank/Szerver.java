package netbank;

import org.springframework.boot.*;
import org.springframework.web.*;
import org.springframework.data.*;
import java.util.List;
import java.util.ArrayList;

@RestController
public class Szerver {
	
	public static class SzamlaResource {
		
		@PostMapping("/addSzamla")
		public void onPost(@RequestBody Szamla szamla) {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			Controller.addSzamla(szamla);
			return;
		}
		
		@GetMapping("/getSzamla/{szam}")
		public Szamla onGet(@PathVariable("szam") String szam) {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			Szamla szamla = Controller.getSzamla(szam);
			return szamla;
		}
		
	}
		
	public static class TranzakcioResource {
		
		@PostMapping("/addTranzakcio")
		public int onPost(@RequestBody Tranzakcio tranzakcio) {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			int hiany = Controller.addTranzakcio(tranzakcio);
			return hiany;
		}
		
		@GetMapping("/getTranzakciok/{szam}")
		public Tranzakcio[] onGet(@PathVariable("szam") String szam) {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			ArrayList<Tranzakcio> tranzakciok_lista = Controller.getTranzakciok(szam);
			Tranzakcio[] tranzakciok_tomb = tranzakciok_lista.toArray(new Tranzakcio[tranzakciok_lista.size()]);
			return tranzakciok_tomb;
		}
		
		@DeleteMapping("/deleteTranzakcio/{szam}")
		public void onDelete(@PathVariable("szam") int szam) {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			Controller.deleteTranzakcio(szam);
			return;
		}
	
	}
	
	public static class FelhasznaloResource {
		
		@GetMapping("/getMindenFelhasznalo")
		public Felhasznalo[] onGet() {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			List<Felhasznalo> felhasznalok_lista = Controller.getMindenFelhasznalo();
			Felhasznalo[] felhasznalok_tomb = felhasznalok_lista.toArray(new Felhasznalo[felhasznalok_lista.size()]);
			return felhasznalok_tomb;
		}
		
		@GetMapping("/getFelhasznaloByEmail/{email}")
		public Felhasznalo onGetByEmail(@PathVariable("email") String email) {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			Felhasznalo felhasznalo = Controller.getFelhasznaloByEmail(email);
			return felhasznalo;
		}
		
		@GetMapping("/getFelhasznaloBySzamla/{szam}")
		public Felhasznalo onGetBySzamla(@PathVariable("szam") String szam) {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			Szamla szamla = Controller.getSzamla(szam);
			Felhasznalo felhasznalo = Controller.getFelhasznaloBySzamla(szamla);
			return felhasznalo;
		}
		
		@PutMapping("/updateFelhasznalo/{email}") 
		public void onPut(@PathVariable("email") String email, @RequestBody Felhasznalo felhasznalo) {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			Controller.updateFelhasznalo(felhasznalo);
		}
		
	}
	
	public static class AdminResource {
		
		@GetMapping("/getMindenAdmin")
		public Admin[] onGet() {
			/* Authentikáció,
			 * JSON dekódolás
			 * még elvégzendö */
			List<Admin> adminok_lista = Controller.getMindenAdmin();
			Admin[] adminok_tomb = adminok_lista.toArray(new Admin[adminok_lista.size()]);
			return adminok_tomb;
		}
		
	}

}
