import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserRecommendationsComponent } from './user-recommendations.component';

describe('UserRecommendationsComponent', () => {
  let component: UserRecommendationsComponent;
  let fixture: ComponentFixture<UserRecommendationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserRecommendationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserRecommendationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
