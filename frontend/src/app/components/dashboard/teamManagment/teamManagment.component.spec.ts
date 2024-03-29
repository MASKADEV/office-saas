import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamManagmentComponent } from './teamManagment.component';

describe('HomeComponent', () => {
  let component: TeamManagmentComponent;
  let fixture: ComponentFixture<TeamManagmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamManagmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamManagmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
