import { Component, OnInit } from '@angular/core';
import { ShareService } from '../../services/share.service';
import { EmployeeService } from '../../services/employee.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {

  date = new Date();
  empId: any;
  employee: any = [];
  manager: any = [];
  manId: any;
  minDate: { year: number; month: number; day: number; };
  emp: any;
  profile: any;
  constructor(private share: ShareService, public employeeService: EmployeeService, private authService: AuthService) { }

  ngOnInit() {

    const data = JSON.parse(localStorage.getItem('emp'));


    this.empId = data.empId;
    // console.log("empID dashBoardComp: ", this.empId);

    this.manId = data.manId;
    // console.log("managerID dashBoardComp: ", this.manId);

    this.getEmployeeById();
    this.getManagerById()


  }

  getEmployeeById(): void {
    this.employeeService.getEmployeeById(this.empId).subscribe(data => {
      this.employee = data
      // console.log("employee details dashComp :", this.employee);
    });
  }

  getManagerById(): void {
    this.employeeService.getManagerById(this.manId).subscribe(data => {
      this.manager = data
      // console.log("Manager details dashComp :", this.manager);
    });
  }


  tokenForEmployee(profile) {
    sessionStorage.setItem('profile', JSON.stringify(profile));
    this.profile = profile;
  }

}
