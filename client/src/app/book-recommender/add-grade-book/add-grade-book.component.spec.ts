import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGradeBookComponent } from './add-grade-book.component';

describe('AddGradeBookComponent', () => {
  let component: AddGradeBookComponent;
  let fixture: ComponentFixture<AddGradeBookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddGradeBookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddGradeBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
