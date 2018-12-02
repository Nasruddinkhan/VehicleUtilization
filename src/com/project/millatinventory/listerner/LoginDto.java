package com.project.millatinventory.listerner;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.project.millatinventory.model.Users;

public class LoginDto implements HttpSessionBindingListener {
		  private boolean alreadyLoggedIn;
		   private static Map<Users, HttpSession> logins = new HashMap<Users, HttpSession>();

		  @Override
		  public void valueBound(HttpSessionBindingEvent event){
		    System.out.println("Bound");
			  HttpSession oldSession = logins.get((Users)event.getValue());
		    if (oldSession != null) {
		      alreadyLoggedIn = true;
		      System.out.println("Already  Login");
		    } else {
		      logins.put((Users)event.getValue(), event.getSession());
		    }

		    //Note: you can comment above code and remove comments from below code. removing comments from 
		 //below code will remove old session of user and let the user log-in from new session.

		    //HttpSession session = logins.remove(this);
		    //if (session != null) {
		    //  session.invalidate();
		   //}
		   //logins.put(this, event.getSession());  
		  }

		  @Override
		  public void valueUnbound(HttpSessionBindingEvent event) {
			  System.out.println("UnBound");
			  logins.remove((Users)event.getValue());
		  }

		}//end of class LoginDto


