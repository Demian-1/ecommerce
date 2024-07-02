import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { TabMenuModule } from 'primeng/tabmenu';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [TabMenuModule, RouterLink, ButtonModule, DialogModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  elementosDelMenu: MenuItem[] | undefined;
  displayLogoutDialog: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.elementosDelMenu = [
      { label: 'Productos', icon: 'pi pi-box', routerLink: '/product-list' },
      { label: 'Carrito', icon: 'pi pi-shopping-cart', routerLink: '/shopping_cart' },
      { label: 'Orden', icon: 'pi pi-file', routerLink: '/shop_order' },
      { label: 'Método de Pago', icon: 'pi pi-wallet', routerLink: '/user_payment_method' },
      { label: 'Dirección de Usuario', icon: 'pi pi-map', routerLink: '/user_address' },
      { label: 'Info de Usuario', icon: 'pi pi-info-circle', routerLink: '/user-info' },
      { label: 'Logout', icon: 'pi pi-sign-out', command: () => this.showLogoutDialog() }
    ];
  }

  showLogoutDialog() {
    this.displayLogoutDialog = true;
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/loginForm']);
    this.displayLogoutDialog = false;
  }

  onCancel() {
    this.displayLogoutDialog = false;
  }
}
