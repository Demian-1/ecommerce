import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { TabMenuModule } from 'primeng/tabmenu';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [TabMenuModule, RouterLink],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  elementosDelMenu: MenuItem[] | undefined;

  ngOnInit(): void {
    this.elementosDelMenu = [
      {label: 'Home', icon: 'pi pi-home', routerLink: '/home'},
      {label: 'Categorías', icon: 'pi pi-tags', routerLink: '/categoria'},
      {label: 'Productos', icon: 'pi pi-box', routerLink: '/product'},
      {label: 'Carrito', icon: 'pi pi-shopping-cart', routerLink: '/shopping_cart'},
      {label: 'Orden', icon: 'pi pi-file', routerLink: '/shop_order'},
      {label: 'Método de Pago', icon: 'pi pi-wallet', routerLink: '/user_payment_method'},
      {label: 'Dirección de Usuario', icon: 'pi pi-map', routerLink: '/user_address'},
      {label: 'Registro', icon: 'pi pi-user-edit', routerLink: '/registroForm'},
      {label: 'Login', icon: 'pi pi-user-plus', routerLink: '/loginForm'}
    ];
  }
}
