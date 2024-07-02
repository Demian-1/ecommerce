import { TestBed } from '@angular/core/testing';

import { ShopOrderService } from './shop-order.service';

describe('ShopOrderService', () => {
  let service: ShopOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShopOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
