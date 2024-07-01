import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { AuthGuard } from './service/auth.guard';
import { AddressComponent } from './address/address.component';

export const routes: Routes = [
    {
      path: '',
      redirectTo: '/home',
      pathMatch: 'full'
    },
    {
      path: 'home',
      component: HomeComponent
    },
    {
      path: 'loginForm',
      component: LoginFormComponent
    },
    {
      path: 'registroForm',
      component: RegisterFormComponent
    },
    {
      path: 'categoria',
      component: RegisterFormComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'product',
      component: RegisterFormComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'shopping_cart',
      component: RegisterFormComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'shop_order',
      component: RegisterFormComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'user_payment_method',
      component: RegisterFormComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'user_address',
      component: AddressComponent,
      canActivate: [AuthGuard]
    }
  ];