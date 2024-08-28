import { TestBed } from '@angular/core/testing';

import { StudentAlumniService } from './studAlum.service';

describe('StudentAlumniServiceService', () => {
  let service: StudentAlumniService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentAlumniService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
