import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminpostloginComponent } from './adminpostlogin.component';

describe('AdminpostloginComponent', () => {
  let component: AdminpostloginComponent;
  let fixture: ComponentFixture<AdminpostloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminpostloginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminpostloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
