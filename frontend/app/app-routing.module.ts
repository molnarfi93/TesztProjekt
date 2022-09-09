import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { FelhasznalokComponent } from './components/felhasznalok/felhasznalok.component';
import { TranzakciokComponent } from './components/tranzakciok/tranzakciok.component';
import { SzamlaComponent } from './components/szamla/szamla.component';
import { AddTranzakcioComponent } from './components/add-tranzakcio/add-tranzakcio.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LoginComponent },
  { path: 'felhasznalok', component: FelhasznalokComponent },
  { path: 'tranzakciok/:szam', component: TranzakciokComponent },
  { path: 'szamla/:szam', component: SzamlaComponent },
  { path: 'add-tranzakcio/:szam', component: AddTranzakcioComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }