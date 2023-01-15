import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupadminComponent } from './supadmin.component';

describe('SupadminComponent', () => {
  let component: SupadminComponent;
  let fixture: ComponentFixture<SupadminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupadminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SupadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
