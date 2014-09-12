package com.google.gwt.sample.stockwatcher.server;

import java.sql.*;

import com.google.gwt.sample.stockwatcher.client.GreetingService;
import com.google.gwt.sample.stockwatcher.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;



/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

  public String greetServer(String userid, String userpwd) throws IllegalArgumentException 
  {
	  Connection con = DBConnectionSingleton.getInstance();
      Statement stmt = null;
      ResultSet rs = null;
	  try
	  {
	      stmt = con.createStatement();
	      StringBuffer query = new StringBuffer("SELECT * FROM Users WHERE Id == \"");
	      query.append(userid).append("\";");
	      rs = stmt.executeQuery( query.toString() );
	      if(rs.next())
	      {
	         String id = rs.getString("Id");
	         String  passwd = rs.getString("Password");
	         System.out.println( "ID = " + id );
	         System.out.println( "PASSWORD = " + passwd );
	         System.out.println();
	         
	         if(passwd.equals(userpwd))
	         {
	        	 return "Welcome, You are logged in!";
	         }
	      }
	      rs.close();
	      stmt.close();
	  }
	  catch (Exception e)
	  {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	  return "Invalid User";
	/*
    // Verify that the input is valid. 
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
        */
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   * 
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
}
