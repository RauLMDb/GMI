import { Component } from '@angular/core';
import { ApiService } from './core/service/api.service';
import { SharedDataService } from './core/service/shared-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'calculator';

  constructor( private sharedDataService: SharedDataService, private api: ApiService ) {}

  ngOnInit(): void {
    const cookies : { linkSend: String, linkReturn: String, direccion: string, municipio: string } = JSON.parse(this.api.getCookie('tokenCiudadano'));

    console.log(cookies)

    if ( cookies == null ) window.location.href = "https://skrm.ismigit.fi.upm.es/admin-fe/";

    this.sharedDataService.linkSend   = cookies.linkSend   || '#';
    this.sharedDataService.linkReturn = cookies.linkReturn || '#';
    this.sharedDataService.direccion  = cookies.direccion  || '';
    this.sharedDataService.municipio  = cookies.municipio  || '';
  }
}
