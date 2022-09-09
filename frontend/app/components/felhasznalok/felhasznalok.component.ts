import {Component, NgZone, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../service/api.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Felhasznalo} from '../model/felhasznalo';
import {Szamla} from '../model/szamla';
import {Tranzakcio} from '../model/tranzakcio';


@Component({
  selector: 'app-felhasznalok',
  templateUrl: './felhasznalok.component.html',
  styleUrls: ['app.component.css']
})
export class FelhasznalokComponent implements OnInit {
  
  felhasznalok: Felhasznalo[];
  felhasznalo: Felhasznalo;
  felhasznaloForm: FormGroup;

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
    this.getMindenFelhasznalo();	
  }
  
  mainForm() {
	this.felhasznaloForm = this.fb.group({felhasznalo});
  }
  
   get myForm() {
    return this.felhasznaloForm.controls;
  }

  loadTranzakciok(szam){
    this.ngZone.run(() => this.router.navigateByUrl(['/tranzakciok', {szam: this.szam}]));
  }
  
  getMindenFelhasznalo(){
    this.apiService.getMindenFelhasznalo().subscribe((data) => {
        this.felhasznalok = data;
    }, (error) => {
		alert(error);
	});  
  }
  
  getFelhasznalo(){
	  this.apiService.getFelhasznaloByEmail(this.felhasznaloForm.value.felhasznalo).subscribe((data) => {
		this.felhasznalo = data;
		this.loadTranzakciok(felhasznalo.szamla.szam);
		return;
	  });
	  this.apiService.getFelhasznaloBySzamla(this.felhasznaloForm.value.felhasznalo).subscribe((data) => {
		this.felhasznalo = data;
		this.loadTranzakciok(felhasznalo.szamla.szam);
		return;
	  });
	  alert('Nincs ilyen felhasználó!');
  }
  
}