import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrarLibrosComponent } from './administrar-libros.component';

describe('AdministrarLibrosComponent', () => {
  let component: AdministrarLibrosComponent;
  let fixture: ComponentFixture<AdministrarLibrosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdministrarLibrosComponent]
    });
    fixture = TestBed.createComponent(AdministrarLibrosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
