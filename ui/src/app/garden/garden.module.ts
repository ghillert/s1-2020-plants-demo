import { NgModule, Injector } from '@angular/core';
import { GardenRoutingModule } from './garden-routing.module';
import { GardenComponent } from './garden.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { LeafletMarkerClusterModule } from '@asymmetrik/ngx-leaflet-markercluster';
import { PopupComponent } from './popup.component';
import { createCustomElement } from '@angular/elements';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    GardenComponent,
    PopupComponent
  ],
  entryComponents: [PopupComponent],
  imports: [
    SharedModule,
    GardenRoutingModule,
    LeafletModule,
    LeafletMarkerClusterModule
  ]
})
export class GardenModule { 

  constructor(private injector: Injector) {
    const PopupElement = createCustomElement(PopupComponent, {injector});
    // Register the custom element with the browser.
    customElements.define('popup-element', PopupElement);
  }
}
