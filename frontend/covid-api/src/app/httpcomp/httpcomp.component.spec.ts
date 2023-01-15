import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpcompComponent } from './httpcomp.component';

describe('HttpcompComponent', () => {
  let component: HttpcompComponent;
  let fixture: ComponentFixture<HttpcompComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HttpcompComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HttpcompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
