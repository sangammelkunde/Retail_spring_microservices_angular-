import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletecustomerComponent } from './deletecustomer.component';

describe('DeletecustomerComponent', () => {
  let component: DeletecustomerComponent;
  let fixture: ComponentFixture<DeletecustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletecustomerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletecustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
