import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { LeaveDetailsComponent } from './components/leave-details/leave-details.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { DashBoardComponent } from './components/dash-board/dash-board.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './mateial.module';
import { ProfileComponent } from './components/profile/profile.component';
import { FormsModule } from '@angular/forms';
import { AuthGuard } from './services/auth.guard';
import { AuthService } from './services/auth.service';
import { EmployeeFilterPipe } from './components/employee/employee-filter.pipe';
import { LeaveFilterPipe } from './components/leave-details/leave-filter.pipe';



const ROUTES: Routes = [
  {
    path: '',
    component: EmployeeComponent,

  },
  {
    path: 'dashboard',
    component: DashBoardComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'leave',
    component: LeaveDetailsComponent,
    canActivate: [AuthGuard],
  }
]

@NgModule({
  declarations: [
    AppComponent,
    LeaveDetailsComponent,
    EmployeeComponent,
    DashBoardComponent,
    NavBarComponent,
    ProfileComponent,
    EmployeeFilterPipe,
    LeaveFilterPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MaterialModule,
    FormsModule,
    RouterModule.forRoot(ROUTES),
    BrowserAnimationsModule,
  ],
  providers: [AuthGuard, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
