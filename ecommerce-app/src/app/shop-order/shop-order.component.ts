import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { PanelModule } from 'primeng/panel';
import { DividerModule } from 'primeng/divider';
import { AuthService } from '../service/auth.service';
import { CarouselModule } from 'primeng/carousel';

@Component({
  selector: 'app-shop-order',
  standalone: true,
  imports: [CommonModule, CardModule, PanelModule, DividerModule, CarouselModule],
  templateUrl: './shop-order.component.html',
  styleUrls: ['./shop-order.component.css']
})
export class ShopOrderComponent implements OnInit {
  userId: number | null = null;
  orders: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.userId = this.authService.getUserId();
    if (this.userId) {
      this.http.get<any[]>(`http://localhost:8080/api/shop-orders/user/${this.userId}`).subscribe(orders => {
        this.orders = orders;
      });
    }
  }
}
