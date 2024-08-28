import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostloginalumniComponent } from './postloginalumni.component';

describe('PostloginalumniComponent', () => {
  let component: PostloginalumniComponent;
  let fixture: ComponentFixture<PostloginalumniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostloginalumniComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostloginalumniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
