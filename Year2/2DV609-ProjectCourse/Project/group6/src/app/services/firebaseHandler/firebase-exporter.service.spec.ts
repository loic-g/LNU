import { TestBed } from '@angular/core/testing';

import { FirebaseExporterService } from './firebase-exporter.service';

describe('FirebaseExporterService', () => {
  let service: FirebaseExporterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirebaseExporterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
