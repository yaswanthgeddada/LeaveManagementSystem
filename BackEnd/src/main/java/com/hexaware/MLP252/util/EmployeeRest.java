package com.hexaware.MLP252.util;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexaware.MLP252.factory.EmployeeFactory;
import com.hexaware.MLP252.model.AuthModel;
import com.hexaware.MLP252.model.Employee;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/employees")
public class EmployeeRest {

	/**
	 * Returns a list of all the employees.
	 *
	 * @return a list of all the employees
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public final Employee[] employeesList() {
		System.out.println("Employees List");
		final Employee[] employees = EmployeeFactory.listAll();
		return employees;
	}

	/**
	 * Returns a specific employee's details.
	 *
	 * @param id the id of the employee
	 * @return the employee details
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public final Employee employeeListById(@PathParam("id") final int id) {
		final Employee empl = EmployeeFactory.listById(id);
		if (empl == null) {
			throw new NotFoundException("No such Employee ID: " + id);
		}
		return empl;
	}

	/**
	 * @param add It will add the employee
	 * @return addedEmployee
	 */
	@POST
	@Path("/addEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public final Employee addedEmployee(final Employee add) {
		int empId = add.getEmpId();
		int manId = add.getManId();
		String empName = add.getEmpName();
		String empMail = add.getEmpMail();
		String empPhone = add.getEmpPhone();
		String empJoinDate = add.getEmpJoinDate();
		int empLeaveCount = add.getEmpLeaveCount();
		String empRole = add.getEmpRole();

		EmployeeFactory.addEmployee(empId, manId, empName, empMail, empPhone, empJoinDate, empLeaveCount, empRole);

		System.out.println("added");
		return add;
	}

	/**
	 * @param changePhNo updating phone number.
	 * @param id         id of the employee.
	 * @return update Phone number.
	 */
	@PUT
	@Path("/updatePhNo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public final Employee updatePhNo(final Employee changePhNo, @PathParam("id") final int id) {
		String empPhone = changePhNo.getEmpPhone();

		EmployeeFactory.modifyEmpPhNo(id, empPhone);

		System.out.println("Your modification in data successfully updated.");
		return changePhNo;
	}

	/**
	 * @param result uses result.
	 * @return empLogin.
	 */
	@POST
	@Path("/empLogin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public final AuthModel empLogin(final AuthModel result) {
		int empId = result.getEmpId();
		String empPassword = result.getEmpPassword();
		Boolean isAuth = false;
		Employee check = EmployeeFactory.listById(empId);

		int id = check.getEmpId();
		String pass = check.getEmpPassword();

		if (empPassword.equals(pass)) {
			isAuth = true;
			result.setAuth(isAuth);

		} else {
			isAuth = false;
			result.setAuth(isAuth);
		}

		return result;

	}
}
