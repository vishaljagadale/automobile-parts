import { Component, OnInit } from '@angular/core';
import { OrdersService } from './orders.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent implements OnInit {
  orders: any[] = [];
  loading = true;
  error = '';
  searchTerm: string = '';
  sortBy: string = 'id';
  currentPage: number = 1;
  pageSize: number = 8;

  constructor(private ordersService: OrdersService) {}

  ngOnInit(): void {
    this.ordersService.getOrders().subscribe({
      next: (data: any) => {
        this.orders = data;
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Failed to load orders';
        this.loading = false;
      }
    });
  }

  get filteredOrders(): any[] {
    let filtered = this.orders.filter(o =>
      o.id.toString().includes(this.searchTerm) ||
      o.status.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
    filtered = filtered.sort((a, b) => {
      if (this.sortBy === 'total') {
        return a.total - b.total;
      }
      return a.id - b.id;
    });
    return filtered;
  }

  get paginatedOrders(): any[] {
    const start = (this.currentPage - 1) * this.pageSize;
    return this.filteredOrders.slice(start, start + this.pageSize);
  }

  get totalPages(): number {
    return Math.ceil(this.filteredOrders.length / this.pageSize);
  }

  setPage(page: number) {
    this.currentPage = page;
  }
}