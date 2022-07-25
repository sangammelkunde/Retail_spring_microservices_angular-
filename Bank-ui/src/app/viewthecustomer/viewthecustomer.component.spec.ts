import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewthecustomerComponent } from './viewthecustomer.component';

describe('ViewthecustomerComponent', () => {
  let component: ViewthecustomerComponent;
  let fixture: ComponentFixture<ViewthecustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewthecustomerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewthecustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
