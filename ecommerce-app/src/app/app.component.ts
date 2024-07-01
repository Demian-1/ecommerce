import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CardModule } from 'primeng/card';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { AuthService } from './service/auth.service';
import { HeaderNoAuthComponent } from './header-no-auth/header-no-auth.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule,RouterOutlet, HeaderComponent, FooterComponent, CardModule, LoginFormComponent, RegisterFormComponent, HeaderNoAuthComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(public authService: AuthService) {}

  onLogout() {
    this.authService.logout();
  }

  title = 'ecommerce-app';
}
