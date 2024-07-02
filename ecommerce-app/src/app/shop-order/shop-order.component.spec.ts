import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopOrderComponent } from './shop-order.component';

describe('ShopOrderComponent', () => {
  let component: ShopOrderComponent;
  let fixture: ComponentFixture<ShopOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShopOrderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
