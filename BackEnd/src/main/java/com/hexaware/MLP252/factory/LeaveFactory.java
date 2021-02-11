package com.hexaware.MLP252.factory;

import java.util.List;

import com.hexaware.MLP252.model.Leave;

// import org.jvnet.hk2.internal.SystemDescriptor;

import com.hexaware.MLP252.persistence.DbConnection;
import com.hexaware.MLP252.persistence.LeaveDAO;

/**
 * LeaveFactory class to store leave details.
 *
 * @author hexaware
 */
public class LeaveFactory {
  /**
   * @return LeaveDAO
   */
  private static LeaveDAO call() {
    final DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDAO.class);
  }

  /**
   * @param empId employeeID
   * @return Leave[]
   */
  // employee can see this api
  public static Leave[] leaveHistory(final int empId) {
    final List<Leave> es = call().find(empId);
    return es.toArray(new Leave[es.size()]);
  }

  // manager can see this api
  /**
   * @param manId uses managerId.
   * @return es.toArrat().
   */
  public static Leave[] pendingLeavesOfEmployees(final int manId) {
    final List<Leave> es = call().pendingLeavesOfEmployees(manId);
    return es.toArray(new Leave[es.size()]);
  }

  /**
   * @return LeaveDAO
   * @param status  tells the status
   * @param leaveId LeaveId
   */
  public final static LeaveDAO update(final String status, final int leaveId) {
    // final DbConnection db = new DbConnection();
    // final LeaveDAO leaveDAO = db.getConnect().onDemand(LeaveDAO.class);
    final int rowsUpdated = call().update(status, leaveId);
    System.out.println("No of Rows Updated : " + rowsUpdated);
    return call();
  }

  /**
   * @param managerComments Comments of manager.
   * @param status          status of leave.
   * @param leaveId         leaveId of the employee.
   * @return call().
   */
  public final static LeaveDAO addComments(final String managerComments, final String status, final int leaveId) {
    final int rowsUpdated = call().addComments(managerComments, status, leaveId);
    System.out.println("No of Rows Updated : " + rowsUpdated);
    return call();
  }

  /**
   * @param empId Id of the employee.
   * @return Leave[].
   */
  public static Leave[] leavePendingList(final int empId) {
    final List<Leave> es = call().leavePendingList(empId);
    return es.toArray(new Leave[es.size()]);
  }

  /**
   * @param empId     EmpId is inserted.
   * @param manId     ManagerId is inserted.
   * @param startDate Start date.
   * @param endDate   End date.
   * @param leaveType Leave Type is declared.
   * @param reason    Reason should be given.
   * @param status    Status should be shown here.
   * @param appliedOn applied date.
   * @return LeaveDAO returns to Leave dao.
   */
  public final static LeaveDAO insertLeave(final int empId, final int manId, final String startDate,
      final String endDate, final String leaveType, final String reason, final String status, final String appliedOn) {
    final int rowsInserted = call().insertLeave(empId, manId, startDate, endDate, leaveType, reason, status, appliedOn);
    System.out.println("Rows Inserted : " + rowsInserted);
    return call();
  }

  /**
   * @param id id of the employee
   * @return call().
   */
  public static LeaveDAO deletingLatestLeave(final int id) {
    final int deleteRow = call().deletingLatestLeaveByEmpId(id);
    System.out.println("Rows Inserted : " + deleteRow);
    return call();
  }

  /**
   * @param empId employee id
   * @return call().
   */
  public static String getPreviousLeaveDate(final int empId) {
    return call().gettingPreviousLeaveDate(empId);
  }

}
