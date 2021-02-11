package com.hexaware.MLP252.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import com.hexaware.MLP252.factory.EmployeeFactory;
import com.hexaware.MLP252.persistence.EmployeeDAO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * Test class for Employee.
 */
@RunWith(JMockit.class)
public class EmployeeTest {

  /**
   * setup method.
   */
  @Before
  public void initInput() {

  }

  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testEmployee() {
    // Employee e100 = new Employee(100);
    // Employee e101 = new Employee(101);
    // assertNotEquals(e100, null);
    // // assertNotEquals(e100, new Integer(100));
    // assertEquals(e100, new Employee(100));
    // assertNotEquals(e101, new Employee(100));
    // assertEquals(e100.hashCode(), new Employee(100).hashCode());
    // assertEquals(e100.getEmpId(), new Employee(100).getEmpId());
    // e101.setEmpId(100);
    // assertEquals(e101, new Employee(100));

    Employee emp1 = new Employee(200, "123", 500, "Viji", "viji@gmail.com", "7123456789", "2020-09-10", 10, "Trainee",
        "");
    Employee emp2 = new Employee(201, "123", 501, "Viji", "viji@gmail.com", "7123456789", "2020-09-10", 10, "Trainee",
        "");
    assertNotEquals(emp2, emp1);
    assertNotEquals(emp1.hashCode(), emp2.hashCode());
    assertNotEquals(emp1.toString(), emp2.toString());

  }

  /**
   * Tests the constructor of employee class.
   */
  @Test
  public final void tesTEmployee() {
    Employee emp = new Employee();
  }

  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void getEmployeeMethods() {
    Employee e1 = new Employee(200, "123", 500, "Viji", "viji@gmail.com", "7123456789", "2020-09-10", 10, "Trainee",
        "");
    assertEquals(200, e1.getEmpId());
    assertEquals(500, e1.getManId());
    assertEquals("Viji", e1.getEmpName());
    assertEquals("viji@gmail.com", e1.getEmpMail());
    assertEquals("7123456789", e1.getEmpPhone());
    assertEquals("2020-09-10", e1.getEmpJoinDate());
    assertEquals(10, e1.getEmpLeaveCount());
    assertEquals("Trainee", e1.getEmpRole());
  }

  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testSetEmployee() {
    Employee e = new Employee(200, "123", 500, "Viji", "viji@gmail.com", "7123456789", "2020-09-10", 10, "Trainee",
        "https://firebasestorage.googleapis.com/v0/b/lms-app-429ed.appspot.com/o/employee_photos%2Fstevejobs.jpg?alt=media&token=28909514-ea70-4f76-940f-b6d8097b11eb");
    e.setEmpId(200);
    assertEquals(200, e.getEmpId());
    e.setEmpId(500);
    e.setEmpPassword("123");
    assertEquals("123", e.getEmpPassword());
    assertEquals(500, e.getManId());
    e.setEmpName("Viji");
    assertEquals("Viji", e.getEmpName());
    e.setEmpMail("viji@gmail.com");
    assertEquals("viji@gmail.com", e.getEmpMail());
    e.setEmpPhone("7123456789");
    assertEquals("7123456789", e.getEmpPhone());
    e.setEmpJoinDate("2020-09-10");
    assertEquals("2020-09-10", e.getEmpJoinDate());
    e.setEmpLeaveCount(10);
    assertEquals(10, e.getEmpLeaveCount());
    e.setEmpRole("Trainee");
    assertEquals("Trainee", e.getEmpRole());
    e.setManId(5000);
    assertEquals(5000, e.getManId());
    e.setEmpImageUrl(
        "https://firebasestorage.googleapis.com/v0/b/lms-app-429ed.appspot.com/o/employee_photos%2Fstevejobs.jpg?alt=media&token=28909514-ea70-4f76-940f-b6d8097b11eb");
    assertEquals(
        "https://firebasestorage.googleapis.com/v0/b/lms-app-429ed.appspot.com/o/employee_photos%2Fstevejobs.jpg?alt=media&token=28909514-ea70-4f76-940f-b6d8097b11eb",
        e.getEmpImageUrl());
  }

  /**
   * tests that empty employee list is handled correctly.
   *
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.list();
        result = new ArrayList<Employee>();
      }
    };
    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = EmployeeFactory.listAll();
    // assertEquals(0, es.length);
  }

  /**
   * Tests that a list with some employees is handled correctly.
   *
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllSome(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        ArrayList<Employee> es = new ArrayList<Employee>();
        // es.add(new Employee(1));
        // es.add(new Employee(10));
        // es.add(new Employee(100));
        dao.list();
        result = es;
      }
    };
    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = EmployeeFactory.listAll();
    // assertEquals(3, es.length);
    // assertEquals(new Employee(1), es[0]);
    // assertEquals(new Employee(10), es[1]);
    // assertEquals(new Employee(100), es[2]);
  }

  /**
   * Tests that a fetch of a specific employee works correctly.
   *
   * @param dao mocking the dao class
   */
  @Test
  public final void testListById(@Mocked final EmployeeDAO dao) {
    // final Employee e100 = new Employee(100);
    new Expectations() {
      {
        int n = 0;
        // dao.find(100);
        // // result = e100;
        // dao.find(-1);
        // result = null;
      }
    };
    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    // Employee e = EmployeeFactory.listById(100);
    // // 4444assertEquals(e100, e);

    // e = EmployeeFactory.listById(-1);
    // assertNull(e);
  }
}
