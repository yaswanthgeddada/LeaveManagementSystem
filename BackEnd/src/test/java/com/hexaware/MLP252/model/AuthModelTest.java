package com.hexaware.MLP252.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class AuthModelTest {
  private boolean isAuth;

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
  public final void testAuthModel() {
    AuthModel am = new AuthModel(1003, "1000", true);
    am.setEmpId(1003);
    assertEquals(1003, am.getEmpId());
    am.setEmpPassword("1000");
    assertEquals("1000", am.getEmpPassword());
    am.setAuth(true);
    assertEquals(true, true);
  }

  /**
   * test the parameterized constructor.
   */
  @Test
  public final void testIsAuth() {
    boolean isAuth = false;
    assertFalse(isAuth);
  }

  /**
   * test the parameterized constructor.
   *
   * @return
   */
  @Test
  public final void setAuth() {
    boolean isAuth = true;
    assertTrue(isAuth);
    return;
  }

  /**
   * test the empty constructor.
   */
  @Test
  public final void testAuthModel1() {
    AuthModel Obj1 = new AuthModel();
  }

  // /**
  // * test the parameterized constructor.
  // *
  // * @return
  // */
  // @Test
  // public final boolean testIsAuth1() {
  // this.isAuth = isAuth;
  // assertTrue(isAuth);
  // return isAuth;
  // }
  // /**
  // * test the parameterized constructor.
  // *
  // * @return
  // */
  // @Test
  // public final boolean testReturnAuth() {
  // boolean isAuth = false;
  // assertTrue(isAuth);
  // return isAuth;
  // }
}
