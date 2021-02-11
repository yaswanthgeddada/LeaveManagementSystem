package com.hexaware.MLP252.persistence;

import java.util.List;

import com.hexaware.MLP252.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * The DAO class for employee.
 *
 * @author hexware
 */
public interface EmployeeDAO {
  /**
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * @param empId the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empId")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empId") int empId);

  /**
   * @param empId    id of the employee.
   * @param empPhone phone number of the employee.
   * @return employee id and phone number.
   */
  @SqlUpdate("UPDATE EMPLOYEE SET EMP_PHONENUMBER = :EMP_PHONENUMBER WHERE EMP_ID = :EMP_ID")
  int modifyingPhNo(@Bind("EMP_ID") int empId, @Bind("EMP_PHONENUMBER") String empPhone);

  /**
   * @param empId       id of the employee
   * @param empPassword password of the employee user
   * @return login details
   */
  @SqlQuery("Select * from employee where emp_id = :empId and EMP_PASSWORD = :empPassword")
  @Mapper(EmployeeMapper.class)
  Employee userAuth(@Bind("empId") int empId, @Bind("empPassword") String empPassword);

  /**
   * @param empId         Id of the employee.
   * @param manId         Id of the manager
   * @param empName       name of the employee.
   * @param empMail       mailId of the employee.
   * @param empPhone      phone number of the employee.
   * @param empJoinDate   Join date of employee.
   * @param empLeaveCount available leaves.
   * @param empRole       Role of the employee.
   * @return int.
   */

  @SqlUpdate("Insert into EMPLOYEE(EMP_ID,MANAGER_Id, EMP_NAME, EMP_EMAILID, EMP_PHONENUMBER, EMP_JOIN_DATE,EMP_LEAVE_COUNT,EMP_ROLE) values(:empId,:manId,:empName,:empMail,:empPhone,:empJoinDate,:empLeaveCount,:empRole)")
  int addEmployee(@Bind("empId") int empId, @Bind("manId") int manId, @Bind("empName") String empName,
      @Bind("empMail") String empMail, @Bind("empPhone") String empPhone, @Bind("empJoinDate") String empJoinDate,
      @Bind("empLeaveCount") int empLeaveCount, @Bind("empRole") String empRole);

  /**
   * @param leaveId id for leave.
   * @return leave id of employee.
   */
  @SqlQuery("SELECT EMP_LEAVE_COUNT FROM EMPLOYEE WHERE EMP_ID = (SELECT EMP_ID FROM LEAVE_TABLE WHERE LEAVE_ID = :LEAVE_ID AND LEAVE_STATUS = 'PENDING')")
  // @Mapper(LeaveMapper.class * EmployeeMapper.class)
  int getEmpLeaveCount(@Bind("LEAVE_ID") int leaveId);

  /**
   * @param leaveId id of leave.
   * @return leaveId.
   */
  @SqlUpdate("UPDATE EMPLOYEE SET EMP_LEAVE_COUNT = EMP_LEAVE_COUNT - 1 WHERE EMP_ID = (SELECT EMP_ID FROM LEAVE_TABLE WHERE LEAVE_ID = :LEAVE_ID);")
  @Mapper(LeaveMapper.class)
  int changeEmpLeaveCount(@Bind("LEAVE_ID") int leaveId);

  /**
   * selecting empId.
   *
   * @return all empId
   */
  @SqlQuery("SELECT EMP_ID FROM EMPLOYEE")
  // @Mapper(EmployeeMapper.class)
  List<Integer> listAllEmpIds();

  /**
   * close with no args is used to close the connection.
   */
  void close();
}
