import { Component, OnInit, TemplateRef } from '@angular/core';
import { ShareService } from 'src/app/services/share.service';
import { LeaveService } from '../../services/leave.service';
import { AuthService } from '../../services/auth.service';
import { FormGroup } from '@angular/forms';
import { FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmployeeService } from 'src/app/services/employee.service';
import { MatTableDataSource } from '@angular/material';
import { ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';




@Component({
  selector: 'app-leave-details',
  templateUrl: './leave-details.component.html',
  styleUrls: ['./leave-details.component.css']
})


export class LeaveDetailsComponent implements OnInit {



  emp: any = [];
  employees: any = [];
  pendingLeaves: any = [];
  ManagerForEmployee: any = [];
  Title = 'leave details :';
  managerId: any;
  employeeId: any;
  leaveId: any;
  startDate: any;
  endDate: any;
  date = new Date();
  leaveType: any;
  reason: any;
  leaveStatus: any;
  appliedOn: any;
  empId: any;
  leave: any;
  leaveHistory: any;
  empProfile: any;
  employeeDetails: any = [];
  spinner: boolean = false;
  minDate = new Date();


  //datepicker validations
  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    // Prevent Saturday and Sunday from being selected.

    return day !== 0 && day !== 6;
  }
  pendings: any = [];




  successSnackBar() {
    this.sanckBar.open("Submitted Successful", "close", {
      duration: 2000,
    })
  }

  failSnackBar() {
    this.sanckBar.open("Submission failed", "close", {
      duration: 2000,
    })
  }



  email = new FormControl('', [Validators.required, Validators.email]);
  manEmp: any;
  status: Object;
  reasonMd: any;
  leavId: any;
  appliedDate: any;
  comments: any;
  Rejected: any;
  Approved: any;
  LeaveStatus: any;
  manComments: Object;
  stat: any;
  managerEmployee: any = [];
  searchInput: any;



  displayedColumns1: string[] = ['empId', 'empLeaveCount', 'empMail', 'empName', 'empPhone',];
  displayedColumns: string[] = ['leaveId', 'empId', 'startDate', 'endDate', 'appliedOn', 'reason', 'status',];
  displayedColumnsForManager: string[] = ['leaveId', 'empId', 'startDate', 'endDate', 'appliedOn', 'reason', 'status', 'full View', 'Actions',];

  constructor(private leaveDetails: LeaveService, private share: ShareService,
    private authService: AuthService, public dialog: MatDialog,
    private sanckBar: MatSnackBar, private employeeService: EmployeeService) { }

  dataSource: MatTableDataSource<any>;


  paginator: MatPaginator;




  ngOnInit() {


    // console.log("loaded");

    this.empProfile = JSON.parse(sessionStorage.getItem('profile'));

    // this.employeeId = this.share.getInfo();

    const data = JSON.parse(localStorage.getItem('emp'));
    this.employeeId = data.empId;
    console.log(this.employeeId)
    this.managerId = data.manId;
    console.log(this.managerId)
    this.getEmployees();

    if (this.employeeId.toString().length < 4) {
      this.manEmp = data.empId;

    }
    else {
      this.manEmp = data.manId;
    }

    // console.log("empid :", this.employeeId, "ManId :", this.managerId)

    this.appliedOn = this.date.getUTCFullYear() + "-" + (this.date.getUTCMonth() + 1) + "-" + this.date.getDate();




    this.getPendingLeaves(this.employeeId);
    console.log(this.Title)

    // this.managerId = this.share.getManager();


    this.getLeaveHistory(this.employeeId);

    this.getEmployeePendingLeavesForMan(this.manEmp)
    this.pendingLeaves = new MatTableDataSource(this.pendingLeaves);
    this.leaveHistory = new MatTableDataSource(this.leaveHistory)
    this.ManagerForEmployee = new MatTableDataSource(this.ManagerForEmployee)

  }


  // table data for pending leaves material table
  getPendingLeaves(employeeId) {

    this.leaveDetails.getPendingLeaves(employeeId).subscribe(result => {
      this.pendingLeaves.data = result;
      // this.pendingLeaves.forEach(leave => {
      //   this.pendings.unshift(leave);
      // })
      // console.log("LeaveDetails :", result);

    })
    // this.pendings = [];
  }
  getLeaveHistory(employeeId) {
    this.leaveDetails.getLeaveHistory(employeeId).subscribe(result => {
      this.leaveHistory.data = result;
      console.log("Leave History for employees : ", result);
    })
  }

  getEmployeePendingLeavesForMan(manEmp) {


    this.leaveDetails.getEmployeePendingLeavesForMan(manEmp).subscribe(result => {
      this.ManagerForEmployee = result;
      this.ManagerForEmployee.forEach(employee => {
        if (employee.empId != this.manEmp)
          this.managerEmployee.unshift(employee);
      })

      console.log("data :", this.managerEmployee, this.manEmp);

      // console.log("ManagerForEmployee : ", result);
    })
    this.managerEmployee = [];
  }

  applyLeave() {
    this.empId = this.employeeId;
    this.leaveStatus = 'Pending';
    this.appliedOn;

    var sDate = this.startDate.getFullYear() + "-" + this.startDate.getMonth() + "-" + this.startDate.getDate();
    var eDate = this.endDate.getFullYear() + "-" + this.endDate.getMonth() + "-" + this.endDate.getDate();
    let start = Number(this.startDate.getDate());
    let end = Number(this.endDate.getDate());
    let yeaar = this.endDate.getFullYear();
    let month = this.endDate.getMonth()

    var newLeave = {
      "empId": this.empId,
      "manId": this.managerId,
      "startDate": sDate,
      "endDate": eDate,
      "leaveType": this.leaveType,
      "reason": this.reason,
      "status": this.leaveStatus,
      "appliedOn": this.appliedOn
    }

    var flag = 1;
    // console.log("startdate", this.empId, this.leaveHistory[0].startDate)

    // console.log(newLeave);
    let endArray = eDate.split("-");
    let startArray = sDate.split("-");
    let endCond = Number(endArray[2]);
    let startCond = Number(startArray[2])
    let year = Number(endArray[0])
    let monthCond = Number(endArray[1])

    // console.log(Number(endArray[2]))

    this.leaveHistory.forEach(leave => {
      let lstd = leave.startDate.split("-")
      let letd = leave.startDate.split("-")
      // if (leave.startDate == sDate && leave.empId == newLeave.empId &&
      //   leave.endDate == sDate) {
      console.log(month, yeaar)
      if ((letd[2] >= endCond && leave.empId == newLeave.empId && leave.startDate == sDate) && (letd[0] != month)) {
        flag = 2

        console.log("Date is not valid");

        this.sanckBar.open("Date is already taken, Not Valid !!", "close", {
          duration: 2000,

        })

      }
      // }
    });

    if (flag == 1) {
      console.log("Date last block :", start, sDate, end,)
      this.spinner = true;



      this.leaveDetails.addLeave(newLeave).subscribe(result => {

        this.leave = result;
        console.log("Inserted :", result)
        this.successSnackBar()

        this.getPendingLeaves(this.employeeId);

        this.getLeaveHistory(this.employeeId);

        this.spinner = false;

      })

    }





  }

  approveSuccessSnack() {
    this.sanckBar.open("approved Successfully", "close", {
      duration: 2000,
    })
  }

  rejectLeaveSnack() {
    this.sanckBar.open("rejected Successfully", "close", {
      duration: 2000,
    })
  }

  commitLeaveSnack() {
    this.sanckBar.open("Inserted Successfully", "close", {
      duration: 2000,
    })
  }

  approveLeave(leaveId) {
    var emp = {
      "leaveId": leaveId
    }
    this.leaveDetails.approveLeave(emp).subscribe(result => {
      this.status = result;
      console.log(this.status);
      this.approveSuccessSnack()
      this.getEmployeePendingLeavesForMan(this.manEmp)
    })

  }

  rejectLeave(leaveId) {
    var emp = {
      "leaveId": leaveId
    }
    this.leaveDetails.rejectLeave(emp).subscribe(result => {
      this.status = result;
      console.log(this.status);
      this.rejectLeaveSnack()
      this.getEmployeePendingLeavesForMan(this.manEmp)
    })

    this.getEmployeePendingLeavesForMan(this.manEmp)

  }

  openDialog(leaveId, reason, appliedOn) {

    this.reasonMd = reason;
    this.leavId = leaveId;
    this.appliedDate = appliedOn;
  }

  addComments() {

    var comments = {
      "managerComments": this.comments,
      "leaveId": this.leavId,
      "status": this.stat
    }
    console.log(comments);
    this.leaveDetails.addComments(comments).subscribe(result => {
      this.manComments = result;
      console.log("inserted comment:", this.manComments);
      this.commitLeaveSnack()
      this.getEmployeePendingLeavesForMan(this.manEmp)
    })


  }

  getEmployees(): void {
    const data = JSON.parse(localStorage.getItem('emp'));
    const managerID = data.empId;

    this.employeeService.getEmployees().subscribe(result => {
      this.employeeDetails = result
      this.employeeDetails.forEach(emp => {
        if (emp.manId == managerID) {
          this.emp.unshift(emp);
        }
      })
      this.employees = this.emp;
      // console.log("manage employee details:", this.emp);
    });

  }

  // closeModel() {
  //   this.leaveDetails.filter('Registered click');
  // }





  applyFilter(filterValue: any) {
    // console.log(this.dataSource);
    this.pendingLeaves.filter = filterValue.trim().toLowerCase();
    this.leaveHistory.filter = filterValue.trim().toLowerCase();
    // console.log(c);
  }

}
