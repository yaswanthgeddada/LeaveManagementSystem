import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './employee.service';
// import { Employee } from './services/employee';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [EmployeeService]
})
export class AppComponent implements OnInit {
  model;
  date = new Date();
  minDate: { year: any; month: any; day: any; };

  constructor() { }

  title = 'Leave Management Application';
  employees: any = [];
  ngOnInit(): void {

    // this.getEmployees();
  }

  // getEmployees(): void {
  //   this.employeeService.getEmployees().subscribe(data => {
  //     this.employees = data
  //     err => console.log(err)
  //     console.log("employee details :", this.employees);
  //   });

  // }


}
