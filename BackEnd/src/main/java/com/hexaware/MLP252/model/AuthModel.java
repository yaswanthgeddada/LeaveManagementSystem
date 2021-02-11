package com.hexaware.MLP252.model;

public class AuthModel {
	/**
	 * AuthModel variables.
	 */
	private int empId;
	private String empPassword;
	private boolean isAuth;

	/**
	 *
	 * @param empId       employee id
	 * @param empPassword employee password
	 * @param isAuth      boolean indicating of signed in user.
	 */
	public AuthModel(final int empId, final String empPassword, final boolean isAuth) {
		this.empId = empId;
		this.empPassword = empPassword;
		this.isAuth = isAuth;
	}

	public AuthModel() {
	}

	/**
	 *
	 * @return empId
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 *
	 * @param empId employee id
	 */
	public void setEmpId(final int empId) {
		this.empId = empId;
	}

	/**
	 *
	 * @return empPassword
	 */
	public String getEmpPassword() {
		return empPassword;
	}

	/**
	 *
	 * @param empPassword employee password
	 */
	public void setEmpPassword(final String empPassword) {
		this.empPassword = empPassword;
	}

	/**
	 *
	 * @return isAuth
	 */
	public boolean isAuth() {
		return isAuth;
	}

	/**
	 *
	 * @param isAuth isAuth
	 */
	public void setAuth(final boolean isAuth) {
		this.isAuth = isAuth;
	}

}
