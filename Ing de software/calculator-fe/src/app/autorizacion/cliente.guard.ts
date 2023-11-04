import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteGuard implements CanActivate {

  private login: string = 'https://skrm.ismigit.fi.upm.es/admin-fe/';

  constructor(
    private cookieService: CookieService,
    private router: Router,
    private http: HttpClient
    ){}

  // El guarda nos permite comprobar que antes de acceder a nuestra página el usuario tenga las cookies necesarias
  // para poder ser auorizado.
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    // Descomentar cuando probeis en local
    return true;
    const correcto = this.cookieService.check('token') && this.cookieService.check('email');

    if(!correcto){
      this.redirect();
      return false;
    }
  
    const email = this.cookieService.get('email');
    const token = this.cookieService.get('token');
    
    return this.http.get(`http://ismi.fi.upm.es:8086/getUserInfo/${email}`).pipe(map((auth)=>{
      if(auth){
        console.log('Autorizado');
        return true;
      }
      console.log("No autorizado");
      this.redirect();
      return false;
    }))

  }

  // Función que redirige a la página de login de la app
  redirect(){

    // Redirige a la página de login falsa
    //Descomentar cuando probeis en local
    this.router.navigate(['/login']);

    // Hasta que login no consiga hacer su manejo de cookies no lo activamos
    // y usamos la pagina de login falsa.
    // Comentar cuando probeis en local
    // window.location.href = this.login;
  }
  
}
