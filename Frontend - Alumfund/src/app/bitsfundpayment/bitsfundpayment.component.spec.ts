import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BitsfundpaymentComponent } from './bitsfundpayment.component';

describe('BitsfundpaymentComponent', () => {
  let component: BitsfundpaymentComponent;
  let fixture: ComponentFixture<BitsfundpaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BitsfundpaymentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BitsfundpaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
