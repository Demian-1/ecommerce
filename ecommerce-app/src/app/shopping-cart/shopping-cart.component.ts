import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { ShoppingCartItem, ShoppingCartService } from '../service/shoppingcart.service';
import { ShopOrderService } from '../service/shop-order.service';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { InputNumberModule } from 'primeng/inputnumber';
import { MessageService } from 'primeng/api';
import { TableModule } from 'primeng/table';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DropdownModule } from 'primeng/dropdown';

@Component({
  selector: 'app-shopping-cart',
  standalone: true,
  imports: [CommonModule, FormsModule, CardModule, ButtonModule, ToastModule, InputNumberModule, TableModule, ConfirmDialogModule, DropdownModule],
  providers: [MessageService, ConfirmationService],
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  cartItems: ShoppingCartItem[] = [];
  total: number = 0;
  shippingTotal: number = 0;
  userId: number | null = null;
  defaultAddress: any = null;
  defaultPaymentMethod: any = null;
  canCheckout: boolean = false;
  errorMessage: string | null = null;

  selectedShippingMethod: any = { name: 'Standard', cost: 70 };
  shippingMethods = [
    { name: 'Standard', cost: 70 },
    { name: 'Express', cost: 150 },
    { name: 'Next Day', cost: 250 }
  ];

  constructor(
    private shoppingCartService: ShoppingCartService,
    private authService: AuthService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private shopOrderService: ShopOrderService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.userId = this.authService.getUserId();
    if (this.userId) {
      this.loadCartItems();
      this.loadDefaultAddress();
      this.loadDefaultPaymentMethod();
    }
  }

  loadCartItems(): void {
    this.shoppingCartService.getShoppingCartItems(this.userId!).subscribe((items: ShoppingCartItem[]) => {
      this.cartItems = items.map(item => ({
        ...item,
        totalPrice: item.product.price * item.qty
      }));
      this.updateTotal();
    });
  }

  loadDefaultAddress(): void {
    this.http.get<any>(`http://localhost:8080/api/user_addresses/user/${this.userId}`).subscribe(addresses => {
      this.defaultAddress = addresses.find((address: any) => address.default);
      this.checkCanCheckout();
    });
  }

  loadDefaultPaymentMethod(): void {
    this.http.get<any>(`http://localhost:8080/api/user-payment-methods/user/${this.userId}`).subscribe(paymentMethods => {
      this.defaultPaymentMethod = paymentMethods.find((method: any) => method.isDefault);
      this.checkCanCheckout();
    });
  }

  checkCanCheckout(): void {
    this.canCheckout = !!this.defaultAddress && !!this.defaultPaymentMethod;
    this.errorMessage = this.canCheckout
      ? null
      : 'Debe tener una dirección y un método de pago predeterminados.';
  }

  updateQuantity(item: ShoppingCartItem, qty: number): void {
    if (qty < 1) return; // Evita que la cantidad sea menor a 1
    item.qty = qty;
    item.totalPrice = item.product.price * item.qty;
    this.updateTotal();
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
      this.updateTotal();
      this.messageService.clear(); // Limpia mensajes anteriores
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Artículo eliminado!' });
    });
  }

  updateTotal(): void {
    this.total = this.cartItems.reduce((sum, item) => sum + item.totalPrice!, 0);
    this.shippingTotal = this.selectedShippingMethod ? this.selectedShippingMethod.cost : 0;
  }

  checkout(): void {
    if (!this.canCheckout) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: this.errorMessage! });
      return;
    }

    const orderTotalWithShipping = this.total + this.shippingTotal;

    const order = {
      orderDate: new Date().toISOString().split('T')[0],
      address: { id: this.defaultAddress.id },
      shippingMethod: this.selectedShippingMethod ? this.selectedShippingMethod.name : 'Standard',
      orderTotal: orderTotalWithShipping,
      orderStatus: 'Processing',
      user: { id: this.userId },
      userPaymentMethods: [{ paymentMethodId: this.defaultPaymentMethod.id }]
    };

    this.shopOrderService.createOrder(order).subscribe(orderResponse => {
      const orderId = orderResponse.id;
      this.createOrderLines(orderId!);
    });
  }

  createOrderLines(orderId: number): void {
    const orderLineRequests = this.cartItems.map(item => ({
      price: item.product.price,
      qty: item.qty,
      productItem: { id: item.product.id },
      shopOrder: { id: orderId }
    }));

    let completedRequests = 0;

    orderLineRequests.forEach(orderLine => {
      this.http.post<any>('http://localhost:8080/api/order-lines', orderLine).subscribe(() => {
        completedRequests++;
        if (completedRequests === orderLineRequests.length) {
          this.emptyCart();
          this.router.navigate(['/shop_order', orderId]);
        }
      });
    });
  }

  emptyCart(): void {
    this.shoppingCartService.emptyCart(this.userId!).subscribe(() => {
      this.cartItems = [];
      this.total = 0;
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Carrito vaciado!' });
    });
  }
}
