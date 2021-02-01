import { TestBed } from '@angular/core/testing';

import { FirebaseImporterService } from './firebase-importer.service';

describe('FirebaseImporterService', () => {
  let service: FirebaseImporterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirebaseImporterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
