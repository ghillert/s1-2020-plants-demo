import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { PlantsComponent } from './plants.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'
import { PlantService } from './plant.service';

describe('PlantsComponent', () => {
  let component: PlantsComponent;
  let fixture: ComponentFixture<PlantsComponent>;

  let httpMock: HttpTestingController;
  
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [ PlantsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   inject([HttpTestingController],
  //     (httpMock: HttpTestingController, service: PlantService) => {

  //     const req = httpMock.expectOne('/api/plantswwwww');
  //     expect(req.request.method).toEqual('GET');
  //     // Then we set the fake data to be returned by the mock
  //     req.flush({data: 
  //       {foo: "dddd"}
  //     });
  //     const req2 = httpMock.expectOne('/api/plants/geojson');
  //     expect(req2.request.method).toEqual('GET');
  //     // Then we set the fake data to be returned by the mock
  //     req2.flush({data: 
  //       {foo: "dddd"}
  //     });

  //       expect(component).toBeTruthy();
  //     });
  //   });
});
