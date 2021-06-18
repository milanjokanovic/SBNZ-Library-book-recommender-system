import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserreadbookComponent } from './userreadbook.component';

describe('UserreadbookComponent', () => {
  let component: UserreadbookComponent;
  let fixture: ComponentFixture<UserreadbookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserreadbookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserreadbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
