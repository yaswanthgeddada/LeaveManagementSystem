package com.hexaware.MLP252.model;

// import java.util.Objects;

/**
 * Employee class to store employee personal details.
 *
 * @author hexware
 */
public class Employee {

  /**
   * empId to store employee id.
   */
  private int empId;
  private String empPassword;
  private int manId;
  private String empName;
  private String empMail;
  private String empPhone;
  private String empJoinDate;
  private int empLeaveCount;
  private String empRole;
  private String empImageUrl;

  /**
   * Employee parametrized constructor.
   *
   * @param argEmpId         employee id
   * @param argEmpName       employee name
   * @param argEmpPassword   employee login password
   * @param argManId         managerId
   * @param argEmpMail       employee mail
   * @param argEmpPhone      employee phone
   * @param argEmpJoinDate   employee join date
   * @param argEmpLeaveCount employee leave count
   * @param argEmpImageUrl   url link for the image
   * @param argEmpRole       employee role
   */
  public Employee(final int argEmpId, final String argEmpPassword, final int argManId, final String argEmpName,
      final String argEmpMail, final String argEmpPhone, final String argEmpJoinDate, final int argEmpLeaveCount,
      final String argEmpRole, final String argEmpImageUrl) {
    this.empId = argEmpId;
    this.empPassword = argEmpPassword;
    this.manId = argManId;
    this.empName = argEmpName;
    this.empMail = argEmpMail;
    this.empPhone = argEmpPhone;
    this.empJoinDate = argEmpJoinDate;
    this.empLeaveCount = argEmpLeaveCount;
    this.empRole = argEmpRole;
    this.empImageUrl = argEmpImageUrl;
  }

  /**
   * @return String
   */
  public final int getEmpId() {
    return empId;
  }

  /**
   * @param argEmpId employee id
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * @return String
   */
  public final String getEmpName() {
    return empName;
  }

  /**
   * @param argEmpName employee name
   */
  public final void setEmpName(final String argEmpName) {
    this.empName = argEmpName;
  }

  /**
   * @return String
   */
  public final String getEmpMail() {
    return empMail;
  }

  /**
   * @param argEmpMail employee mail
   */
  public final void setEmpMail(final String argEmpMail) {
    this.empMail = argEmpMail;
  }

  /**
   * @return String
   */
  public final String getEmpPhone() {
    return empPhone;
  }

  /**
   * @param argEmpPhone employee phone
   */
  public final void setEmpPhone(final String argEmpPhone) {
    this.empPhone = argEmpPhone;
  }

  /**
   * @return String
   */
  public final String getEmpJoinDate() {
    return empJoinDate;
  }

  /**
   * @param argEmpJoinDate employee join date
   */
  public final void setEmpJoinDate(final String argEmpJoinDate) {
    this.empJoinDate = argEmpJoinDate;
  }

  /**
   * @return String
   */
  public final String getEmpRole() {
    return empRole;
  }

  /**
   * @param argEmpRole employee role
   */
  public final void setEmpRole(final String argEmpRole) {
    this.empRole = argEmpRole;
  }

  /**
   * @return manId
   */
  public final int getManId() {
    return manId;
  }

  /**
   * @param manId Id of the manager.
   */
  public final void setManId(final int manId) {
    this.manId = manId;
  }

  /**
   * default Constructor.
   */
  public Employee() {
  }

  /**
   * @return empLeaveCount.
   */
  public final int getEmpLeaveCount() {
    return empLeaveCount;
  }

  /**
   * @param argEmpLeaveCount Available leaves.
   */
  public final void setEmpLeaveCount(final int argEmpLeaveCount) {
    this.empLeaveCount = argEmpLeaveCount;
  }

  /**
   * @return empPassword
   */
  public final String getEmpPassword() {
    return empPassword;
  }

  /**
   * @param empPassword password of an employee.
   */
  public final void setEmpPassword(final String empPassword) {
    this.empPassword = empPassword;
  }

  /**
   * @return empImageUrl.
   */
  public final String getEmpImageUrl() {
    return empImageUrl;
  }

  /**
   * @param empImageUrl image Url
   */
  public final void setEmpImageUrl(final String empImageUrl) {
    this.empImageUrl = empImageUrl;
  }

  @Override
  public final String toString() {
    return String.format(
        "ID: %d, ManagerId:%d, Name: %s, Mail: %s, Contact: %s, Join_Date: %s, Leave_Count: %d, role: %s", empId, manId,
        empName, empMail, empPhone, empJoinDate, empLeaveCount, empRole);
  }
  // private int empId;
  // private int empId;

  // @Override
  // public final boolean equals(final Object obj) {
  // if (obj == null) {
  // return false;
  // }
  // if (getClass() != obj.getClass()) {
  // return false;
  // }
  // Employee emp = (Employee) obj;
  // if (Objects.equals(empId, emp.empId)) {
  // return true;
  // }
  // return false;
  // }

  // @Override
  // public final int hashCode() {
  // return Objects.hash(empId);
  // }

  /**
   * @param argEmpId to initialize employee id. used to get details through
   *                 constructor. //
   */
  // public Employee(final int argEmpId) {
  // this.empId = argEmpId;
  // }

  // /**
  // * @return this Employee's ID.
  // */
  // public final int getEmpId() {
  // return empId;
  // }

  // /**
  // * @param argEmpId to set employee id.
  // */
  // public final void setEmpId(final int argEmpId) {
  // this.empId = argEmpId;
  // }

}
