

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BitsfundpostloginComponent } from './bitsfundpostlogin.component';

describe('BitsfundregisterComponent', () => {
  let component: BitsfundpostloginComponent;
  let fixture: ComponentFixture<BitsfundpostloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BitsfundpostloginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BitsfundpostloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});