import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TranzakciokComponent } from './tranzakciok.component';

describe('TranzakciokComponent', () => {
  let component: TranzakciokComponent;
  let fixture: ComponentFixture<TranzakciokComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TranzakciokComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TranzakciokComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});