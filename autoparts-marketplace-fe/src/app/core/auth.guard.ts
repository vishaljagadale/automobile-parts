import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRoles = route.data['role'];
    const token = this.authService.getToken();
    const userRole = this.authService.getRole();
    if (token && (!expectedRoles || (Array.isArray(expectedRoles) ? expectedRoles.includes(userRole) : userRole === expectedRoles))) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}