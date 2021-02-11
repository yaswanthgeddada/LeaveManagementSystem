import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee.service';
import { ShareService } from 'src/app/services/share.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  date = new Date();
  empId: any;
  employee: any = [];
  manager: any = [];
  manId: any;
  minDate: { year: number; month: number; day: number; };
  empProfile: any;
  emp: any;
  constructor(private share: ShareService, public employeeService: EmployeeService) { }
  spinner: boolean = false;

  ngOnInit() {

    this.spinner = true;
    this.empProfile = JSON.parse(sessionStorage.getItem('profile'));
    const data = JSON.parse(localStorage.getItem('emp'));
    this.empId = data.empId;
    this.manId = data.manId;

    this.getEmployeeById();
    this.getManagerById()
  }






  getEmployeeById(): void {

    this.employeeService.getEmployeeById(this.empId).subscribe(data => {
      this.employee = data
      console.log("employee details dashComp :", this.employee);
      this.spinner = false;
    });
  }

  getManagerById(): void {
    this.employeeService.getManagerById(this.manId).subscribe(data => {
      this.manager = data
      console.log("Manager details dashComp :", this.manager);
    });
  }


}
