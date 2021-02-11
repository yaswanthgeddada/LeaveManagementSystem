import { Component, OnInit, ViewChildren } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee.service';
import { ShareService } from '../../services/share.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ViewChild, ElementRef } from '@angular/core';



@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})


export class EmployeeComponent implements OnInit {
  employees: any = [];
  employeeId: any;
  managerId: any;
  role: any;
  empPassword: String;
  searchInput: any;
  emp: any = [];

  constructor(private employeeService: EmployeeService, public shareInfo: ShareService,
    private router: Router, private authService: AuthService, private sanckBar: MatSnackBar) { }

  @ViewChild('closebutton', { static: false }) closebutton: ElementRef;

  spinner: boolean = false;

  successSnackBar() {
    this.sanckBar.open("login Successful", "close", {
      duration: 2000,
    })
  }

  failSnackBar() {
    this.sanckBar.open("login failed", "close", {
      duration: 2000,
    })
  }

  ngOnInit() {
    this.spinner = true;

    this.getEmployees();


    // console.log("login :", this.empPassword, this.employeeId)
  }

  singleEmp(empId, manId, empRole) {
    this.employeeId = empId;
    this.managerId = manId;
    this.role = empRole;

    // console.log("employee empComp ", this.employeeId);
    // console.log("Manager empComp ", this.managerId);
    this.shareInfo.setInfo(this.employeeId);
    this.shareInfo.setManager(this.managerId)
    this.shareInfo.setRole(this.role);
  }

  empLogin() {
    var emplogin = {
      "empId": this.employeeId,
      "empPassword": this.empPassword,
    }

    var storageObject = {
      "empId": this.employeeId,
      "manId": this.managerId,
      "empRole": this.role

    }
    this.authService.auth(emplogin).subscribe(result => {
      console.log(result);
      var check: any = result;
      if (check.auth == true) {
        this.router.navigateByUrl("/dashboard");
        console.log(emplogin);
        this.authService.storeUserData(storageObject);
        this.successSnackBar()
      }
      else {
        this.router.navigateByUrl("/");
        this.failSnackBar()
      }
    })

  }



  getEmployees(): void {

    this.employeeService.getEmployees().subscribe(data => {
      this.employees = data
      err => console.log(err)
      console.log("employee details :", this.employees);
    });
    this.spinner = false;


  }

  enter(searchInput) {

    // console.log(searchInput);
    if (searchInput.code == 'Enter') {
      this.empLogin();
      this.router.navigate(['/dashboard'])
      this.closebutton.nativeElement.click();

    }

  }





  // search(searchInput) {
  //   console.log(Number(searchInput))
  //   if (searchInput != null) {
  //     this.employees.forEach(employee => {
  //       if (employee.empId == Number(searchInput)) {
  //         this.employees.push(employee);
  //         this.getEmployees
  //       }

  //       // else {
  //       //   this.emp = this.employees;
  //       // }
  //     })
  //   }
  //   else {
  //     this.getEmployees();
  //   }


  // }





}
