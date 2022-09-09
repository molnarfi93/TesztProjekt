import {Component, NgZone, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../service/api.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Felhasznalo} from '../model/felhasznalo';
import {Szamla} from '../model/szamla';
import {Tranzakcio} from '../model/tranzakcio';

@Component({
  selector: 'app-add-tranzakcio',
  templateUrl: './add-tranzakcio.component.html',
  styleUrls: ['app.component.css']
})
export class AddTranzakcioComponent implements OnInit {
  
  felhasznalok: Felhasznalo[];
  tranzakcioForm: FormGroup;
  szam: string;

  constructor(
    private actRoute: ActivatedRoute,
    private router: Router,
    private ngZone: NgZone,
    private apiService: ApiService,
	private fb: FormBuilder
  ) {
	  this.mainForm();
  }	

  ngOnInit(): void {
    this.szam = this.actRoute.snapshot.paramMap.get('szam');	
  }
  
  mainForm() {
	this.tranzakcioForm = this.fb.group({felado, cimzett, osszeg, kozlemeny});
  }
  
  get myForm() {
    return this.tranzakcioForm.controls;
  }
  
  loadSzamla(){
	this.ngZone.run(() => this.router.navigateByUrl(['/szamla', {szam: this.szam}]));
  }
  
  addTranzakcio(){
	const cimzett = JSON.stringify(this.tranzakcioForm.value.cimzett);
    this.apiService.getMindenFelhasznalo().subscribe((data) => {
	  this.felhasznalok = data;
	  for (const felhasznalo of this.felhasznalok) {
	    if (JSON.stringify(felhasznalo.szamla.szam) == cimzett) {
			this.apiService.addTranzakcio(this.tranzakcioForm.value);
			alert('Kérés rögzítve');
			this.loadSzamla();
		}
	  }
	}, (error) => {
      alert(error);
	});  
	alert('Nincs ilyen számlaszám!');
  }
  
}