package com.hexaware.MLP252.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

import com.hexaware.MLP252.model.Employee;

/**
 * Mapper class to map from result set to employee object.
 *
 * @author hexware.
 */
public class EmployeeMapper implements ResultSetMapper<Employee> {
  /**
   * @param idx the index
   * @param rs  the resultset
   * @param ctx the context
   * @return the mapped employee object
   * @throws SQLException in case there is an error in fetching data from the
   *                      resultset
   */
  public final Employee map(final int idx, final ResultSet rs, final StatementContext ctx) throws SQLException {

    int empId = rs.getInt("EMP_ID");
    String empPassword = rs.getString("EMP_PASSWORD");
    int manId = rs.getInt("MANAGER_ID");
    String empName = rs.getString("EMP_NAME");
    String empMail = rs.getString("EMP_EMAILID");
    String empPhone = rs.getString("EMP_PHONENUMBER");
    String empJoinDate = rs.getString("EMP_JOIN_DATE");
    int empLeaveCount = rs.getInt("EMP_LEAVE_COUNT");
    String empRole = rs.getString("EMP_ROLE");
    String empImageUrl = rs.getString("EMP_IMAGE_URL");
    /**
     * @return Employee
     */
    Employee employee = new Employee(empId, empPassword, manId, empName, empMail, empPhone, empJoinDate, empLeaveCount,
        empRole, empImageUrl);
    return employee;
  }
}
