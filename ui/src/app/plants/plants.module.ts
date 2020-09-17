import { NgModule } from '@angular/core';
import { PlantsRoutingModule } from './plants-routing.module';
import { PlantsComponent } from './plants.component';
import { PlantDetailsComponent } from './plant-details/plant-details.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [PlantsComponent, PlantDetailsComponent],
  imports: [
    SharedModule,
    PlantsRoutingModule
  ]
})
export class PlantsModule { }
