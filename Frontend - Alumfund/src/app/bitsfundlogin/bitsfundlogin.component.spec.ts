import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BitsfundloginComponent } from './bitsfundlogin.component';

describe('BitsfundloginComponent', () => {
  let component: BitsfundloginComponent;
  let fixture: ComponentFixture<BitsfundloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BitsfundloginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BitsfundloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
