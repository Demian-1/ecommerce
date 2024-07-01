import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { CardModule } from 'primeng/card';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    PasswordModule,
    ToastModule,
    CardModule,
    MessagesModule,
    MessageModule
  ],
  providers: [MessageService],
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  loginForm: FormGroup;

  constructor(private messageService: MessageService) {
    this.loginForm = new FormGroup({
      emailAddress: new FormControl('', [Validators.required, Validators.email]),
      userName: new FormControl('', Validators.required),
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}$')]),
      password: new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const formData = this.loginForm.value;
      // Aquí iría la lógica de autenticación
      this.messageService.add({severity:'success', summary: 'Éxito de Inicio de Sesión', detail: 'Sesión iniciada correctamente'});
    } else {
      this.messageService.add({severity:'error', summary: 'Error de Inicio de Sesión', detail: 'Por favor, ingrese credenciales válidas'});
    }
  }
}
