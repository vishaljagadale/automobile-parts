import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  error: string = '';

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const payload = {
        username: this.loginForm.value.username,
        password: this.loginForm.value.password,
        email: '', // not used for login
        roles: [], // not used for login, but required by backend DTO
      };
      this.auth.login(payload).subscribe({
        next: () => this.router.navigate(['/products']),
        error: (err: any) => this.error = 'Invalid credentials'
      });
    }
  }
}