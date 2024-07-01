import { Component } from '@angular/core';
import { CategoryService } from '../../service/category.service';
import { Category } from '../../model/Category';
import { ProgressSpinnerModule } from 'primeng/progressspinner'; 
import { ProductItemComponent } from '../product-item/product-item.component';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    ProgressSpinnerModule,
    ProductItemComponent
  ],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent {
  constructor(
    private categoryService: CategoryService
  ){}

  categories: Category[] = [];
  loading: boolean = true; // Add loading state

  ngOnInit() {
    this.categoryService.getCategories().subscribe(
      (data: Category[]) => {
        this.categories = data;
        this.loading = false; // Set loading to false after data is loaded
      },
      (error) => {
        console.error('Error fetching categories', error);
        this.loading = false; // Set loading to false if there's an error
      }
    );
  }
}
