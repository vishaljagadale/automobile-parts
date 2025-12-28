import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './core/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'autoparts-marketplace-fe';
  isLoggedIn = false;

  constructor(private authService: AuthService, private router: Router) {
    this.authService.isAuthenticated().subscribe(isAuth => {
      this.isLoggedIn = isAuth;
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
