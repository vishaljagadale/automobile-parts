import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ProductsListComponent } from './products-list.component';
import { ProductAddComponent } from './product-add.component';

@NgModule({
  declarations: [
    ProductsListComponent,
    ProductAddComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild([
      { path: '', component: ProductsListComponent },
      { path: 'add', component: ProductAddComponent }
    ])
  ]
})
export class ProductsModule {}