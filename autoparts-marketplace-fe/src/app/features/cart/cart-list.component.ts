import { Component, OnInit } from '@angular/core';
import { CartService } from './cart.service';
import { CommonModule } from '@angular/common';
import { Order, OrderItem, Address } from '../../core/models';
import { OrdersService } from '../orders/orders.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cart-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {
  cartItems: any[] = [];
  error: string = '';
  backendErrors: { [key: string]: string } = {};
  buyerId: number = 1; // TODO: Replace with actual logged-in user ID
  addressForm: FormGroup;
  paymentId: number = 0; // TODO: Integrate payment module
  loading: boolean = false;

  constructor(private cartService: CartService, private ordersService: OrdersService, private fb: FormBuilder) {
    this.addressForm = this.fb.group({
      street: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      zip: ['', Validators.required],
      country: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.cartService.getCartItems().subscribe({
      next: items => this.cartItems = items,
      error: err => this.error = 'Failed to load cart items'
    });
  }

  removeItem(itemId: number): void {
    this.cartService.removeCartItem(itemId).subscribe({
      next: () => this.cartItems = this.cartItems.filter(i => i.id !== itemId),
      error: err => this.error = 'Failed to remove item'
    });
  }

  checkout(): void {
    if (this.addressForm.invalid || this.cartItems.length === 0) {
      this.error = 'Please fix validation errors above and add items to cart.';
      return;
    }
    this.loading = true;
    this.error = '';
    this.backendErrors = {};
    const addressPayload = this.addressForm.value;
    const cartPayload = {
      userId: this.buyerId,
      items: this.cartItems,
      address: addressPayload
    };
    this.cartService.addToCart(cartPayload).subscribe({
      next: () => {
        this.loading = false;
        // Redirect or show success
      },
      error: (err: any) => {
        this.loading = false;
        if (err.status === 400 && err.error) {
          this.backendErrors = err.error;
        } else {
          this.error = 'Failed to place order. Please try again.';
        }
      }
    });
  }
}