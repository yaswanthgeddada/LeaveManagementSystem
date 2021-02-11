import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  headers = new HttpHeaders({
    'Content-type': 'application/json'
  })
  emp: any;
  role: any;
  path = "http://localhost:2523/MLP252/api/";

  constructor(private route: Router, private http: HttpClient) { }

  auth(isauth) {
    return this.http.post(this.path + 'employees/empLogin', isauth);
  }

  storeUserData(emp) {
    localStorage.setItem('emp', JSON.stringify(emp));
    this.emp = emp;
  }

  loggedIn() {
    return !!localStorage.getItem('emp')
  }

  logoutUser() {
    localStorage.removeItem('emp');
    sessionStorage.removeItem('profile');
    this.route.navigate(['/']);
  }

  managerLoggedIn() {
    var data1 = JSON.parse(localStorage.getItem('emp'));
    this.role = data1.empRole;
    if (!data1) {
      data1 = null;
      return false;
    }
    else if (this.role == "Manager") {
      data1 = null;
      return true;
    }
    else {
      data1 = null;
      return false;
    }
  }

  employeeLoggedIn() {
    var data1 = JSON.parse(localStorage.getItem('emp'));
    if (!data1) {
      data1 = null;
      return false;
    }
    else if (data1.data.role == "Develop") {
      data1 = null;
      return true;
    }
    else {
      data1 = null;
      return false;
    }
  }

  ceoLoggedIn() {
    var data1 = JSON.parse(localStorage.getItem('emp'));
    if (!data1) {
      data1 = null;
      return false;
    }
    else if (data1.data.role == "ceo") {
      data1 = null;
      return true;
    }
    else {
      data1 = null;
      return false;
    }
  }

}
