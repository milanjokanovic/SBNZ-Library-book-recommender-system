import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFavoriteBookComponent } from './add-favorite-book.component';

describe('AddFavoriteBookComponent', () => {
  let component: AddFavoriteBookComponent;
  let fixture: ComponentFixture<AddFavoriteBookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFavoriteBookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFavoriteBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
