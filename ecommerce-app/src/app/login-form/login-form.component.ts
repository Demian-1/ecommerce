import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { CardModule } from 'primeng/card';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';
import { SiteUserService } from '../service/site-user.service';
import { AuthService } from '../service/auth.service';
import { SiteUser } from '../model/SiteUser';
import { FloatLabelModule } from 'primeng/floatlabel';
@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    PasswordModule,
    CardModule,
    MessagesModule,
    MessageModule,
    ToastModule,
    FloatLabelModule
  ],
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
  providers: [MessageService]
})
export class LoginFormComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private siteUserService: SiteUserService,
    private authService: AuthService,
    private messageService: MessageService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      emailAddress: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const { emailAddress, password } = this.loginForm.value;
      this.siteUserService.loginUser(emailAddress, password).subscribe(
        (response: SiteUser) => {
          this.authService.login(response.id); // Guarda el ID del usuario
          this.messageService.add({ severity: 'success', summary: 'Inicio de Sesión Exitoso', detail: 'Usuario autenticado correctamente' });
          this.router.navigate(['/product-list']);
        },
        error => {
          this.messageService.add({ severity: 'error', summary: 'Error de Inicio de Sesión', detail: 'Credenciales inválidas' });
        }
      );
    } else {
      this.messageService.add({ severity: 'error', summary: 'Error de Inicio de Sesión', detail: 'Por favor, ingrese datos válidos' });
    }
  }
}
