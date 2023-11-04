import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteGuard } from './autorizacion/cliente.guard';
import { MapComponent } from './m-ciudadano/map.component';
import { Map2Component } from './m-tramitador/map2.component';
import { Map3Component } from './m-supervisor/map3.component';


const routes: Routes = [
  {path: '', redirectTo: '/inicio', pathMatch: 'full'},
  {path: 'tramitador', redirectTo: '/tranitador', pathMatch: 'full'},
  {path: 'supervisor', redirectTo: '/supervisor', pathMatch: 'full'},
  {path: 'inicio', component: MapComponent, canActivate: [ClienteGuard]},
  {path: 'tranitador', component: Map2Component, canActivate: [ClienteGuard]},
  {path: 'supervisor', component: Map3Component, canActivate: [ClienteGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
