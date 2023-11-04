import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { SharedDataService } from '../../../core/service/shared-data.service';

@Component({
    selector: 'map-dialogInstrucciones',
    templateUrl: './map-dialogInstrucciones.component.html',
    styleUrls: ['./map-dialogInstrucciones.component.scss']
})

export class MapDialogInstruccionees {
    constructor(public sharedDataService: SharedDataService, public dialogRef: MatDialogRef<MapDialogInstruccionees>) { }
}