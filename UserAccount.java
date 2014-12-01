package groupProject;

import java.util.*;
import java.sql.*;

public class UserAccount {
   
   UserAccount(String userIn, String passIn){
      username = userIn;
      password = passIn;
      messagesPosted = 0;
   }
   
   UserAccount(){
	   username = "";
	   password = "";
	   messagesPosted = 0;
   } //default constructor
   
   public void changePassword(Statement stat){
   //asks a user for a new password and then calls the backend password method with the information that they give
      System.out.println("Please enter your new password: ");
      Scanner input = new Scanner(System.in);
      String newPassword = input.next();
      changePassBackend(newPassword);
      System.out.println("Your password has been changed.");
      
      String changePassQuery = "UPDATE userlist " +
    		  					"SET password = '"+ newPassword + "'" +
								"WHERE username = '" + username + "' ;";
      try{
    	  stat.executeUpdate(changePassQuery);
      }catch(Exception e){
    	  System.out.println(e);
      }
  
   }
   
   
   
   public String getName(){
      return username;
   }
   
   public String getPass(){
      return password;
   }
   
   public boolean doesUsernameExist(String new_username, Statement stat){//checks if a username is in the system
	
	   String lookupNameQuery = "SELECT username FROM userlist WHERE username = '" + new_username + "';";
	   ResultSet rs = null;
	   try{
		   rs = stat.executeQuery(lookupNameQuery);
	   }catch(Exception e){
		   System.out.println(e);
	   }
	   
	   try{
		   boolean found = false;
		   String userName = null;
               while (rs.next()) {
                 userName = rs.getString("username");
                 
                 if(userName.equals(new_username)){
                	 found = true;
                	 break;
                 }
  			   	 else{continue;}
  		   	
               }
             return found;
               

	   }catch(Exception e2){
		   System.out.println(e2);
		   return false;
	   }
	   
   }
   
   public boolean isThisTheCorrectPassword(String entered_username, String entered_password, Statement stat){
	   
	   String lookupPasswordQuery = "SELECT password FROM userlist WHERE username = '" + entered_username + "';";
	   ResultSet rs = null;
	   try{
		   rs = stat.executeQuery(lookupPasswordQuery);
	   }catch(Exception e){
		   System.out.println(e);
	   }
	   
	   try{
		   boolean found = false;
		   String userPassword = null;
               while (rs.next()) {
                 userPassword = rs.getString("password"); 
                 if(userPassword.equals(entered_password)){
                	 found = true;
                	 break;
                 }
  				 else{continue;}
               }
              return found;
               
       }catch(Exception e2){
		   System.out.println(e2);
		   return false;
	   	}
   }
   
   
   public int getUserID(Statement stat){
	   int userID = 0;
	   
	   System.out.println(username);
	   
	   String retrieveUserIDQuery = "SELECT id FROM userlist WHERE username = '" + username +"';";
	   System.out.println(retrieveUserIDQuery);
	   ResultSet rs = null;
	   try{
	   		rs = stat.executeQuery(retrieveUserIDQuery);
	   }catch(Exception e){
		   System.out.println("entering catch");
		   System.out.println(e);
	   }
	   
	   
	   try{
           while (rs.next()) {
             userID = rs.getInt("id"); 
             System.out.println(userID);
             return userID;
           }
      }catch(Exception e2){
		   System.out.println(e2);
	   	}
	   
	   return userID;
	   
   }
 
   
   public void followUser(UserAccount toBeFollowed){
      subscriptions.add(toBeFollowed.getName());
   }
   
   //DATA MEMBERS
   private String username;
   private String password;
   private int messagesPosted; //this should iterate every time the user posts a (public?) message with this account
   //there's a public method for that
   
   //ArrayList of names of fellow users that this user is following
   //store it as a username because there's really no point in storing pointers to user accounts when we will only be using the name
   private ArrayList<String> subscriptions = new ArrayList<String>();
}
