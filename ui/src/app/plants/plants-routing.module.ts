import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PlantsComponent } from './plants.component';
import { PlantDetailsComponent } from './plant-details/plant-details.component';

const routes: Routes = [
  { path: '', component: PlantsComponent },
  { path: ':id', component: PlantDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlantsRoutingModule { }
