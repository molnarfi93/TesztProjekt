import {Component, NgZone, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../service/api.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Felhasznalo} from '../model/felhasznalo';
import {Szamla} from '../model/szamla';
import {Tranzakcio} from '../model/tranzakcio';
import {Admin} from '../model/admin';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['app.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm: FormGroup;
  felhasznalok: Felhasznalo[];
  adminok: Admin[];
  probalkozasok: number;

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
	this.probalkozasok = 0;  
  }

  mainForm() {
	this.loginForm = this.fb.group({email, jelszo});
  }
  
  get myForm() {
    return this.loginForm.controls;
  }

  login(){
	if (probalkozasok >= 3) {
		alert('3 lehetöség volt próbálkozni!');
	}
	else {
		const email = JSON.stringify(this.loginForm.value.email);
		const jelszo = JSON.stringify(this.loginForm.value.jelszo);
		this.apiService.getMindenFelhasznalo().subscribe((data) => {
			this.felhasznalok = data;
			for (const felhasznalo of this.felhasznalok) {
				if (JSON.stringify(felhasznalo.email) == email && JSON.stringify(felhasznalo.jelszo) == jelszo) {
					let szam = felhasznalo.szamla.szam;
					this.ngZone.run(() => this.router.navigateByUrl(['/szamla', {szam: this.szam}]));
					return;
				}
			}
		}, (error) => {
			alert(error);
		});
		this.apiService.getMindenAdmin().subscribe((data) => {
			this.adminok = data;
			for (const admin of this.adminok) {
				if (JSON.stringify(admin.email) == email && JSON.stringify(admin.jelszo) == jelszo) {
					this.ngZone.run(() => this.router.navigateByUrl('/felhasznalok'));
					return;
				}
			}
		}, (error) => {
			alert(error);
		});  
		probalkozasok = probalkozasok + 1;
		alert('Nincs ilyen felhasználó!');
	}
  }
  
}