package com.hexaware.MLP252.persistence;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.logging.PrintStreamLog;;

/**
 */
public class DbConnection {
  /**
   * Connecting to MYSQL DB.
   * @return database Connection.
   */
  public final DBI getConnect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String dbc = System.getenv("DB_CONNECTION");
      if (dbc == null || dbc.equals("")) {
        dbc = "localhost:3306";
      }
      DBI dbi = new DBI("jdbc:mysql://" + dbc + "/MLP252?useSSL=false&serverTimezone=UTC", "MLP252", "MLP252");
     // DBI dbi = new DBI("jdbc:mysql://" + dbc + "/MLPXX&serverTimezone=UTC?useSSL=false", "MLPXX", "MLPXX");
      //DBI dbi = new DBI("jdbc:mysql://" + dbc + "/MLPXX?useSSL=false&serverTimezone=UTC", "MLPXX", "MLPXX");
      dbi.setSQLLog(new PrintStreamLog());
      return dbi;
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
