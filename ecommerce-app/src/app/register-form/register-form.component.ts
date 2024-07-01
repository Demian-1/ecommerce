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
  selector: 'app-register-form',
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
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {
  registerForm: FormGroup;

  constructor(private messageService: MessageService) {
    this.registerForm = new FormGroup({
      emailAddress: new FormControl('', [Validators.required, Validators.email]),
      userName: new FormControl('', Validators.required),
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}$')]),
      password: new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const formData = this.registerForm.value;
      // Aquí iría la lógica de registro
      this.messageService.add({severity:'success', summary: 'Registro Exitoso', detail: 'Usuario registrado correctamente'});
    } else {
      this.messageService.add({severity:'error', summary: 'Error de Registro', detail: 'Por favor, ingrese datos válidos'});
    }
  }
}
