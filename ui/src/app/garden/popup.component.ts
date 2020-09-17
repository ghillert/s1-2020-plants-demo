import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-popup',
  template: '<p> {{ message }}</p>',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent implements OnInit {

  @Input() message = 'Default Pop-up Message!';

  constructor() { }

  ngOnInit() {
      console.log('message:', this.message)
  }

}