package com.hexaware.MLP252.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.hexaware.MLP252.factory.EmployeeFactory;
import com.hexaware.MLP252.factory.LeaveFactory;
import com.hexaware.MLP252.model.Employee;
import com.hexaware.MLP252.model.Leave;

/**
 * Class CliMain provides the command line interface to the leave management.
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");
  Date dateObj = new Date();
  DateFormat dateStamp = new SimpleDateFormat("yyyy-MM-dd");
  LocalDate currentDateStamp = LocalDate.parse(dateStamp.format(dateObj));
  int currentYear = currentDateStamp.getYear();
  Month currentMonthName = currentDateStamp.getMonth();
  int currentMonth = currentDateStamp.getMonthValue();
  int currentDate = currentDateStamp.getDayOfMonth();

  /**
   *
   * @throws AddressException   handling exception
   * @throws MessagingException handling exception
   */
  private void mainMenu() throws AddressException, MessagingException {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. Apply For Leave");
    System.out.println("4. View The Leave History");
    System.out.println("5. View the Pending Leaves");
    System.out.println("6. Approve the Leave");
    System.out.println("7. Add a new Employee");
    System.out.println("8. Update employee phone number");
    System.out.println("9. Delete your latest unapproved(pending) leave application.");
    System.out.println("10. Validations of empID");
    System.out.println("11. Exit from this ");
    System.out.println("Enter your choice:");
    int menuOption = option.nextInt();
    mainMenuDetails(menuOption);
  }

  /**
   *
   * @param selectedOption selectedOption
   * @throws AddressException   handling exception
   * @throws MessagingException handling exception
   */
  private void mainMenuDetails(final int selectedOption) throws AddressException, MessagingException {
    switch (selectedOption) {
      case 1:
        listEmployeesDetails();
        break;
      case 2:
        listEmployeeDetail();
        break;
      case 3:
        applyLeave();
        break;
      case 4:
        leaveHistory();
        break;
      case 5:
        leavePending();
        break;
      case 6:
        approveLeave();
        break;
      case 7:
        addEmployee();
        break;
      case 8:
        updateEmployeePhNo();
        break;
      case 9:
        deleteLatestPendingLeave();
        break;
      case 10:
        empIdValid();
        break;
      case 11:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        Runtime.getRuntime().halt(0);
      default:
        System.out.println("Choose either 1, 2 or 3");
    }
    mainMenu();
  }

  private void deleteLatestPendingLeave() {
    System.out.print("Enter your Employee Id : ");
    int empId = option.nextInt();
    if (empId == 0 || empId < 0) {
      System.out.println("Sorry, No such employee");
      deleteLatestPendingLeave();
    }

    LeaveFactory.deletingLatestLeave(empId);

    System.out.println("Your request for deleting latest unapproved leave was successfully.");
  }

  private void updateEmployeePhNo() {
    System.out.print("Enter your Employee Id : ");
    int empId = option.nextInt();
    if (empId == 0 || empId < 0) {
      System.out.println("Sorry, No such employee");
      updateEmployeePhNo();
    }
    option.nextLine();
    System.out.print("Enter the new phone number : ");
    String empNewPhoneNo = option.nextLine();

    EmployeeFactory.modifyEmpPhNo(empId, empNewPhoneNo);

    System.out.println("Your request for updating mobile number was successfully.");
  }

  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    option.nextLine();
    int empId = option.nextInt();
    Employee employee = EmployeeFactory.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      // System.out.println(employee.getEmpId());
      // System.out.println(employee.getEmpName());
      // System.out.println(employee.getEmpMail());
      // System.out.println(employee.getEmpPhone());
      // System.out.println(employee.getEmpJoinDate());
      // System.out.println(employee.getEmpRole());
      System.out.println(employee);
    }
  }

  private void listEmployeesDetails() throws AddressException, MessagingException {
    Employee[] employee = EmployeeFactory.listAll();
    String ansiReset = "\u001B[35m";
    for (Employee e : employee) {
      // System.out.println(e.getEmpId());
      // System.out.println(e.getEmpName());
      // System.out.println(e.getEmpMail());
      // System.out.println(e.getEmpPhone());
      // System.out.println(e.getEmpJoinDate());
      // System.out.println(e.getEmpRole());
      System.out.println(ansiReset + e);
    }
    System.out.println("\u001B[0m");
    mainMenu();
  }

  // pending leaves of employees under manager
  private void leaveHistory() {

    System.out.println("Enter the employee ID");
    int empId = option.nextInt();
    if (empId == 0 || empId < 0) {
      System.out.println("Sorry, No such employee");
      leaveHistory();
    }
    Leave[] leave = LeaveFactory.leaveHistory(empId);
    for (Leave e : leave) {
      System.out.println("\u001B[35m" + e);
    }
    System.out.println("\u001B[0m");
  }

  private void leavePending() {
    System.out.println("Enter the employee ID");
    int empId = option.nextInt();
    if (empId == 0 || empId < 0) {
      System.out.println("Sorry, No such employee");
      leavePending();
    }
    Leave[] leave = LeaveFactory.leavePendingList(empId);
    String ansiReset = "\u001B[35m";

    for (Leave e : leave) {
      System.out.println(ansiReset + e);
    }
  }

  private void approveLeave() {
    String ansiColor = "\u001B[35m";
    String ansiResetColor = "\u001B[0m";
    System.out.println("Enter Leave Id:");
    int leaveId = option.nextInt();
    if (leaveId == 0 || leaveId < 0) {
      System.out.println("Sorry, No such leave application.");
      approveLeave();
    }
    int leaveBalance = EmployeeFactory.listLeaveCount(leaveId);
    if (leaveBalance == 0) {
      System.out.println(ansiColor + "Sorry, there is no such leaveId which status is pending." + ansiResetColor);
    } else {
      System.out.println(ansiColor + "Press 1 to Approve \nPress 2 to Reject" + ansiResetColor);
      int ref = option.nextInt();
      String status;
      if (ref == 1) {
        status = "Approved";
        EmployeeFactory.decrementLeaveCount(leaveId);
      } else {
        status = "Rejected";
      }
      LeaveFactory.update(status, leaveId);
    }
  }

  /**
   *
   * @throws AddressException   handling exception
   * @throws MessagingException handing exception
   */
  private void applyLeave() throws AddressException, MessagingException {
    String ansiColor = "\u001B[35m";
    String ansiResetColor = "\u001B[0m";
    System.out.println("insert empId");
    int empId = option.nextInt();
    if (empId == 0 || empId < 0) {
      System.out.println("Sorry, No such employee");
      applyLeave();
    }
    Employee employee = EmployeeFactory.listById(empId);
    // Employee manager = EmployeeFactory.listById(manId);
    System.out.println("Insert Manager_Id");
    int manId = option.nextInt();
    try {
      option.nextLine(); // new Line for Scanner class
      System.out.println("insert startDate (YYYY-MM-DD)");
      String startDate = option.nextLine();
      LocalDate startDate1 = LocalDate.parse(startDate);
      LocalDate previousLeaveDate = LocalDate.parse(LeaveFactory.getPreviousLeaveDate(empId));
      if (startDate1.getYear() < previousLeaveDate.getYear()) {
        System.out.println(ansiColor + "You are entered a past date to your previous leave. Please do start from first."
            + ansiResetColor);
        applyLeave();
      } else if (startDate1.getYear() == previousLeaveDate.getYear()
          && startDate1.getMonthValue() < previousLeaveDate.getMonthValue()) {
        System.out.println(ansiColor + "You are entered a past date to your previous leave. Please do start from first."
            + ansiResetColor);
        applyLeave();
      } else if (startDate1.getYear() == previousLeaveDate.getYear()
          && startDate1.getMonthValue() == previousLeaveDate.getMonthValue()
          && startDate1.getDayOfMonth() < previousLeaveDate.getDayOfMonth()) {
        System.out.println(ansiColor + "You are entered a past date to your previous leave. Please do start from first."
            + ansiResetColor);
        applyLeave();
      } else if (startDate1.getYear() < currentYear) {
        System.out.println(
            ansiColor + "You entered past date. Please do enter a future date and start from first." + ansiResetColor);
        applyLeave();
      } else if (startDate1.getYear() == currentYear && startDate1.getMonthValue() < currentMonth) {
        System.out.println(
            ansiColor + "You entered past date. Please do enter a future date and start from first." + ansiResetColor);
        applyLeave();
      } else if (startDate1.getYear() == currentYear && startDate1.getMonthValue() == currentMonth
          && startDate1.getDayOfMonth() < currentDate) {
        System.out.println(
            ansiColor + "You entered past date. Please do enter a future date and start from first." + ansiResetColor);
        applyLeave();
      }
      System.out.println("insert endDate (YYYY-MM-DD)");
      String endDate = option.nextLine();
      LocalDate endDate1 = LocalDate.parse(endDate);
      if (endDate1.getYear() < startDate1.getYear()) {
        System.out.println(
            ansiColor + "You entered past date to your start date. Please do start from first." + ansiResetColor);
        applyLeave();
      } else if (endDate1.getYear() == startDate1.getYear() && endDate1.getMonthValue() < currentMonth) {
        System.out.println(
            ansiColor + "You entered past date to your start date. Please do start from first." + ansiResetColor);
        applyLeave();
      } else if (startDate1.getYear() == currentYear && startDate1.getMonthValue() == startDate1.getMonthValue()
          && endDate1.getDayOfMonth() < startDate1.getDayOfMonth()) {
        System.out.println(
            ansiColor + "You entered past date to your start date. Please do start from first." + ansiResetColor);
        applyLeave();
      }
      System.out.println("insert leaveType");
      System.out.println("Press 1 to Earned Leave \nPress 2 to Optional Leave");
      int ref = option.nextInt();
      String leaveType;
      if (ref == 1) {
        leaveType = "Earned Leave";
      } else {
        leaveType = "Optional Leave";
      }
      option.nextLine();
      System.out.println("insert reason");
      String reason = option.nextLine();
      String status = "Pending";
      String appliedOn = dateStamp.format(dateObj);
      // LeaveFactory leave = new LeaveFactory();
      // LeaveFactory.insertLeave(empId, manId, startDate, endDate, leaveType, reason,
      // status, appliedOn);

      final Employee manager = EmployeeFactory.listById(employee.getManId());
      final String mailSub = "Attendance Tracker : Leave Application";
      final String mailbody = "<h1 style:\"color:red;background-color:black\">Leave Management System</h1><br /><p>Dear <b>"
          + employee.getEmpName() + " </b> <br/>" + ",\n\nYour Leave request against(" + startDate + " to " + endDate
          + ")has been submitted <p style= \"color:green \">Successfully for approval to <b>" + manager.getEmpName()
          + ".</b></p></p><br/> \n\n\n\n\n\n\n\nRegards,\nTeam MLP252\n\n---------\nThis is an auto-generated Email,Please do not reply";
      LeaveFactory.insertLeave(empId, manId, startDate, endDate, leaveType, reason, status, appliedOn);

      EmailNotification.sendEmail(employee.getEmpMail(), mailSub, mailbody);
      // EmailNotification.sendEmail(employee.getEmpEmail(), mailSub, mailbody);
      // System.out.println("/nEmail sent successfully/n");
      System.out.println("\n*********************leave inserted, thank you************************");

    } catch (NullPointerException e) {
      System.out.println("you are the CEO of the company");
    } catch (DateTimeParseException e) {
      System.out.println("You entered an invalid date.");
      applyLeave();
    }

  }

  private void addEmployee() {
    System.out.println("Insert employeeId");
    int empId = option.nextInt(); // adds a new employeeId
    System.out.println("Insert managerId");
    int manId = option.nextInt(); // adds a new ManagerId(manId)
    option.nextLine(); // new Line for Scanner class
    System.out.println("Insert EmployeeName");
    String empName = option.nextLine();
    System.out.println("Insert EmployeeMailId");
    String empMail = option.nextLine();
    System.out.println("Insert EmployeePhNo");
    String empPhone = option.nextLine();
    System.out.println("Enter JoinDate");
    String empJoinDate = option.nextLine();
    System.out.println("Insert Employee Leave Count");
    int empLeaveCount = option.nextInt();
    option.nextLine(); // new Line for Scanner class
    System.out.println("Enter the Role");
    String empRole = option.nextLine();
    EmployeeFactory.addEmployee(empId, manId, empName, empMail, empPhone, empJoinDate, empLeaveCount, empRole);

  }

  /**
   * Method to check whether given employee id is valid or not.
   */
  public final void empIdValid() {
    try {
      System.out.println("Enter employee Id");
      int j = option.nextInt();

      List<Integer> emp = EmployeeFactory.listAllEmpId();
      for (int e1 : emp) {
        if (e1 < 0 || e1 == 0) {
          System.out.println("Employee Id is not found in the database");
        } else if (e1 == j) {
          System.out.println(" Employee is found with such employee id");
          break;
        }
      }
    } catch (InputMismatchException e) {
      System.out.println(" Employee Id should be an integer");
    }

  }

  /*
   * private void InsertLeave() { LeaveDAO leaveDAO =
   * db.getConnect().onDemand(LeaveDAO.class); int rowsinserted =
   * leaveDAO.insertLeave("001","2020-10-27","2020-10-28","OP","festival",
   * "pending","2020-10-26"); }
   */
  /**
   * main method is the basic entry point for the application.
   *
   * @param ar the list of arguments
   * @throws MessagingException handling exception
   * @throws AddressException   handling exception
   */
  public static void main(final String[] ar) throws AddressException, MessagingException {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}
