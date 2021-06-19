import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAuthorsListComponent } from './all-authors-list.component';

describe('AllAuthorsListComponent', () => {
  let component: AllAuthorsListComponent;
  let fixture: ComponentFixture<AllAuthorsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllAuthorsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllAuthorsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
