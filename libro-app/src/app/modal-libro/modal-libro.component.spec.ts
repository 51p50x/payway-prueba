import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalLibroComponent } from './modal-libro.component';

describe('ModalLibroComponent', () => {
  let component: ModalLibroComponent;
  let fixture: ComponentFixture<ModalLibroComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModalLibroComponent]
    });
    fixture = TestBed.createComponent(ModalLibroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
