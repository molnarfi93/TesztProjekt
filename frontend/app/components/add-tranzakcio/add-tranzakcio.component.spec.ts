import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTranzakcioComponent } from './add-tranzakcio.component';

describe('AddTranzakcioComponent', () => {
  let component: AddTranzakcioComponent;
  let fixture: ComponentFixture<AddTranzakcioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTranzakcioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTranzakcioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});