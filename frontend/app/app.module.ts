import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ApiService } from './service/api.service';
import { LoginComponent } from './components/login/login.component';
import { FelhasznalokComponent } from './components/felhasznalok/felhasznalok.component';
import { TranzakciokComponent } from './components/tranzakciok/tranzakciok.component';
import { SzamlaComponent } from './components/szamla/szamla.component';
import { AddTranzakcioComponent } from './components/add-tranzakcio/add-tranzakcio.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FelhasznalokComponent,
	TranzakciokComponent,
	SzamlaComponent,
	AddTranzakcioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
	HttpClientModule
  ],
  providers: [ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }