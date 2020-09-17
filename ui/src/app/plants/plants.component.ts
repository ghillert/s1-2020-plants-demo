import { Component, OnInit, OnDestroy } from '@angular/core';
import { Page } from '../shared/model/page';
import { Plant } from '../shared/model/plant.model';
import { PlantService } from './plant.service';
import { Subject, combineLatest, timer } from 'rxjs';
import { Router } from '@angular/router';
import { takeUntil, map, finalize } from 'rxjs/operators';
import { ClrDatagridStateInterface } from '@clr/angular';

@Component({
  selector: 'app-plants',
  templateUrl: './plants.component.html',
  styleUrls: ['./plants.component.scss']
})
export class PlantsComponent implements OnInit, OnDestroy {

  /**
   * Current collection of plants
   */
  plants = new Page<Plant>();
  loading = true;

  /**
   * Unsubscribe
   */
  private ngUnsubscribe$: Subject<any> = new Subject();

  constructor(
    private plantService: PlantService,
    private router: Router) { }

  ngOnInit(): void {
  }

  ngOnDestroy() {
    this.ngUnsubscribe$.next();
    this.ngUnsubscribe$.complete();
  }

  /**
   * Load a paginated list of {@link Plant}s.
   */
  loadPlants(page?: number, size?: number) {
    if (page && size) {
      combineLatest(timer(500), this.plantService.getPlants(page, size))
      .pipe(
        takeUntil(this.ngUnsubscribe$),
        map(x => x[1]),
        finalize(() => (this.loading = false))
      )
      .subscribe((page: Page<Plant>) => {
        this.plants = page;
        console.log(`Got ${this.plants.items.length}.`);
      },
      error => {
        console.log(error);
      });
    }
  }

  refresh(state: ClrDatagridStateInterface) {
    if (state.page?.current != undefined && state.page?.size != undefined) {
      this.loadPlants(state.page.current, state.page?.size);
    }
  }

  loadDetails(taxonId?: number) {
    if (taxonId) {
      console.log('Go to Add Application page ...');
      this.router.navigate(['/plants', taxonId]);
    }
  }
}
