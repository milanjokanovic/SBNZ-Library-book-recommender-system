import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllGenresListComponent } from './all-genres-list.component';

describe('AllGenresListComponent', () => {
  let component: AllGenresListComponent;
  let fixture: ComponentFixture<AllGenresListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllGenresListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllGenresListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
