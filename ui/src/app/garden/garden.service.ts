import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Page } from '../shared/model/page';
import { Plant } from '../shared/model/plant.model';

@Injectable({
  providedIn: 'root'
})
export class GardenService {

  public geoJsonUrl = '/api/plants/geojson';
  public taxaSubject$ = new BehaviorSubject<Plant | null>(null);

  constructor(private httpClient: HttpClient) { }

  getGeoJson(): Observable<any> {
    const params = new HttpParams();

    return this.httpClient
    
        .get<any>(this.geoJsonUrl, { params });
        // .pipe(
        //   map(Plant.pageFromJSON),
        // );
  }
}
