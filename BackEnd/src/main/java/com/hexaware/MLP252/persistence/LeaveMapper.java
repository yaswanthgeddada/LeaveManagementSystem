package com.hexaware.MLP252.persistence;

import com.hexaware.MLP252.model.Leave;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * LeaveMapper class.
 */
public class LeaveMapper implements ResultSetMapper<Leave> {
  /**
   * @param ctx       the context
   * @param index     index
   * @param resultSet index
   * @return the mapped employee object
   * @throws SQLException in case there is an error in fetching data from the
   *                      resultset
   */

  public final Leave map(final int index, final ResultSet resultSet, final StatementContext ctx) throws SQLException {
    final int leaveId = resultSet.getInt("LEAVE_ID");
    final int empId = resultSet.getInt("EMP_ID");
    final int manId = resultSet.getInt("MANAGER_ID");
    final String startDate = resultSet.getString("START_DATE");
    final String endDate = resultSet.getString("END_DATE");
    final String leaveType = resultSet.getString("LEAVE_TYPE");
    final String reason = resultSet.getString("REASON");
    final String status = resultSet.getString("LEAVE_STATUS");
    final String appliedOn = resultSet.getString("APPLIED_ON");
    final String managerComments = resultSet.getString("MANAGER_COMMENTS");

    // leaveId,startDate,endDate,leaveType,reason,status,AppliedOn

    final Leave leave = new Leave(leaveId, empId, manId, startDate, endDate, leaveType, reason, status, appliedOn,
        managerComments);

    return leave;

  }

}
