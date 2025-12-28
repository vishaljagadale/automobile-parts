import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OrdersListComponent } from './orders-list.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    OrdersListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild([
      { path: '', component: OrdersListComponent }
    ])
  ]
})
export class OrdersModule {}