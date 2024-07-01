import { TestBed } from '@angular/core/testing';

import { UserPaymentMethodService } from './user-payment-method.service';

describe('UserPaymentMethodService', () => {
  let service: UserPaymentMethodService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserPaymentMethodService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
