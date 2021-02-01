import { TestBed } from '@angular/core/testing';

import { FirebaseAuthConnectorService } from './firebase-auth-connector.service';

describe('FirebaseAuthConnectorService', () => {
  let service: FirebaseAuthConnectorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirebaseAuthConnectorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
