import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SystembooksComponent } from './systembooks.component';

describe('SystembooksComponent', () => {
  let component: SystembooksComponent;
  let fixture: ComponentFixture<SystembooksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SystembooksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SystembooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
