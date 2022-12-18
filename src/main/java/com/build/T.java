package com.build;

import java.net.URL;
import java.sql.*;
import javax.swing.ImageIcon;

public class T {
	 public static void main( String args[] ) {
	      Connection c = null;
	      URL url = T.class.getResource("/SQLite.db");
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:SQLite.db");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }
}
