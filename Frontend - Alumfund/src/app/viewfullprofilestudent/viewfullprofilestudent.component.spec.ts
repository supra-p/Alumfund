import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewfullprofilestudentComponent } from './viewfullprofilestudent.component';

describe('ViewfullprofilestudentComponent', () => {
  let component: ViewfullprofilestudentComponent;
  let fixture: ComponentFixture<ViewfullprofilestudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewfullprofilestudentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewfullprofilestudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
