import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShareService {
  info: any;
  manId: any;
  role: any;
  empProfile: any;
  constructor() { }

  // emplyee details component interaction
  setInfo(info) {
    this.info = info;
  }

  // manager details component interaction
  setManager(manId) {
    this.manId = manId;

  }

  setRole(empRole) {
    this.role = empRole;
  }

  // employee get details component interaction
  getInfo() {
    return this.info;
  }

  // manager get details component interaction
  getManager() {
    return this.manId;
  }

  setEmpProfile(profile) {
    this.empProfile = profile;
  }

  getEmpProfile() {
    return this.empProfile;
  }

}
