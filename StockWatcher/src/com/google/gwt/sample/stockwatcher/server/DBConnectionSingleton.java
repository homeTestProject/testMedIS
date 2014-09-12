package com.google.gwt.sample.stockwatcher.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DBConnectionSingleton {
	
	public static Connection c = null;
	
	public static synchronized Connection getInstance()
	{
		if(c == null)
		{
			System.out.println("creating new Connection instance");
		    try 
		    {
		    	Class.forName("org.sqlite.JDBC");
		        c = DriverManager.getConnection("jdbc:sqlite:wtest.db");
		    } 
		    catch ( Exception e ) 
		    {
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        System.exit(0);
		    }
		    System.out.println("Opened database successfully");
		}
		else
		{
			System.out.println("return cached Connection instance");
		}
		return c;
	}

}
