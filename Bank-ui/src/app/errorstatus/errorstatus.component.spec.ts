import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorstatusComponent } from './errorstatus.component';

describe('ErrorstatusComponent', () => {
  let component: ErrorstatusComponent;
  let fixture: ComponentFixture<ErrorstatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErrorstatusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ErrorstatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
