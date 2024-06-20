import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SandwichAddComponent } from './sandwich-add.component';

describe('SandwichAddComponent', () => {
  let component: SandwichAddComponent;
  let fixture: ComponentFixture<SandwichAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SandwichAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SandwichAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
