package com.hexaware.MLP252.persistence;

import com.hexaware.MLP252.model.Leave;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * LeaveDAO interface.
 */
public interface LeaveDAO {
  /**
   * Sql Query.
   *
   * @param empId Employee Id
   * @return List<Leave>
   */
  @SqlQuery("SELECT * FROM LEAVE_TABLE WHERE EMP_ID = :empId ")
  @Mapper(LeaveMapper.class)
  List<Leave> find(@Bind("empId") int empId);

  /**
   * @param manId id of manager.
   * @return list.
   */
  @SqlQuery("select * from leave_table where leave_status = 'Pending' and manager_id=:manId")
  @Mapper(LeaveMapper.class)
  List<Leave> pendingLeavesOfEmployees(@Bind("manId") int manId);

  /**
   * part1 of the sqlquery.
   */
  String PART1 = "Insert into LEAVE_TABLE(EMP_ID,MANAGER_ID,START_DATE,END_DATE,";
  /**
   * part2 of the sqlquery.
   */
  String PART2 = "LEAVE_TYPE,REASON,LEAVE_STATUS,APPLIED_ON) ";
  /**
   * part3 of the sqlquery.
   */
  String PART3 = "values(:empId,:manId,:startDate,:endDate,:leaveType,:reason,:status,:appliedOn)";

  /**
   * Sql Query.
   * @param empId     emp id
   * @param manId     manager id
   * @param startDate start date
   * @param endDate   end data
   * @param leaveType leave type
   * @param reason    reason
   * @param status    status
   * @param appliedOn applied
   * @return int
   */
  @SqlUpdate(PART1 + PART2 + PART3)
  int insertLeave(@Bind("empId") int empId, @Bind("manId") int manId, @Bind("startDate") String startDate,
      @Bind("endDate") String endDate, @Bind("leaveType") String leaveType, @Bind("reason") String reason,
      @Bind("status") String status, @Bind("appliedOn") String appliedOn);

  /**
   * Sql Query.
   *
   * @param status  status
   * @param leaveId leave id
   * @return int
   */
  @SqlUpdate("UPDATE LEAVE_TABLE SET LEAVE_STATUS= :status WHERE LEAVE_ID = :leaveId")
  int update(@Bind("status") String status, @Bind("leaveId") int leaveId);

  /**
   * @param managerComments Comments of a manager.
   * @param status          status of leave.
   * @param leaveId         id of leave.
   * @return int.
   */
  @SqlUpdate("UPDATE LEAVE_TABLE SET manager_comments=:managerComments , Leave_status =:status WHERE LEAVE_ID =:leaveId; ")
  int addComments(@Bind("managerComments") String managerComments, @Bind("status") String status,
      @Bind("leaveId") int leaveId);

  // @SqlUpdate("UPDATE LEAVE_TABLE SET STATUS= :status WHERE LEAVE_ID =
  // :leaveId")
  // int update(@Bind("leaveId") String leaveId, @Bind("status") String status);
  /**
   * Sql Query.
   *
   * @return List<Leave>
   */
  /**
   * @param empId id of the employee.
   * @return list.
   */
  @SqlQuery("SELECT * FROM LEAVE_TABLE WHERE LEAVE_STATUS = 'Pending' and EMP_ID = :empId")
  @Mapper(LeaveMapper.class)
  List<Leave> leavePendingList(@Bind("empId") int empId);

  /**
   * @param id id of the employee.
   * @return deleting latest empId.
   */
  @SqlUpdate("DELETE FROM LEAVE_TABLE WHERE EMP_ID = :EMP_ID  AND LEAVE_STATUS = 'pending' ORDER BY LEAVE_ID DESC LIMIT 1")
  int deletingLatestLeaveByEmpId(@Bind("EMP_ID") int id);

  /**
   * @param empId id of the employee.
   * @return getting previous leave date.
   */
  @SqlQuery("SELECT APPLIED_ON FROM LEAVE_TABLE WHERE EMP_ID = :EMP_ID AND (LEAVE_STATUS='APPROVED' OR LEAVE_STATUS='PENDING') ORDER BY LEAVE_ID DESC LIMIT 1")
  String gettingPreviousLeaveDate(@Bind("EMP_ID") int empId);

  /**
   * closed the connection.
   */
  void close();

}
