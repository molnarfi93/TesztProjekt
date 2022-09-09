export class Tranzakcio {
	szam: number;
	felado: Felhasznalo;
	cimzett: Felhasznalo;
	osszeg: number;
	datum: date;
	kozlemeny: string;
	statusz: enum;
}