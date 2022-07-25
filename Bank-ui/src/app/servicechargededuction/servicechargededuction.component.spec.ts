import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicechargedeductionComponent } from './servicechargededuction.component';

describe('ServicechargedeductionComponent', () => {
  let component: ServicechargedeductionComponent;
  let fixture: ComponentFixture<ServicechargedeductionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServicechargedeductionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServicechargedeductionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
