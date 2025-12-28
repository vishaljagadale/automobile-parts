// Shared DTO interfaces for strict typing and payload completeness

export interface Address {
  street: string;
  city: string;
  state: string;
  zip: string;
  country: string;
}

export interface User {
  id?: number;
  username: string;
  email: string;
  password?: string;
  role: 'BUYER' | 'SELLER' | 'ADMIN';
  address?: Address;
  sellerProfile?: SellerProfile;
}

export interface SellerProfile {
  companyName: string;
  gstNumber: string;
  kycStatus?: string;
}

export interface Product {
  id?: number;
  name: string;
  description: string;
  categoryId: number;
  subCategoryId: number;
  brandId: number;
  vehicleTypeId: number;
  model: string;
  year: number;
  price: number;
  inventory: number;
  images: string[];
  specifications: any;
  sku?: string;
  status?: string;
}

export interface CartItem {
  productId: number;
  quantity: number;
}

export interface Order {
  id?: number;
  buyerId: number;
  items: OrderItem[];
  address: Address;
  paymentId?: number;
  status?: string;
}

export interface OrderItem {
  productId: number;
  quantity: number;
  price: number;
}

export interface Review {
  id?: number;
  productId: number;
  buyerId: number;
  rating: number;
  comment: string;
  sellerId?: number;
  status?: string;
}
