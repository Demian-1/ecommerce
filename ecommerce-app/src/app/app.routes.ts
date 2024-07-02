import { Routes } from '@angular/router';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { AuthGuard } from './service/auth.guard';
import { AddressComponent } from './address/address.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { PaymentFormComponent } from './payment-form/payment-form.component';
import { ProductItemComponent } from './products/product-item/product-item.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';

export const routes: Routes = [
    {
      path: '',
      redirectTo: '/product-list',
      pathMatch: 'full'
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
      component: ShoppingCartComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'shop_order',
      component: RegisterFormComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'user_payment_method',
      component: PaymentFormComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'user_address',
      component: AddressComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'user-info',
      component: UserInfoComponent,
    },
    {
      path: 'product-list',
      component: ProductListComponent,
    }
  ];