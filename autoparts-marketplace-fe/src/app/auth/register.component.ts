import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { ApiService } from '../core/api.service';
import { Router } from '@angular/router';
import { User, Address, SellerProfile } from '../core/models';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  error: string = '';
  backendErrors: { [key: string]: string } = {};

  roles = [
    { value: 'BUYER', label: 'Buyer' },
    { value: 'SELLER', label: 'Seller' },
    { value: 'ADMIN', label: 'Admin' }
  ];

  constructor(private fb: FormBuilder, private api: ApiService, private router: Router) {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
      password: ['', [Validators.required, Validators.minLength(6), this.passwordComplexityValidator]],
      email: ['', [Validators.required, Validators.email]],
      role: ['BUYER', Validators.required],
      street: [''],
      city: [''],
      state: [''],
      zip: [''],
      country: [''],
      companyName: [''],
      gstNumber: ['']
    });
  }

  passwordComplexityValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (!value) return null;
    const hasLetter = /[A-Za-z]/.test(value);
    const hasNumber = /[0-9]/.test(value);
    if (!hasLetter || !hasNumber) {
      return { complexity: true };
    }
    return null;
  }

  onSubmit(): void {
    console.log('Register form submitted', this.registerForm.value);
    if (this.registerForm.invalid) {
      this.error = '';
      this.backendErrors = {};
      this.registerForm.markAllAsTouched();
      return;
    }
    this.error = '';
    this.backendErrors = {};
    const userPayload = {
      username: this.registerForm.value.username,
      password: this.registerForm.value.password,
      email: this.registerForm.value.email,
      roles: [this.registerForm.value.role],
      enabled: true
    };

    // Use postText because backend returns plain text ("User registered successfully")
    this.api.postText('/auth/register', userPayload).subscribe({
      next: (res: string) => {
        console.log('Register success text response:', res);
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('Register error:', err);
        if (err.error && err.error.field && err.error.message) {
          if (err.error.field === 'general') {
            this.error = err.error.message;
          } else {
            this.backendErrors[err.error.field] = err.error.message;
          }
        } else {
          this.error = 'Registration failed. Please try again.';
        }
      }
    });
  }
}