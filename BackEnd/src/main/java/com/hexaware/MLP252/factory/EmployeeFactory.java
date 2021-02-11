package com.hexaware.MLP252.factory;

import java.util.List;

import com.hexaware.MLP252.model.Employee;
// import com.hexaware.MLP252.model.Leave;
import com.hexaware.MLP252.persistence.DbConnection;
import com.hexaware.MLP252.persistence.EmployeeDAO;
// import com.hexaware.MLP252.persistence.LeaveDAO;

/**
 * EmployeeFactory class used to fetch Employee data from database.
 *
 * @author hexware
 */
public class EmployeeFactory {
  /**
   * Protected constructor.
   */
  protected EmployeeFactory() {

  }

  /**
   * The dao for employee.
   */
  private static EmployeeDAO dao() {
    final DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * Call the data base connection.
   *
   * @return all employees' details
   */
  public static Employee[] listAll() {
    final List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }

  /**
   * @param id id to get employee details.
   * @return Employee
   */
  public static Employee listById(final int id) {
    return dao().find(id);
  }

  /**
   * @param empId         to get employee id.
   * @param manId         to get manager id.
   * @param empName       to get employee name.
   * @param empMail       to get employee mail.
   * @param empPhone      to get phone no of employee.
   * @param empJoinDate   to get join date of employee.
   * @param empLeaveCount to get count of the employee.
   * @param empRole       to get the role of the employee.
   * @return dao().
   */
  public final static EmployeeDAO addEmployee(final int empId, final int manId, final String empName,
      final String empMail, final String empPhone, final String empJoinDate, final int empLeaveCount,
      final String empRole) {
    final int emp = dao().addEmployee(empId, manId, empName, empMail, empPhone, empJoinDate, empLeaveCount, empRole);

    System.out.println("Added employees : " + emp);
    return dao();
  }

  /**
   * @param empId    id to get employee details.
   * @param empPhone to get phone number of employee.
   * @return Employee
   */
  // public static Employee listById(final String empId) {
  // return dao.find();
  // }

  public final static EmployeeDAO modifyEmpPhNo(final int empId, final String empPhone) {
    final int rowUpdated = dao().modifyingPhNo(empId, empPhone);
    System.out.println("No of Row(s) Updated : " + rowUpdated);
    return dao();
  }

  /**
   * Listing all EmpId.
   *
   * @return es.
   */
  public static List<Integer> listAllEmpId() {
    List<Integer> es = dao().listAllEmpIds();
    return es;
  }

  /**
   * @param leaveId leave id of the employee
   * @return leaveBalance
   */
  public static int listLeaveCount(final int leaveId) {
    final String ansiColor = "\u001B[35m";
    final String ansiResetColor = "\u001B[0m";
    final int leaveBalance = dao().getEmpLeaveCount(leaveId);
    if (leaveBalance != 0) {
      System.out.println(ansiColor + "This leave applied employee had " + leaveBalance
          + " more chance(s) to apply leave." + ansiResetColor);
    }
    return leaveBalance;
  }

  /**
   * @param leaveId leaveId parameter
   */
  public static void decrementLeaveCount(final int leaveId) {
    dao().changeEmpLeaveCount(leaveId);
  }
}
