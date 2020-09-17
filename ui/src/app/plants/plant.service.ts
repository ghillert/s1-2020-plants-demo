import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Page } from '../shared/model/page';
import { Plant } from '../shared/model/plant.model';

@Injectable({
  providedIn: 'root'
})
export class PlantService {

  public taxaUrl = '/api/plants';
  public taxaSubject$ = new BehaviorSubject<Plant | null>(null);

  constructor(private httpClient: HttpClient) { }

  getPlants(page: number, size: number): Observable<Page<Plant>> {
    const params = new HttpParams()
      .append('page', page.toString())
      .append('size', size.toString());

    return this.httpClient
    
        .get<any>(this.taxaUrl, { params })
        .pipe(
          map(Plant.pageFromJSON),
        );
  }

  getPlant(id: number): Observable<Plant> {
    const params = new HttpParams()
      .append('radius', '20');

    return this.httpClient
    
        .get<any>(this.taxaUrl + `/${id}`, { params })
        .pipe(
          map(Plant.fromJSON),
        );
  }
}
