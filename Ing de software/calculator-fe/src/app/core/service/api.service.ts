import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  readonly ENDPOINT = 'http://ismi.fi.upm.es:8087';

  constructor( private http: HttpClient, private cookie: CookieService ) {}

  validar<T>(coordinates: string[]): Observable<T> {
    const body = { "coordinates" : coordinates };
    console.log(body)
    return this.http.post<T>(`${this.ENDPOINT}/validar`, body);
  }

  calcSuperficie<T>(coordinates: string[]): Observable<T> {
    const body = { "coordinates" : coordinates };
    return this.http.post<T>(`${this.ENDPOINT}/calcSuperficie`, body);
  }

  getOcupados<T>(): Observable<T> {
    return this.http.get<T>(`${this.ENDPOINT}/getOcupados`);
  }

  setCookie(code: any, value:any): void{
    this.cookie.set(code, value, undefined,  '/');
  }
  
  getCookie(code: any): any{
    return this.cookie.get(code);
  }

}
