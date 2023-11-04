import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { SharedDataService } from '../../../core/service/shared-data.service';

@Component({
    selector: 'map-dialogF',
    templateUrl: './map-dialogF.component.html',
    styleUrls: ['./map-dialogF.component.scss']
})

export class MapDialogF {
    constructor(public sharedDataService: SharedDataService) { }
}