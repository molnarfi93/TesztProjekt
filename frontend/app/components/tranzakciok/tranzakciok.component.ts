import {Component, NgZone, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../service/api.service';
import {Felhasznalo} from '../model/felhasznalo';
import {Szamla} from '../model/szamla';
import {Tranzakcio} from '../model/tranzakcio';

@Component({
  selector: 'app-tranzakciok',
  templateUrl: './tranzakciok.component.html',
  styleUrls: ['app.component.css']
})
export class TranzakciokComponent implements OnInit {
  
  tranzakciok: Tranzakcio[];
  szam: string;

  constructor(
    private actRoute: ActivatedRoute,
    private router: Router,
    private ngZone: NgZone,
    private apiService: ApiService
  ) {
	  this.apiService.listen.subscribe(() => {
		  this.getTranzakciok(szam);
	  })
  }	

  ngOnInit(): void {
    this.szam = this.actRoute.snapshot.paramMap.get('szam');
    this.getTranzakciok(szam);	
  }
  
  get myForm() {
    return this.tranzakciokForm.controls;
  }

  loadFelhasznalok(){
    this.ngZone.run(() => this.router.navigateByUrl('/felhasznalok'));
  }
  
  getTranzakciok(szam){
    this.apiService.getTranzakciok(szam).subscribe((data) => {
        this.tranzakciok = data;
    }, (error) => {
		alert(error);
	});
  }	
  
  deleteTranzakcio(tranzakcio_szam){
	if (window.confirm('Biztosan törölni akarod?')) {
        this.apiService.deleteTranzakcio(tranzakcio_szam).subscribe((res) => {
            this.getTranzakciok(szam);
        }, (error) => {
			alert(error);
		});  
	}  
  }
  
}