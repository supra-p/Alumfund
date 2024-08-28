import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BitsfundhomeComponent } from './bitsfundhome.component';

describe('BitsfundhomeComponent', () => {
  let component: BitsfundhomeComponent;
  let fixture: ComponentFixture<BitsfundhomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BitsfundhomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BitsfundhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
