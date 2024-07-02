import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { ShoppingCartItem, ShoppingCartService } from '../service/shoppingcart.service';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { InputNumberModule } from 'primeng/inputnumber';
import { MessageService } from 'primeng/api';
import { TableModule } from 'primeng/table';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-shopping-cart',
  standalone: true,
  imports: [CommonModule, FormsModule, CardModule, ButtonModule, ToastModule, InputNumberModule, TableModule, ConfirmDialogModule],
  providers: [MessageService, ConfirmationService],
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  cartItems: ShoppingCartItem[] = [];
  total: number = 0;

  constructor(
    private shoppingCartService: ShoppingCartService,
    private authService: AuthService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    const userId = this.authService.getUserId();
    if (userId) {
      this.shoppingCartService.getShoppingCartItems(userId).subscribe((items: ShoppingCartItem[]) => {
        this.cartItems = items.map(item => ({
          ...item,
          totalPrice: item.product.price * item.qty
        }));
        this.total = this.cartItems.reduce((sum, item) => sum + item.totalPrice!, 0);
      });
    }
  }

  updateQuantity(item: ShoppingCartItem, qty: number): void {
    if (qty < 1) return; // Evita que la cantidad sea menor a 1
    item.qty = qty;
    item.totalPrice = item.product.price * item.qty;
    this.total = this.cartItems.reduce((sum, item) => sum + item.totalPrice!, 0);
    this.shoppingCartService.updateItemQuantity(item.id!, qty).subscribe(() => {
      this.messageService.clear(); // Limpia mensajes anteriores
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Cantidad actualizada!' });
    });
  }

  confirmRemoveItem(itemId: number): void {
    this.confirmationService.confirm({
      message: '¿Estás seguro de que deseas eliminar este artículo?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.removeItem(itemId);
      }
    });
  }

  removeItem(itemId: number): void {
    this.shoppingCartService.removeItemFromCart(itemId).subscribe(() => {
      this.cartItems = this.cartItems.filter(item => item.id !== itemId);
      this.total = this.cartItems.reduce((sum, item) => sum + item.totalPrice!, 0);
      this.messageService.clear(); // Limpia mensajes anteriores
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Artículo eliminado!' });
    });
  }

  checkout(): void {
    this.messageService.clear(); // Limpia mensajes anteriores
    this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Compra realizada con éxito!' });
  }
}
