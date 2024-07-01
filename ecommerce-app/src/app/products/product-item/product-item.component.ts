import { Component, Input } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { Product } from '../../model/Product';
import { AuthService } from '../../service/auth.service';
import { ShoppingCartService } from '../../service/shoppingcart.service';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms'; // Import FormsModule

@Component({
  selector: 'app-product-item',
  standalone: true,
  imports: [
    CardModule,
    ButtonModule,
    InputNumberModule,
    FormsModule
  ],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.css'
})
export class ProductItemComponent {  
  @Input() product = new Product();
  userId : number | null = null;
  qty = 1;
  constructor(
    private authService: AuthService,
    private shoppingCartService: ShoppingCartService,
  ) { }

  substractQty(){
    if(this.qty>1){
      this.qty = this.qty - 1;
    }
  }

  ngOnInit(){
    this.userId = this.authService.getUserId();
  }

  addToCart(idProducto: number){
    if(this.userId){
      this.shoppingCartService.addProductToCart(idProducto, this.userId, this.qty).subscribe(
        () => {
          alert("Agregado con exito");
        }
      );
    }
  }

}
