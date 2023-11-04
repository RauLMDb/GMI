import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable()
export class ClienteInterceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService) {}

  // Toma cualquier petición http y le añade el token en el header, para poder autenticarse al back
  // Tener un interceptor permite que no tengamos que añadir el header en todas las peticiones que tenemos en cada service
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const token: string = this.cookieService.get('token');
    let req = request;

    if(token){
      req = request.clone({
        setHeaders: {
          authorization: `Bearer ${token}`
        }
      })
    }
    return next.handle(req);
  }
}
