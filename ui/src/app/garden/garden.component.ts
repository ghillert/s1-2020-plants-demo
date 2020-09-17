import { Component, OnInit } from '@angular/core';
import { tileLayer, latLng, geoJSON, CircleMarker, markerClusterGroup, MarkerOptions, icon, GeoJSONOptions, marker } from 'leaflet';
import 'leaflet.markercluster';
import { GardenService } from '../garden/garden.service';
import { Router } from '@angular/router';
import "leaflet/dist/images/marker-shadow.png";
import "leaflet/dist/images/marker-icon-2x.png";
import { NgElement, WithProperties } from '@angular/elements';
import { PopupComponent } from './popup.component';
import { LeafletLayersControlDirective, LeafletControlLayersConfig } from '@asymmetrik/ngx-leaflet';

@Component({
  selector: 'app-garden',
  templateUrl: './garden.component.html',
  styleUrls: ['./garden.component.scss']
})
export class GardenComponent implements OnInit {

  public options: any;
  public show = false;
  public layers = [];
  constructor(
    private gardenService: GardenService,
    private router: Router) { }

  public layersControl: LeafletControlLayersConfig = {
    baseLayers: {},
    overlays: {}
  };

  ngOnInit(): void {
    this.options = {
      layers: [
        tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 25, attribution: '...' })
      ],
      zoom: 15,
      center: latLng(19.657449, -155.949777)
    };

    this.gardenService.getGeoJson().subscribe(geojson => this.handleGeoJson(geojson));
  }

  private handleGeoJson(geojson: any) {
    let geojsonLayer = this.createGeoJSONLayer(geojson);
    let markers = markerClusterGroup();
    markers.addLayer(geojsonLayer);
//https://stackoverflow.com/questions/60433277/how-to-make-baselayers-overlays-selected-by-default-using-asymmetrik-ngx-leaf

    this.layersControl = {
      baseLayers: {
        'QGIS': tileLayer('/map-tiles/{z}/{x}/{y}.png', {
                  attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
                  maxZoom: 23
                }),
        'Open Street Map': tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 23, attribution: '...' }),
        'Open Cycle Map': tileLayer('http://{s}.tile.opencyclemap.org/cycle/{z}/{x}/{y}.png', { maxZoom: 23, attribution: '...' })
      },
      overlays: {
        'Plants': geojsonLayer,
        'Plants Clustered': markers
      }
    };
    this.show = true;
  }

  private createGeoJSONLayer(geojson: any) {
      const geojsonMarkerOptions : MarkerOptions = {
        icon: icon({
          iconSize: [ 25, 41 ],
          iconAnchor: [ 13, 41 ],
          iconUrl: 'assets/marker-icon.png',
          shadowUrl: 'assets/marker-shadow.png'
        })
      };
      const geojsonOptions : GeoJSONOptions = {
        onEachFeature: function (feature, layer) {
          layer.bindPopup( fl => {
            const popupEl: NgElement & WithProperties<PopupComponent> = document.createElement('popup-element') as any;
            // Listen to the close event
            popupEl.addEventListener('closed', () => document.body.removeChild(popupEl));
            popupEl.message = `${feature.properties.name}`;
            // Add to the DOM
            document.body.appendChild(popupEl);
            return popupEl;
          });
        },
        pointToLayer: function(geoJsonPoint, latlng) {
          return marker(latlng, geojsonMarkerOptions);
        }
      }
      return geoJSON(geojson, geojsonOptions);
  }
  
  public onMapReady(map: L.Map) {
    let marker = new CircleMarker(latLng(19.657449, -155.949777), {
      color: 'green',
      radius: 1,
    })

    marker.bindPopup(fl => this.createPopupComponentWithMessage('test popup'));
    marker.addTo(map);
  }

  public createPopupComponentWithMessage(message: any) {
    const popupEl: NgElement & WithProperties<PopupComponent> = document.createElement('popup-element') as any;
    // Listen to the close event
    popupEl.addEventListener('closed', () => document.body.removeChild(popupEl));
    popupEl.message = message;
    // Add to the DOM
    document.body.appendChild(popupEl);
    return popupEl;
  }
}
