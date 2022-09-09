package netbank;

import org.hibernate.*;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import netbank.Tranzakcio.Statusz;

public static class Controller {

	private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
	
	public static void addSzamla(Szamla szamla) {
		Session s = sf.openSession();
		Transaction t = null;
		if (szamla.ellSzamlaszam() == true) {
			try {
				t = s.beginTransaction();
				s.save(szamla);
				Felhasznalo tulajdonos = (Felhasznalo)s.get(Felhasznalo.class, szamla.getTulajdonos());
				tulajdonos.setSzamla(szamla);
				s.update(tulajdonos);
				t.commit();
			} catch (HibernateException e) {
				if (t != null) t.rollback();
				e.printStackTrace();
			} finally {
				s.close();
			}
		}
		else System.out.println("Érvénytelen számlaszám!"); 
		return;
	}
	
	public static Szamla getSzamla(String szam) {
		Session s = sf.openSession();
		Transaction t = null;
		Szamla szamla;
		try {
			t = s.beginTransaction();
			szamla = (Szamla)s.get(Szamla.class, szam);
			t.commit();
		} catch (HibernateException e) {
			if (t != null) t.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return szamla;
	}
	
	public static int addTranzakcio(Tranzakcio tranzakcio) {
		Session s = sf.openSession();
		Transaction t = null;
		int hiany = tranzakcio.ellEgyenleg();
		if (hiany == 0) {
			try {
				t = s.beginTransaction();
				s.save(tranzakcio);
				tranzakcio.egyenlegModositas(false, tranzakcio.getStatusz());
				tranzakcio.getFelado().getSzamla().setTranzakciok(tranzakcio);
				tranzakcio.getCimzett().getSzamla().setTranzakciok(tranzakcio);
				t.commit();
			} catch (HibernateException e) {
				if (t != null) t.rollback();
				e.printStackTrace();
			} finally {
				s.close();
			}
		}
		else System.out.println("Nincs elég fedezet! Hiány: "+hiany+" Ft.");
		return hiany;
	}
	
	public static ArrayList<Tranzakcio> getTranzakciok(String szam) {
		Session s = sf.openSession();
		Transaction t = null;
		Szamla szamla;
		ArrayList<Tranzakcio> tranzakciok;
		try {
			t = s.beginTransaction();
			szamla = (Szamla)s.get(Szamla.class, szam);
			tranzakciok = szamla.getTranzakciok();
			t.commit();
		} catch (HibernateException e) {
			if (t != null) t.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return tranzakciok;
	}
	
	public static void deleteTranzakcio(int szam) {
		Session s = sf.openSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
			Tranzakcio tranzakcio = (Tranzakcio)s.get(Tranzakcio.class, szam);
			tranzakcio.egyenlegModositas(true, null);
			s.delete(tranzakcio);
			t.commit();
		} catch (HibernateException e) {
			if (t != null) t.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return;
	}
	
	public static List<Felhasznalo> getMindenFelhasznalo() {
		Session s = sf.openSession();
		Transaction t = null;
		List<Felhasznalo> felhasznalok = s.createQuery("FROM Felhasznalo").list();
		try {
			t = s.beginTransaction();
			for (Iterator<Felhasznalo> iterator = felhasznalok.iterator(); iterator.hasNext();) {
				Felhasznalo felhasznalo = (Felhasznalo)iterator.next();
				felhasznalok.add(felhasznalo);
			}
			t.commit();
		} catch (HibernateException e) {
			if (t != null) t.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return felhasznalok;
	}
	
	public static Felhasznalo getFelhasznaloByEmail(String email) {
		Session s = sf.openSession();
		Transaction t = null;
		Felhasznalo felhasznalo;
		try {
			t = s.beginTransaction();
			felhasznalo = (Felhasznalo)s.get(Felhasznalo.class, email);
			t.commit();
		} catch (HibernateException e) {
			if (t != null) t.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return felhasznalo;
	}
	
	public static Felhasznalo getFelhasznaloBySzamla(Szamla szamla) {
		Session s = sf.openSession();
		Transaction t = null;
		Felhasznalo felhasznalo;
		try {
			t = s.beginTransaction();
			felhasznalo = (Felhasznalo)s.get(Felhasznalo.class, szamla);
			t.commit();
		} catch (HibernateException e) {
			if (t != null) t.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return felhasznalo;
	}
	
	public static void updateFelhasznalo(Felhasznalo felhasznalo) {
		Session s = sf.openSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
			Felhasznalo modFelhasznalo = (Felhasznalo)s.get(Felhasznalo.class, felhasznalo.getEmail());
			modFelhasznalo.setNev(felhasznalo.getNev());
			modFelhasznalo.setEmail(felhasznalo.getEmail());
			modFelhasznalo.setJelszo(felhasznalo.getJelszo());
			s.update(modFelhasznalo);
			t.commit();
		} catch (HibernateException e) {
			if (t != null) t.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
	
	public static List<Admin> getMindenAdmin() {
		Session s = sf.openSession();
		Transaction t = null;
		List<Admin> adminok = s.createQuery("FROM Admin").list();
		try {
			t = s.beginTransaction();
			for (Iterator<Admin> iterator = adminok.iterator(); iterator.hasNext();) {
				Admin admin = (Admin)iterator.next();
				adminok.add(admin);
			}
			t.commit();
		} catch (HibernateException e) {
			if (t != null) t.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return adminok;
	}
	
}
