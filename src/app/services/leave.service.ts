import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
// import { Subject } from 'rxjs';
// import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {


  // path variable
  path: String = "http://localhost:2523/MLP2520/api/leave/";

  constructor(private http: HttpClient) { }

  // leaves which are in pending for an employee
  getPendingLeaves(empId) {
    return this.http.get(this.path + 'pendingLeaves/' + empId)
  }

  // leaves of employee which are in pending , showen for manager
  getEmployeePendingLeavesForMan(manId) {
    return this.http.get(this.path + 'pendingLeavesOfEmp/' + manId)
  }

  addLeave(newLeave) {
    return this.http.post(this.path + 'applyLeave', newLeave);
  }

  getLeaveHistory(empId) {
    return this.http.get(this.path + 'leaveHistory/' + empId);
  }

  approveLeave(leaveId) {
    return this.http.put(this.path + 'approveLeave', leaveId);
  }

  rejectLeave(empId) {
    return this.http.put(this.path + 'rejectLeave', empId);
  }

  addComments(comment) {
    return this.http.put(this.path + 'addComments', comment);
  }


  // private _listernsers = new Subject<any>();
  // listen(): Observable<any> {
  //   return this._listernsers.asObservable();
  // }
  // filter(filterBy: string) {
  //   this._listernsers.next(filterBy);
  // }

}
