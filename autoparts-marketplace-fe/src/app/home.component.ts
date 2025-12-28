import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  dummyParts = [
    { name: 'Brake Pad Set', vehicle: 'Maruti Suzuki Swift', price: 1499, tag: 'Top Seller' },
    { name: 'Engine Oil 5W-30', vehicle: 'Hyundai i20 / Creta', price: 1899, tag: 'Recommended' },
    { name: 'Air Filter', vehicle: 'Tata Nexon', price: 799, tag: 'New' },
    { name: 'Clutch Plate Kit', vehicle: 'Mahindra Bolero', price: 3499, tag: 'OEM Quality' }
  ];
}
