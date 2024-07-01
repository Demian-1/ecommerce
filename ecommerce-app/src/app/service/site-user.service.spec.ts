import { TestBed } from '@angular/core/testing';

import { SiteUserService } from './site-user.service';

describe('SiteUserService', () => {
  let service: SiteUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SiteUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
