import { Component, OnInit } from '@angular/core';
import { Plant } from 'src/app/shared/model/plant.model';
import { Router, ActivatedRoute } from '@angular/router';
import { PlantService } from '../plant.service';

@Component({
  selector: 'app-plant-details',
  templateUrl: './plant-details.component.html',
  styleUrls: ['./plant-details.component.scss']
})
export class PlantDetailsComponent implements OnInit {

  public plant = new Plant();

  constructor(
    private plantService: PlantService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params
      .subscribe(params => {
        this.plant.id = params.id as number;
        this.plantService.getPlant(this.plant.id).subscribe(plant => {
          this.plant = plant;
        });
    });
  }

  goBack() {
    this.router.navigate(['/plants']);
  }
}
