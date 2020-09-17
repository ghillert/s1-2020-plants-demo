import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClarityModule } from '@clr/angular';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [],
  imports: [
    ClarityModule,
    CommonModule,
    HttpClientModule
  ],
  exports: [
    ClarityModule,
    CommonModule,
    HttpClientModule
  ]
})
export class SharedModule { }
