import { Component } from '@angular/core';
import { SharedDataService } from '../../../core/service/shared-data.service';
import { ApiService } from 'src/app/core/service/api.service';

@Component({
    selector: 'map-dialogT',
    templateUrl: './map-dialogT.component.html',
    styleUrls: ['./map-dialogT.component.scss']
})

export class MapDialogT {
    presupuesto!: string;
  
    constructor( public sharedDataService: SharedDataService, private api: ApiService ) { }
  
    ngOnInit(): void {
        this.presupuesto = new Intl.NumberFormat('es-ES', { style: 'currency', currency: 'EUR' }).format(this.sharedDataService.presupuesto.valueOf())
    
        const btnSend = document.querySelector('.btn-send');
    
        if (btnSend) {
            btnSend.addEventListener('click', () => {
            send();
            });
        }
    
        const send = () => {
            const obj = {
            "area": this.sharedDataService.area, 
            "presupuesto": this.sharedDataService.presupuesto, 
            "coordinates": this.sharedDataService.coords
            }
    
            this.api.setCookie("tokenMapa", JSON.stringify(obj));
            window.location.href = this.sharedDataService.linkSend as string;
        }
    }
}
  