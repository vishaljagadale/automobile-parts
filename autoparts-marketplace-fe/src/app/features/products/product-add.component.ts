import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsService } from './products.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent {
  productForm: FormGroup;
  loading = false;
  error = '';
  backendErrors: { [key: string]: string } = {};

  constructor(private fb: FormBuilder, private productsService: ProductsService, private router: Router) {
    this.productForm = this.fb.group({
      sku: ['', Validators.required],
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      categoryId: [null, Validators.required],
      subCategoryId: [null, Validators.required],
      brandId: [null, Validators.required],
      vehicleTypeId: [null, Validators.required],
      model: ['', Validators.required],
      year: [new Date().getFullYear(), [Validators.required, Validators.min(1900), Validators.max(2100)]],
      price: [null, [Validators.required, Validators.min(1)]],
      approved: [false],
      specifications: [''],
      imageUrls: [''],
      quantity: [null, [Validators.required, Validators.min(1)]]
    });
  }

  addProduct() {
    if (this.productForm.invalid) {
      this.error = 'Please fix validation errors above.';
      return;
    }
    this.loading = true;
    this.error = '';
    this.backendErrors = {};
    const formValue = this.productForm.value;
    // Fix: imageUrls should be an array of strings, or omitted if blank
    let imageUrls: string[] | undefined = undefined;
    if (formValue.imageUrls && typeof formValue.imageUrls === 'string' && formValue.imageUrls.trim() !== '') {
      imageUrls = formValue.imageUrls.split(',').map((url: string) => url.trim()).filter((url: string) => url.length > 0);
    }
    // Fix: specifications is optional, send as string or omit
    let specifications: string | undefined = undefined;
    if (formValue.specifications && typeof formValue.specifications === 'string' && formValue.specifications.trim() !== '') {
      specifications = formValue.specifications;
    }
    const productPayload: any = {
      ...formValue,
      // Only include imageUrls if present and non-empty
      ...(imageUrls ? { imageUrls } : {}),
      // Only include specifications if present and non-empty
      ...(specifications ? { specifications } : {}),
    };
    this.productsService.addProduct(productPayload).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/products']);
      },
      error: (err: any) => {
        this.loading = false;
        if (err.status === 400 && err.error) {
          this.backendErrors = err.error;
        } else {
          this.error = 'Failed to add product. Please try again.';
        }
      }
    });
  }
}