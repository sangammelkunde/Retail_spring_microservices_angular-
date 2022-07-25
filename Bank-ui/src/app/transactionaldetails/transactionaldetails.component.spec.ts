import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionaldetailsComponent } from './transactionaldetails.component';

describe('TransactionaldetailsComponent', () => {
  let component: TransactionaldetailsComponent;
  let fixture: ComponentFixture<TransactionaldetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionaldetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionaldetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
