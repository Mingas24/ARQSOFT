import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SandwichRemoveComponent } from './sandwich-remove.component';

describe('SandwichRemoveComponent', () => {
  let component: SandwichRemoveComponent;
  let fixture: ComponentFixture<SandwichRemoveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SandwichRemoveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SandwichRemoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
