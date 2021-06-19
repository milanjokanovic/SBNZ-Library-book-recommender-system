import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OptionNavBarComponent } from './option-nav-bar.component';

describe('OptionNavBarComponent', () => {
  let component: OptionNavBarComponent;
  let fixture: ComponentFixture<OptionNavBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OptionNavBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OptionNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
