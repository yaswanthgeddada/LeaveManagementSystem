import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) {
  }

  path: String = "http://localhost:2523/MLP252/api/";

  getEmployees(): Observable<Employee[]> {
    console.log('getEmployees called on employee.service');
    return this.http.get<Employee[]>(this.path + 'employees');

  }

  getEmployeeById(empId) {
    return this.http.get(this.path + 'employees/' + empId);

  }
  getManagerById(manId) {
    return this.http.get(this.path + 'employees/' + manId);

  }
}
