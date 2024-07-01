import { Component, Injector } from '@angular/core';
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
import { FloatLabelModule } from 'primeng/floatlabel';


@Component({
  selector: 'app-register-form',
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
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
  providers: [MessageService]
})
export class RegisterFormComponent {
  registerForm: FormGroup;
  private siteUserService: SiteUserService;

  constructor(
    private fb: FormBuilder,
    private injector: Injector, // Use Injector to avoid direct circular dependency
    private messageService: MessageService,
    private router: Router
  ) {
    this.siteUserService = this.injector.get(SiteUserService); // Get the service instance using Injector
    this.registerForm = this.fb.group({
      emailAddress: ['', [Validators.required, Validators.email]],
      userName: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.siteUserService.registerUser(this.registerForm.value).subscribe(
        response => {
          this.messageService.add({ severity: 'success', summary: 'Registro Exitoso', detail: 'Usuario registrado correctamente' });
          this.router.navigate(['/loginForm']);
        },
        error => {
          this.messageService.add({ severity: 'error', summary: 'Error de Registro', detail: error.error });
        }
      );
    } else {
      this.messageService.add({ severity: 'error', summary: 'Error de Registro', detail: 'Por favor, ingrese datos válidos' });
    }
  }
}
