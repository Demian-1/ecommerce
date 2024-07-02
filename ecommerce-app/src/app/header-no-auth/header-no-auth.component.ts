import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { TabMenuModule } from 'primeng/tabmenu';

@Component({
  selector: 'app-header-no-auth',
  standalone: true,
  imports: [TabMenuModule, RouterLink],
  templateUrl: './header-no-auth.component.html',
  styleUrls: ['./header-no-auth.component.css']
})
export class HeaderNoAuthComponent {
  elementosDelMenu: MenuItem[] | undefined;

  ngOnInit(): void {
    this.elementosDelMenu = [
      {label: 'Productos', icon: 'pi pi-box', routerLink: '/product-list'},
      {label: 'Registro', icon: 'pi pi-user-edit', routerLink: '/registroForm'},
      {label: 'Login', icon: 'pi pi-user-plus', routerLink: '/loginForm'}
    ];
  }
}
