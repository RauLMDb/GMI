import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { MapComponent } from './m-ciudadano/map.component';
import { ProgressBarComponent } from './components/shared/progress-bar/progress-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { Map2Component } from './m-tramitador/map2.component';
import { Map3Component } from './m-supervisor/map3.component';

@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    ProgressBarComponent,
    Map2Component,
    Map3Component
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, BrowserAnimationsModule, MatDialogModule, MatButtonModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
