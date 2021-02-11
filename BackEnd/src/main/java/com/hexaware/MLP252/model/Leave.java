package com.hexaware.MLP252.model;

/**
 * Leave class with private variables.
 */
public class Leave {
  private int leaveId;
  private int empId;
  private int manId;
  private String startDate;
  private String endDate;
  private String leaveType;
  private String reason;
  private String status;
  private String appliedOn;
  private String managerComments;

  /**
   * Leave constructor.
   *
   * @param argLeaveId         leave id
   * @param argEmpId           EmpId
   * @param argManId           Id of the manager
   * @param argStartDate       start date
   * @param argEndDate         end date
   * @param argLeaveType       leave type
   * @param argReason          reason
   * @param argStatus          status
   * @param argAppliedOn       applied on
   * @param argManagerComments manager comments
   */
  public Leave(final int argLeaveId, final int argEmpId, final int argManId, final String argStartDate,
      final String argEndDate, final String argLeaveType, final String argReason, final String argStatus,
      final String argAppliedOn, final String argManagerComments) {
    this.leaveId = argLeaveId;
    this.empId = argEmpId;
    this.manId = argManId;
    this.startDate = argStartDate;
    this.endDate = argEndDate;
    this.leaveType = argLeaveType;
    this.reason = argReason;
    this.status = argStatus;
    this.appliedOn = argAppliedOn;
    this.managerComments = argManagerComments;
  }

  /**
   * @param argEmpId     id of the employee.
   * @param argManId     id of the manager.
   * @param argStartDate start date of the leave.
   * @param argEndDate   end date.
   * @param argLeaveType type of the leave.
   * @param argReason    reason for leave.
   * @param argStatus    status of leave.
   * @param argAppliedOn leave applied date.
   */
  public Leave(final int argEmpId, final int argManId, final String argStartDate, final String argEndDate,
      final String argLeaveType, final String argReason, final String argStatus, final String argAppliedOn) {
    this.empId = argEmpId;
    this.manId = argManId;
    this.startDate = argStartDate;
    this.endDate = argEndDate;
    this.leaveType = argLeaveType;
    this.reason = argReason;
    this.status = argStatus;
    this.appliedOn = argAppliedOn;
  }

  /**
   * default constructor.
   */
  public Leave() {
  }

  /**
   * @return String
   */
  public final int getLeaveId() {
    return leaveId;
  }

  /**
   * @param string leave id
   */
  public final void setLeaveId(final int string) {
    this.leaveId = string;
  }

  /**
   * @return String
   */
  public final String getStartDate() {
    return startDate;
  }

  /**
   * @param argStartDate start date
   */
  public final void setStartDate(final String argStartDate) {
    this.startDate = argStartDate;
  }

  /**
   * @return String
   */
  public final String getEndDate() {
    return endDate;
  }

  /**
   * @param argEndDate end date
   */
  public final void setEndDate(final String argEndDate) {
    this.endDate = argEndDate;
  }

  /**
   * @return String
   */
  public final String getLeaveType() {
    return leaveType;
  }

  /**
   * @param argLeaveType leave type
   */
  public final void setLeaveType(final String argLeaveType) {
    this.leaveType = argLeaveType;
  }

  /**
   * @return String
   */
  public final String getReason() {
    return reason;
  }

  /**
   * @param argReason reason
   */
  public final void setReason(final String argReason) {
    this.reason = argReason;
  }

  /**
   * @return String
   */
  public final String getStatus() {
    return status;
  }

  /**
   * @param argStatus status
   */
  public final void setStatus(final String argStatus) {
    this.status = argStatus;
  }

  /**
   * @return String
   */
  public final String getAppliedOn() {
    return appliedOn;
  }

  /**
   * @param argAppliedOn applied on
   */
  public final void setAppliedOn(final String argAppliedOn) {
    this.appliedOn = argAppliedOn;
  }

  /**
   * @return int
   */
  public final int getEmpId() {
    return empId;
  }

  /**
   * @param argEmpId setters method of emp ID
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * @return manId
   */
  public final int getManId() {
    return manId;
  }

  /**
   * @param manId id of the manager
   */
  public final void setManId(final int manId) {
    this.manId = manId;
  }

  /**
   * @return managerComments
   */
  public final String getManagerComments() {
    return managerComments;
  }

  /**
   * @param managerComments comments of a manager.
   */
  public final void setManagerComments(final String managerComments) {
    this.managerComments = managerComments;
  }

  @Override
  public final String toString() {
    return String.format(
        "%nID: %d, Employee_ID: %d, Manager_Id: %d, Start_Date: %s, End_Date: %s,Leave_Type: %s, Reason: %s, Status: %s, Applied@: %s",
        leaveId, empId, manId, startDate, endDate, leaveType, reason, status, appliedOn);
  }

}
