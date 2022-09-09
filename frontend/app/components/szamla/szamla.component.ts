import {Component, NgZone, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../service/api.service';
import {Felhasznalo} from '../model/felhasznalo';
import {Szamla} from '../model/szamla';
import {Tranzakcio} from '../model/tranzakcio';

@Component({
  selector: 'app-szamla',
  templateUrl: './szamla.component.html',
  styleUrls: ['app.component.css']
})
export class SzamlaComponent implements OnInit {
  
  szamla: Szamla;
  szam: string;

  constructor(
    private actRoute: ActivatedRoute,
    private router: Router,
    private ngZone: NgZone,
    private apiService: ApiService
  ) {
  }	

  ngOnInit(): void {
	this.szam = this.actRoute.snapshot.paramMap.get('szam');  
	this.getSzamla(szam);
  }

  loadAddTranzakcio(){
    this.ngZone.run(() => this.router.navigateByUrl(['/add-tranzakcio', {szam: this.szam}]));
  }	
  
  getSzamla(szam){
    this.apiService.getSzamla(szam).subscribe((data) => {
        this.szamla = data;
	}, (error) => {
		alert(error);
	});  
  }
  
}