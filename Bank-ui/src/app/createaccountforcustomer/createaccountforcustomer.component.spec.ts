import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateaccountforcustomerComponent } from './createaccountforcustomer.component';

describe('CreateaccountforcustomerComponent', () => {
  let component: CreateaccountforcustomerComponent;
  let fixture: ComponentFixture<CreateaccountforcustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateaccountforcustomerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateaccountforcustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
