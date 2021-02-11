package com.hexaware.MLP252.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexaware.MLP252.factory.EmployeeFactory;
import com.hexaware.MLP252.factory.LeaveFactory;
import com.hexaware.MLP252.model.Employee;
import com.hexaware.MLP252.model.Leave;
// import com.hexaware.MLP252.util.EmailNotification;
// import com.hexaware.MLP252.persistence.LeaveDAO;

@Path("/leave")
public class LeaveRest {
    Date dateObj = new Date();
    DateFormat dateStamp = new SimpleDateFormat("yyyy-MM-dd");
    LocalDate currentDateStamp = LocalDate.parse(dateStamp.format(dateObj));
    int currentYear = currentDateStamp.getYear();
    Month currentMonthName = currentDateStamp.getMonth();
    int currentMonth = currentDateStamp.getMonthValue();
    int currentDate = currentDateStamp.getDayOfMonth();

    @GET
    @Path("/pendingLeaves/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public final Leave[] pendingLeaves(@PathParam("id") final int empId) {
        System.out.println("pending leaves");
        final Leave[] leaves = LeaveFactory.leavePendingList(empId);
        return leaves;
    }

    @GET
    @Path("/leaveHistory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public final Leave[] leaveHistory(@PathParam("id") final int empId) {
        System.out.println("pending leaves");
        final Leave[] leavesH = LeaveFactory.leaveHistory(empId);
        return leavesH;
    }

    @GET
    @Path("/pendingLeavesOfEmp/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public final Leave[] pendingLeavesOfEmployees(@PathParam("id") final int empId) {
        System.out.println("pending leaves");
        final Leave[] leavesP = LeaveFactory.pendingLeavesOfEmployees(empId);
        return leavesP;
    }

    @POST
    @Path("/applyLeave")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Leave applyLeave(Leave apply) throws AddressException, MessagingException {

        int empId = apply.getEmpId();

        int manId = apply.getManId();
        String startDate = apply.getStartDate();
        String endDate = apply.getEndDate();
        String leaveType = apply.getLeaveType();
        String reason = apply.getReason();
        String status = "Pending";
        String appliedOn = dateStamp.format(dateObj);
        Employee employee = EmployeeFactory.listById(empId);

        try {
            final Employee manager = EmployeeFactory.listById(employee.getManId());
            final String mailSub = "Attendance Tracker : Leave Application";
            final String mailbody = "<h1 style:\"color:red;background-color:black\">Leave Management System</h1><br /><p>Dear <b>"
                    + employee.getEmpName() + " </b> <br/>" + ",\n\nYour Leave request against(" + startDate + " to "
                    + endDate + ")has been submitted <p style= \"color:green \">Successfully for approval to <b>"
                    + manager.getEmpName()
                    + ".</b></p></p><br/> \n\n\n\n\n\n\n\nRegards,\nTeam MLP252\n\n---------\nThis is an auto-generated Email,Please do not reply";

            LeaveFactory.insertLeave(empId, manId, startDate, endDate, leaveType, reason, status, appliedOn);

            EmailNotification.sendEmail(employee.getEmpMail(), mailSub, mailbody);
            // EmailNotification.sendEmail(employee.getEmpEmail(), mailSub, mailbody);
            // System.out.println("/nEmail sent successfully/n");
            System.out.println("\n*********************leave inserted, thank you************************");

        } catch (NullPointerException e) {
            System.out.println("you are the CEO of the company");
        }

        System.out.println("inserted");

        return apply;

    }

    @PUT
    @Path("/approveLeave")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Leave approveLeave(Leave approve) {
        int leaveId = approve.getLeaveId();
        int leaveBalance = EmployeeFactory.listLeaveCount(leaveId);
        if (leaveBalance == 0) {
            System.out.println("Sorry, there is no balance in your leave count.");
        } else {
            String status = "Approved";
            EmployeeFactory.decrementLeaveCount(leaveId);
            LeaveFactory.update(status, leaveId);
        }
        return approve;
    }

    @PUT
    @Path("/rejectLeave")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Leave rejectLeave(Leave reject) {
        int leaveId = reject.getLeaveId();
        int leaveBalance = EmployeeFactory.listLeaveCount(leaveId);
        if (leaveBalance == 0) {
            System.out.println("Sorry, there is no such leaveId which status is pending.");
        } else {
            String status = "Rejected";
            LeaveFactory.update(status, leaveId);
        }
        return reject;
    }

    @PUT
    @Path("/addComments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Leave addComments(Leave comments) {
        int leaveId = comments.getLeaveId();
        String managerComments = comments.getManagerComments();
        String status = comments.getStatus();
        int leaveBalance = EmployeeFactory.listLeaveCount(leaveId);
        if (leaveBalance == 0) {
            System.out.println("Sorry, your available leaves are low ");
        } else {
            LeaveFactory.addComments(managerComments, status, leaveId);
        }
        return comments;
    }

    @DELETE
    @Path("/deleteLeave/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteLeave(@PathParam("id") int id) {
        LeaveFactory.deletingLatestLeave(id);
        System.out.println("Successfully deleted your latest pending leave.");
    }

}