package com.hexaware.MLP252.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.hexaware.MLP252.factory.EmployeeFactory;
import com.hexaware.MLP252.factory.LeaveFactory;
import com.hexaware.MLP252.persistence.EmployeeDAO;
import com.hexaware.MLP252.persistence.LeaveDAO;

// import com.hexaware.MLP252.factory.LeaveFactory;
// import com.hexaware.MLP252.persistence.LeaveDAO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
// import mockit.Expectations;
// import mockit.Mock;
// import mockit.MockUp;
// import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * Test class for Leave.
 */
@RunWith(JMockit.class)
public class LeaveTest {

  /**
   * setup method.
   */
  @Before
  public void initInput() {
    /**
     * setup method.
     */
  }

  /**
   * test the parameterized constructor.
   */
  @Test
  public final void testLeaveDetails() {
    Leave l = new Leave(100, 1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03",
        "No comments");
    assertEquals(100, l.getLeaveId());
    assertEquals("op", l.getLeaveType());
    assertNotEquals(l.hashCode(),
        new Leave(100, 1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03", "No comments")
            .hashCode());
  }

  /**
   * test the parameterized constructor.
   */
  @Test
  public final void testLeaveDetail() {
    Leave l = new Leave(100, 1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03",
        "No comments");
    assertEquals("2020-10-12", l.getStartDate());
    assertEquals("2020-10-15", l.getEndDate());
    assertEquals("op", l.getLeaveType());
    assertEquals("No comments", l.getManagerComments());
  }

  /**
   * test the parameterized constructor.
   */
  @Test
  public final void testLeave() {
    Leave l1 = new Leave(1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03");
    assertEquals("2020-10-12", l1.getStartDate());
    assertEquals("2020-10-15", l1.getEndDate());
    assertEquals("op", l1.getLeaveType());
    assertEquals("sick", l1.getReason());
    assertEquals("Pending", l1.getStatus());
    assertEquals("2020-10-03", l1.getAppliedOn());
  }

  /**
   * test the parameterized constructor.
   */
  @Test
  public final void testLeave1() {
    Leave lObj = new Leave();
  }

  /**
   * test the parameterized constructor.
   */
  @Test
  public final void testLeaveDetail1() {
    Leave l = new Leave(100, 1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03",
        "No comments");
    assertEquals("sick", l.getReason());
    assertEquals("Pending", l.getStatus());
    assertEquals(100, l.getLeaveId());
    assertEquals(1000, l.getEmpId());
  }

  /**
   * test the parameterized constructor.
   */
  @Test
  public final void testLeaveDetail2() {
    Leave l = new Leave(100, 1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03",
        "No comments");
    assertNotEquals("op", l.getReason());
    assertNotEquals("sick", l.getStatus());
    assertNotEquals("2020-10-04", l.getAppliedOn());

  }

  /**
   * test the equals method.
   */
  @Test
  public final void testEquals() {
    Leave l1 = new Leave(100, 1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03",
        "No comments");
    Leave l2 = new Leave(100, 1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03",
        "No comments");
    assertTrue(l1.equals(l1));
    assertFalse(l1.equals(l2));
  }

  /**
   * test for setLeaveId method.
   */
  @Test
  public final void testSetLeaveId() {
    Leave leave = new Leave(100, 1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03",
        "No comments");
    leave.setLeaveId(100);
    assertEquals(100, leave.getLeaveId());
    assertNotEquals(102, leave.getLeaveId());
    leave.setEmpId(1000);
    assertEquals(1000, leave.getEmpId());
    leave.setManId(500);
    assertEquals(500, leave.getManId());
    leave.setStartDate("2020-10-12");
    assertEquals("2020-10-12", leave.getStartDate());
    leave.setEndDate("2020-10-15");
    assertEquals("2020-10-15", leave.getEndDate());
    leave.setLeaveType("op");
    assertEquals("op", leave.getLeaveType());
    leave.setReason("sick");
    assertEquals("sick", leave.getReason());
    leave.setStatus("Pending");
    assertEquals("Pending", leave.getStatus());
    leave.setAppliedOn("2020-10-03");
    assertEquals("2020-10-03", leave.getAppliedOn());
    leave.setManagerComments("No comments");
    assertEquals("No comments", leave.getManagerComments());
    Leave lObj = new Leave();
  }

  /**
   *
   * This class is to test Pending Leaves.
   *
   * @param dao mocked
   *
   */
  public final void testApplyleave(@Mocked final LeaveDAO dao, @Mocked final EmployeeDAO dao1) {
    // myDict.put("sDate", (Date) sdf.parse("2020-11-05"));
    // myDict.put("eDate", (Date) sdf.parse("2020-11-06"));
    // myDict.put("appliedOn", (Date) sdf.parse("2020-11-05"));
    new Expectations() {
      {
        Employee emp = new Employee(1001, "1234", 500, "Steve Jobs", "stevejobs@gmail.com", "9542736477", "2010-10-07",
            10, "Develop",
            "https://firebasestorage.googleapis.com/v0/b/lms-app-429ed.appspot.com/o/employee_photos%2Fstevejobs.jpg?alt=media&token=28909514-ea70-4f76-940f-b6d8097b11eb");
        Employee emp1 = new Employee(1002, "1234", 500, "Mark", "Mark@gmail.com", "9542736477", "2010-10-07", 10,
            "Develop",
            "https://firebasestorage.googleapis.com/v0/b/lms-app-429ed.appspot.com/o/employee_photos%2Fmark.jpg?alt=media&token=97083bd9-7b14-4c54-95b1-2dabd9cf8749");
        dao1.list();
      }
    };
    new Expectations() {
      {
        // dao.applyLeave(01, 20, LocalDate.parse("2020-11-24"),
        // LocalDate.parse("2020-11-25"), "SL", "PENDING", "Sick");
        dao.insertLeave(1003, 500, "2020-03-10", "2020-03-14", "Personal", "Function", "Pending", "2020-03-01");

      }
    };
    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return dao1;
      }
    };
    new MockUp<LeaveFactory>() {
      @Mock
      LeaveDAO dao() {
        return dao;
      }
    };
    LeaveFactory leave = new LeaveFactory();
    LeaveDAO s1 = leave.insertLeave(1003, 500, "2020-03-10", "2020-03-14", "Personal", "Function", "Pending",
        "2020-03-01");
    assertEquals("leave inserted", s1);

  }

  /**
   * test the toString method.
   */
  @Test
  public final void testToString() {
    Leave leave = new Leave(100, 1000, 500, "2020-10-12", "2020-10-15", "op", "sick", "Pending", "2020-10-03",
        "no comments");
    assertNotEquals(leave, leave.toString());
  }
}
