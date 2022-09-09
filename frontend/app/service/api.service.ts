import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class ApiService {

  baseUri = 'http://localhost:4200/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }
  
  addSzamla(data): Observable<any> {
    const url = `${this.baseUri}/addSzamla`;
    return this.http.post(url, data)
      .pipe(
        catchError(this.errorMgmt)
      );
  }
  
  getSzamla(szam): Observable<any> {
	const url = `${this.baseUri}/getSzamla/${szam}`;
    return this.http.get(url, {headers: this.headers}).pipe(
      map((res: Response) => {
        return res || {};
      }),
      catchError(this.errorMgmt)
    );
  }  
  
  addTranzakcio(data): Observable<any> {
    const url = `${this.baseUri}/addTranzakcio`;
    return this.http.post(url, data)
      .pipe(
        catchError(this.errorMgmt)
      );
  }
  
  getTranzakciok(szam): Observable<any> {
    const url = `${this.baseUri}/getTranzakciok/${szam}`;
    return this.http.get(url, {headers: this.headers}).pipe(
      map((res: Response) => {
        return res || {};
      }),
      catchError(this.errorMgmt)
    );
  }
  
  deleteTranzakcio(szam): Observable<any> {
    const url = `${this.baseUri}/deleteTranzakcio/${szam}`;
    return this.http.delete(url, { headers: this.headers }).pipe(
      catchError(this.errorMgmt)
    );
  }
  
  getMindenFelhasznalo(): Observable<any> {
	 const url = `${this.baseUri}/getMindenFelhasznalo`;
     return this.http.get(url).pipe(
      map((res: Response) => {
        return res || {};
      }),
      catchError(this.errorMgmt)
    );
  }

  getFelhasznaloByEmail(email): Observable<any> {
	const url = `${this.baseUri}/getFelhasznaloByEmail/${email}`;
    return this.http.get(url, {headers: this.headers}).pipe(
      map((res: Response) => {
        return res || {};
      }),
      catchError(this.errorMgmt)
    );
  }

  getFelhasznaloBySzamla(szam): Observable<any> {
	const url = `${this.baseUri}/getFelhasznaloBySzamla/${szam}`;
    return this.http.get(url, {headers: this.headers}).pipe(
      map((res: Response) => {
        return res || {};
      }),
      catchError(this.errorMgmt)
    );
  } 
  
  updateFelhasznalo(email, data): Observable<any> {
    const url = `${this.baseUri}/updateFelhasznalo/${email}`;
    return this.http.put(url, data, {headers: this.headers}).pipe(
      catchError(this.errorMgmt)
    );
  }
  
  getMindenAdmin(): Observable<any> {
	 const url = `${this.baseUri}/getMindenAdmin`;
     return this.http.get(url).pipe(
      map((res: Response) => {
        return res || {};
      }),
      catchError(this.errorMgmt)
    );
  } 
  
  errorMgmt(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
  
}  