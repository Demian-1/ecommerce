import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderNoAuthComponent } from './header-no-auth.component';

describe('HeaderNoAuthComponent', () => {
  let component: HeaderNoAuthComponent;
  let fixture: ComponentFixture<HeaderNoAuthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeaderNoAuthComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderNoAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
