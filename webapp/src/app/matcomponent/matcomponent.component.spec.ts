import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MatcomponentComponent } from './matcomponent.component';

describe('MatcomponentComponent', () => {
  let component: MatcomponentComponent;
  let fixture: ComponentFixture<MatcomponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MatcomponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatcomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
