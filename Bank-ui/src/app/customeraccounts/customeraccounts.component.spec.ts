import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomeraccountsComponent } from './customeraccounts.component';

describe('CustomeraccountsComponent', () => {
  let component: CustomeraccountsComponent;
  let fixture: ComponentFixture<CustomeraccountsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomeraccountsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomeraccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
