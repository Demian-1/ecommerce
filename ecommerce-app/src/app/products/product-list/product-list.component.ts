import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../service/category.service';
import { Category } from '../../model/Category';
import { Product } from '../../model/Product';
import { ProgressSpinnerModule } from 'primeng/progressspinner'; 
import { ProductItemComponent } from '../product-item/product-item.component';
import { DropdownModule } from 'primeng/dropdown';
import { CardModule } from 'primeng/card';
import { PanelModule } from 'primeng/panel';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ProductsService } from '../../service/products.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    ProgressSpinnerModule,
    ProductItemComponent,
    DropdownModule,
    CardModule,
    PanelModule,
    FormsModule,
    CommonModule
  ],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  constructor(
    private categoryService: CategoryService,
    private productService: ProductsService // Inject the product service
  ){}

  categories: Category[] = [];
  selectedCategory: Category | null = null;
  products: Product[] = []; // Add products array
  loading: boolean = true;

  ngOnInit() {
    this.categoryService.getCategories().subscribe(
      (data: Category[]) => {
        this.categories = [{ id: 0, name: 'Todos', products: [] }, ...data]; // Add "Todos" option with empty products array
        this.selectedCategory = this.categories[0]; // Set default category to "Todos"
        this.loadProducts(); // Load all products initially
        this.loading = false;
      },
      (error) => {
        console.error('Error fetching categories', error);
        this.loading = false;
      }
    );
  }

  onCategoryChange() {
    this.loadProducts();
  }

  loadProducts() {
    this.loading = true;
    if (this.selectedCategory?.id === 0) {
      this.productService.getProducts().subscribe(
        (data: Product[]) => {
          this.products = data;
          this.loading = false;
        },
        (error) => {
          console.error('Error fetching all products', error);
          this.loading = false;
        }
      );
    } else {
      this.categoryService.getProductsByCategory(this.selectedCategory!.id).subscribe(
        (data: Category) => {
          this.products = data.products || [];
          this.loading = false;
        },
        (error) => {
          console.error('Error fetching products for category', error);
          this.loading = false;
        }
      );
    }
  }
}
