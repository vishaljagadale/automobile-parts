import { Component, OnInit } from '@angular/core';
import { UsersService } from './users.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  users: any[] = [];
  loading = true;
  error = '';
  searchTerm: string = '';
  sortBy: string = 'username';
  currentPage: number = 1;
  pageSize: number = 8;

  constructor(private usersService: UsersService) {}

  ngOnInit(): void {
    this.usersService.getUsers().subscribe({
      next: (data: any) => {
        this.users = data;
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Failed to load users';
        this.loading = false;
      }
    });
  }

  get filteredUsers(): any[] {
    let filtered = this.users.filter(u =>
      u.username.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      u.email.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      u.role.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
    filtered = filtered.sort((a, b) => {
      if (this.sortBy === 'role') {
        return a.role.localeCompare(b.role);
      }
      return a.username.localeCompare(b.username);
    });
    return filtered;
  }

  get paginatedUsers(): any[] {
    const start = (this.currentPage - 1) * this.pageSize;
    return this.filteredUsers.slice(start, start + this.pageSize);
  }

  get totalPages(): number {
    return Math.ceil(this.filteredUsers.length / this.pageSize);
  }

  setPage(page: number) {
    this.currentPage = page;
  }
}