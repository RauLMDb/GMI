import { TestBed } from '@angular/core/testing';

import { ClienteInterceptor } from './cliente.interceptor';

describe('ClienteInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      ClienteInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: ClienteInterceptor = TestBed.inject(ClienteInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
