import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import * as mapboxgl from 'mapbox-gl';
import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import MapboxDraw from '@mapbox/mapbox-gl-draw';
import * as turf from '@turf/turf';
import { FeatureCollection, Polygon } from 'geojson';
import { ApiService } from '../core/service/api.service';
import { MatDialog } from '@angular/material/dialog';
import { SharedDataService } from '../core/service/shared-data.service';
import axios from 'axios';

import { MapDialogInstruccionees } from '../components/dialogs/dialogInstrucciones/map-dialogInstrucciones.component';
import { MapDialogT } from '../components/dialogs/dialogT/map-dialogT.component';
import { MapDialogF } from '../components/dialogs/dialogF/map-dialogF.component';

@Component({
  selector: 'app-map2',
  templateUrl: './map2.component.html',
  styleUrls: ['./map2.component.scss']
})


export class Map2Component implements OnInit {
  @ViewChild('mapContainer', { static: true }) mapContainer!: ElementRef;
  map!: mapboxgl.Map;
  roundedArea!: number;

  constructor( public apiService: ApiService, public dialog: MatDialog, public sharedDataService: SharedDataService, private api: ApiService ) {
    this.roundedArea = 0;
  }

  ngOnInit(): void {

    this.map = new mapboxgl.Map({
      accessToken: 'pk.eyJ1IjoibWVuY2lhIiwiYSI6ImNsZjEweXVsNjAzeXIzcXM5azFqMjk0ZjMifQ.hc_d2rsywhp29WXrLAz9Aw',
      container: this.mapContainer.nativeElement,
      style: 'mapbox://styles/mapbox/satellite-streets-v12',
      center: [-3.7, 40.2],
      zoom: 11,
      maxBounds: [
        [
          -5.001472937182655,
          39.66718156208276
        ], // Southwest coordinates
        [
          -2.8859631358626814,
          41.943656520135676
        ] // Northeast coordinates
      ] // Sets bounds as max
    });

    //botones de control necesarios, zoom in y out
    const geocoder = new MapboxGeocoder({
      accessToken: 'pk.eyJ1IjoibWVuY2lhIiwiYSI6ImNsZjEweXVsNjAzeXIzcXM5azFqMjk0ZjMifQ.hc_d2rsywhp29WXrLAz9Aw',
      language: 'es-ES', 
      mapboxgl: mapboxgl,
      placeholder: 'Buscar', 
      marker: true,
      zoom: 14
    });

    const searchLocation = async (query: string) => {
      let query_new = `https://api.mapbox.com/geocoding/v5/mapbox.places/${encodeURIComponent(query)}.json?access_token=pk.eyJ1IjoibWVuY2lhIiwiYSI6ImNsZjEweXVsNjAzeXIzcXM5azFqMjk0ZjMifQ.hc_d2rsywhp29WXrLAz9Aw&limit=1`;
      console.log(query_new)
      const response = await axios.get(query_new);
    
      if (response.data.features.length > 0) {
        console.log(response.data.features[0])
        const center = response.data.features[0].center;
        return center;
      } else {
        console.error('No se encontró la localidad');
        return null;
      }
    }

  

    this.map.addControl(geocoder, 'top-left');

    this.map.addControl(new mapboxgl.NavigationControl(), 'top-left');

    //botones para el polígono
    // const draw = new MapboxDraw({
    //   displayControlsDefault: false,
    //   controls: {
    //     polygon: true,
    //     trash: true
    //   },
    //   defaultMode: 'draw_polygon'
    // });

    // this.map.addControl(draw, 'top-left');

    // Función de instrucciones
    const nopens = this.api.getCookie("mapOpened");
    if (nopens === "" || nopens === 0) {
      this.dialog.open(MapDialogInstruccionees);
      this.api.setCookie("mapOpened", 1)
    }

    // const updateArea = (e: any) => {
    //   const data = draw.getAll();
    //   if (data.features.length > 0) {
    //     const area = turf.area(data);

    //     this.roundedArea = Math.round(area);
    //   }

    //   // Si se ha creado un polígono, trasladar el botón de validar
    //   if (e.type == 'draw.create') {
    //     const btnDraw = document.querySelector('.mapbox-gl-draw_polygon') as HTMLElement;
    //     const btnValidate = document.querySelector('.btn-validate');
    //     const btnVolver = document.querySelector('.btn-volver');

    //     if (btnValidate && btnVolver && btnDraw) {
    //       btnVolver?.classList.add('mover');
    //       btnDraw.style.display = 'none';
    //       btnValidate.classList.remove('collapsed');
    //     }
    //   }

    //   // Si se ha eliminado, colapsar el botón de validar
    //   if (e.type == 'draw.delete') {
    //     const btnDraw = document.querySelector('.mapbox-gl-draw_polygon') as HTMLElement;
    //     const btnValidate = document.querySelector('.btn-validate');
    //     const btnVolver = document.querySelector('.btn-volver');

    //     if (btnValidate && btnVolver) {
    //       btnDraw.style.display = 'block';
    //       btnVolver.classList.remove('mover');
    //       btnValidate.classList.add('collapsed');
    //     }
    //   }
    // }

    // this.map.on('draw.create', updateArea);
    // this.map.on('draw.delete', updateArea);
    // this.map.on('draw.update', updateArea);

    function getCoordinates(featureCollection: FeatureCollection): number[][][] {
      const polygon = featureCollection.features[0].geometry as Polygon;
      return polygon.coordinates;
    }


    // const btnValidate = document.querySelector('.btn-validate');
    const btnReturn = document.querySelector('.btn-volver');
    // const btnInstrucciones = document.querySelector('.but-progress');

    // if (btnValidate) {
    //   btnValidate.addEventListener('click', () => {
    //     validatePolygon();
    //   });
    // }

    // if (btnInstrucciones) {
    //   btnInstrucciones.addEventListener('click', () => {
    //     this.dialog.open(MapDialogInstruccionees);
    //   });
    // }

    if (btnReturn) {
      btnReturn.addEventListener('click', () => {
        volver();
      });
    }

    // const validatePolygon = async () => {
    //   const data = draw.getAll();
    //   let polygon = data.features.find((f: any) => f.geometry.type === "Polygon");

    //   if (!polygon) {
    //     alert("No se ha dibujado un polígono");
    //   } else if (polygon) {
    //     const coords = getCoordinates(data);
    //     var coordinates: Array<string> = []
    //     for (let i = 0; i < coords.length; i++) coordinates.push(JSON.stringify(coords[i]));
    //     let response: any = await new Promise(resolve => {
    //       this.apiService.validar(coordinates).subscribe((res: any) => {
    //         resolve(res);
    //       });
    //     });

    //     this.sharedDataService.validated = response.valido;
    //     this.sharedDataService.area = response.area;
    //     this.sharedDataService.presupuesto = response.presupuesto;

    //     response = await new Promise(resolve => {
    //       this.apiService.calcSuperficie(coordinates).subscribe((res: any) => {
    //         resolve(res);
    //       });
    //     });

    //     this.sharedDataService.coords = response.coordinates;

    //     if (this.sharedDataService.validated) {
    //       const dialogRef = this.dialog.open(MapDialogT);

    //       dialogRef.afterClosed().subscribe(result => {
    //         console.log(`Dialog result: ${result}`);
    //       });
    //     } else {
    //       const dialogRef = this.dialog.open(MapDialogF);

    //       dialogRef.afterClosed().subscribe(result => {
    //         console.log(`Dialog result: ${result}`);
    //       });
    //     }
    //   }
    // }

    const volver = async () => {
      window.location.href = this.sharedDataService.linkReturn as string; 
    }

    function parseCoordinates(coordinatesString: string): Array<Array<number>> {
      const coordinatesArray: Array<Array<number>> = JSON.parse(coordinatesString);

      return coordinatesArray;
    }

    function createGeoJSONPolygon(coordinatesArray: Array<Array<number>>): any {
      return {
        type: 'geojson',
        data: {
          type: 'FeatureCollection',
          features: [
            {
              type: 'Feature',
              properties: {},
              geometry: {
                coordinates: [coordinatesArray],
                type: 'Polygon',
              },
              id: 0,
            },
          ],
        },
      };
    }

    function addPolygonToMap(coordinatesString: string, i: number, mapa: mapboxgl.Map) {//añadir flag para saber si tramite o ocupados y cambiar color
      // Add a data source containing GeoJSON data.
      mapa.addSource(`${i}`, createGeoJSONPolygon(parseCoordinates(coordinatesString)));

      // Add a new layer to visualize the polygon.
      mapa.addLayer({
        'id': `${i}`,
        'type': 'fill',
        'source': `${i}`, // reference the data source
        'layout': {},
        'paint': {
          'fill-color': '#ef0101', // blue color fill
          'fill-opacity': 0.5
        }
      });
      // Add a black outline around the polygon.
      mapa.addLayer({
        'id': `outline-${i}`,
        'type': 'line',
        'source': `${i}`, // reference the data source
        'layout': {},
        'paint': {
          'line-color': '#000',
          'line-width': 0.5
        }
      });
    }

    this.map.on('load', async () => {
      var coordinatesString: string[] = [];
      coordinatesString = await new Promise(resolve => {
        this.apiService.getOcupados().subscribe((res: any) => {//hacer tambien para getTramite y para getZona
          resolve(res.coordinates);
        });
      });

      /* Cambiar títulos de los botones que estaban en inglés */
      let elems = document.getElementsByClassName('mapboxgl-ctrl-icon');
      if ( elems[0] as HTMLElement ) (elems[0] as HTMLElement).title = "Más zoom";
      if ( elems[1] as HTMLElement ) (elems[1] as HTMLElement).title = "Menos zoom";
      if ( elems[2] as HTMLElement ) (elems[2] as HTMLElement).title = "Reajustar orientación norte";

      let elem = document.querySelector<HTMLElement>('.mapbox-gl-draw_trash');
      if (elem) elem.title = "Eliminar área seleccionada";
      elem = document.querySelector<HTMLElement>('.mapbox-gl-draw_polygon');
      if (elem) elem.title = "Seleccionar área";
    
      for (let i = 0; i < coordinatesString.length; i++) { addPolygonToMap(coordinatesString[i], i, this.map); }

      if (
        ( this.sharedDataService.direccion != undefined && this.sharedDataService.direccion != '' ) || 
        ( this.sharedDataService.municipio != undefined && this.sharedDataService.municipio != '' )
      ) {
        searchLocation(`
            ${ this.sharedDataService.direccion != undefined ? this.sharedDataService.direccion : '' } 
            ${ this.sharedDataService.municipio != undefined ? this.sharedDataService.municipio : '' }
          `).then((center) => {
            if (center != null) {
              this.map.setZoom(20);
              this.map.setCenter(center);
            }
          }
        );
      }
    });

    /* 
     Anadimos un eventListener para el evento 'result'
     que es el evento de cuando se busca una direccion en 
     la barra de busqueda del geocoder.

     De la feature se cogen las coordenadas de la direccion 
     buscada, lng y lat.
    */
    geocoder.on('result', (event: any) => {
      const feature = event.result.geometry;
      const lng = feature.coordinates[0];
      const lat = feature.coordinates[1];

      /*
      Una vez obtenidas las coordenadas de la direccion buscada,
      nos desplazamos (mediante this.map.flyTo() ) a las coordenadas
      en cuestiom. 
      Ademas establecemos el zoom deseado.
      */
      this.map.flyTo({
        center: [lng, lat],
        zoom: 19 // Hard-coded, probablemente haya que cambiarlo.
      });
    });



  }

  ngOnDestroy(): void {
    this.map.remove();
  }
}
