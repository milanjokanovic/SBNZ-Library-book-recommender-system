import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFavoriteAuthorComponent } from './add-favorite-author.component';

describe('AddFavoriteAuthorComponent', () => {
  let component: AddFavoriteAuthorComponent;
  let fixture: ComponentFixture<AddFavoriteAuthorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFavoriteAuthorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFavoriteAuthorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
