import { Component, Input } from '@angular/core';
import { CardModule } from 'primeng/card';
import { Button } from 'primeng/button';
import { Product } from '../../model/Product';

@Component({
  selector: 'app-product-item',
  standalone: true,
  imports: [
    CardModule,
    Button
  ],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.css'
})
export class ProductItemComponent {
  @Input() product = new Product();

  addToCart(id: number){
    alert("Added to cart product: "+id);
  }

}
