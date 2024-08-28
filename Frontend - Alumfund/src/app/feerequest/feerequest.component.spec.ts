import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeerequestComponent } from './feerequest.component';

describe('FeerequestComponent', () => {
  let component: FeerequestComponent;
  let fixture: ComponentFixture<FeerequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeerequestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeerequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
