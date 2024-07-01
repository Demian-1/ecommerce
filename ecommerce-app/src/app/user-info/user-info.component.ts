import { Component } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { InputNumberModule } from 'primeng/inputnumber';
import { SiteUserDTO } from '../model/SiteUser';
import { SiteUserService } from '../service/site-user.service';
import { FloatLabelModule } from 'primeng/floatlabel';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-user-info',
  standalone: true,
  imports: [
    FormsModule,
    InputTextModule,
    ButtonModule, 
    InputNumberModule,
    FloatLabelModule,
    ToastModule
  ],
  templateUrl: './user-info.component.html',
  styleUrl: './user-info.component.css',
  providers: [MessageService]
})
export class UserInfoComponent {
  constructor(
    private authService: AuthService,
    private siteUserService: SiteUserService,
    private messageService: MessageService
  ){}
  usuario = new SiteUserDTO();
  editar = false;
  

  ngOnInit(){
    const id = this.authService.getUserId();

    if(id){
      this.siteUserService.getUserById(id).subscribe(data => {
        this.usuario = data;
      });
    }
  }

  updateUser(){
    this.siteUserService.updateUser(this.usuario.id, this.usuario).subscribe(data => {
      this.usuario = data;
      this.editar = false;
      this.messageService.add({ severity: 'success', summary: 'Datos actualizados', detail: 'Datos actualizados correctamente' });
    });
  }

  id = this.authService.getUserId();
}
