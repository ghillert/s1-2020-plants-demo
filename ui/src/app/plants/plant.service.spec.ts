import { TestBed } from '@angular/core/testing';

import { PlantService } from './plant.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { Plant } from '../shared/model/plant.model';

export const mockPlants = {
  "content" : [ {
    "id" : 77,
    "genus" : "Bismarckia",
    "species" : "nobilis",
    "plantSignMissing" : false
  }, {
    "id" : 78,
    "genus" : "Metroxylon",
    "species" : "amicarum",
    "plantSignMissing" : false
  } ],
  "pageable" : {
    "sort" : {
      "sorted" : false,
      "unsorted" : true,
      "empty" : true
    },
    "pageNumber" : 0,
    "pageSize" : 20,
    "offset" : 0,
    "paged" : true,
    "unpaged" : false
  },
  "totalPages" : 1,
  "totalElements" : 2,
  "last" : true,
  "sort" : {
    "sorted" : false,
    "unsorted" : true,
    "empty" : true
  },
  "numberOfElements" : 2,
  "first" : true,
  "number" : 0,
  "size" : 2,
  "empty" : false
};

export const mockPlant = {
  "id" : 77,
  "genus" : "Bismarckia",
  "species" : "nobilis",
  "plantSignMissing" : false,
  "imageIds" : [ ],
  "plantsNearby" : [ {
    "id" : 96,
    "genus" : "Beccariophoenix",
    "species" : "alfredii",
    "distance" : 4.25,
  }, {
    "id" : 98,
    "genus" : "Sabal",
    "species" : "minor var. 'Louisiana'",
    "distance" : 6.39,
  }]
};

describe('PlantService', () => {
  let httpTestingController: HttpTestingController;
  let service: PlantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(PlantService);
  });
  afterEach(() => {
    httpTestingController.verify();
  });
  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('random should should provide data', () => {
    service.getPlant(77).subscribe((plant: Plant) => {
      expect(plant.id).toBe(77);
    });

    const req = httpTestingController
              .expectOne(`/api/plants/77?radius=20`);
    req.flush(mockPlant);
  });
});
