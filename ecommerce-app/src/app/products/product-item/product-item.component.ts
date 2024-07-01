import { Component, Input } from '@angular/core';
import { CardModule } from 'primeng/card';
import { Button } from 'primeng/button';
import { Product } from '../../model/Product';
import { AuthService } from '../../service/auth.service';
import { ShoppingCartService } from '../../service/shoppingcart.service';

@Component({
  selector: 'app-product-item',
  standalone: true,
  imports: [
    CardModule,
    Button
  ],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.css'
})
export class ProductItemComponent {  
  @Input() product = new Product();
  userId : number | null = null;
  constructor(
    private authService: AuthService,
    private shoppingCartService: ShoppingCartService,
  ) { }

  ngOnInit(){
    this.userId = this.authService.getUserId();
  }

  addToCart(idProducto: number){
    if(this.userId){
      this.shoppingCartService.addProductToCart(idProducto, this.userId, 1).subscribe(
        () => {
          alert("Agregado con exito");
        }
      );
    }
  }

}
