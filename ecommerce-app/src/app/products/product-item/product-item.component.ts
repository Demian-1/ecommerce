import { Component, Input } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { Product } from '../../model/Product';
import { AuthService } from '../../service/auth.service';
import { ShoppingCartService } from '../../service/shoppingcart.service';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-item',
  standalone: true,
  imports: [
    CardModule,
    ButtonModule,
    InputNumberModule,
    FormsModule,
    CommonModule
  ],
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent {  
  @Input() product = new Product();
  userId: number | null = null;
  qty = 1;

  constructor(
    private authService: AuthService,
    private shoppingCartService: ShoppingCartService,
  ) { }

  ngOnInit() {
    this.userId = this.authService.getUserId();
  }

  substractQty() {
    if (this.qty > 1) {
      this.qty = this.qty - 1;
    }
  }

  isUserLoggedIn(): boolean {
    return this.authService.isAuthenticated();
  }

  addToCart(idProducto: number) {
    if (this.userId) {
      this.shoppingCartService.getCartByUserId(this.userId).subscribe(cart => {
        const existingItem = cart.items?.find(item => item.product.id === idProducto);
        if (existingItem) {
          const newQty = existingItem.qty + this.qty;
          this.shoppingCartService.updateItemQuantity(existingItem.id!, newQty).subscribe(() => {
            alert("Cantidad actualizada con éxito");
          });
        } else {
          this.shoppingCartService.addProductToCart(idProducto, this.userId!, this.qty).subscribe(() => {
            alert("Agregado con éxito");
          });
        }
      });
    }
  }
}
