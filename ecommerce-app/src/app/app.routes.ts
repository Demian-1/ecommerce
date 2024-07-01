import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';

export const routes: Routes = [
    {
        path: '', 
        redirectTo: '/home',
        pathMatch: 'full'
    },
    {
        path: 'home',
        component: HomeComponent,
    },
    {
        path: 'loginForm',
        component: LoginFormComponent,
    },
    {
        path: 'registroForm',
        component: RegisterFormComponent,
    },
];
