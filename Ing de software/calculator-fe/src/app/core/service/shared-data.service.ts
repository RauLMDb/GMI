import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {
  public area!: Number;
  public presupuesto!: Number;
  public coords!: String[];
  public validated!: Boolean;

  public linkSend!: String;
  public linkReturn!: String;
  public direccion!: String;
  public municipio!: String;
}
