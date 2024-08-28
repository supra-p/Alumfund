
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BitsfundregComponent } from './bitsfundreg.component';

describe('BitsfundregComponent', () => {
  let component: BitsfundregComponent;
  let fixture: ComponentFixture<BitsfundregComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BitsfundregComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BitsfundregComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

