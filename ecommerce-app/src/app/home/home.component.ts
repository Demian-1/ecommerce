import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ButtonModule, CardModule, ToastModule],
  providers: [MessageService],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  products: any[] = [
    { name: 'Product 1', description: 'Description for Product 1', price: 29.99 },
    { name: 'Product 2', description: 'Description for Product 2', price: 39.99 },
    { name: 'Product 3', description: 'Description for Product 3', price: 49.99 },
  ];

  constructor(private messageService: MessageService) {}

  addToCart(product: any) {
    this.messageService.add({severity:'success', summary: 'Added to Cart', detail: `${product.name} has been added to your cart.`});
  }
}
