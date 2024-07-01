import { Component } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { InputNumberModule } from 'primeng/inputnumber';
import { SiteUser } from '../model/SiteUser';
import { SiteUserService } from '../service/site-user.service';
import { FloatLabelModule } from 'primeng/floatlabel';

@Component({
  selector: 'app-user-info',
  standalone: true,
  imports: [
    FormsModule,
    InputTextModule,
    ButtonModule, 
    InputNumberModule,
    FloatLabelModule
  ],
  templateUrl: './user-info.component.html',
  styleUrl: './user-info.component.css'
})
export class UserInfoComponent {
  constructor(
    private authService: AuthService,
    private siteUserService: SiteUserService,
  ){}
  usuario = new SiteUser();
  editar = false;
  

  ngOnInit(){
    const id = this.authService.getUserId();

    if(id){
      this.siteUserService.getUserById(id).subscribe(data => {
        this.usuario = data;
      });
    }
  }

  id = this.authService.getUserId();
}
