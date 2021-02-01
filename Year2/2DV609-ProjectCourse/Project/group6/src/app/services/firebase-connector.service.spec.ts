import { TestBed } from '@angular/core/testing';

import { FirebaseConnectorService } from './firebase-connector.service';

describe('FirebaseConnectorService', () => {
  let service: FirebaseConnectorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirebaseConnectorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
