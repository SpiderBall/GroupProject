package groupProject;

import java.util.*;
import java.sql.*;

public class UserAccount {
   
   //simple constructor
   UserAccount(String userIn, String passIn){
      username = userIn;
      password = passIn;
      messagesPosted = 0;
   }
   UserAccount(){
	   
   }
   
   public void changePassword(){
   //asks a user for a new password and then calls the backend password method with the information that they give
      System.out.println("Please enter your new password: ");
      Scanner in = new Scanner(System.in);
      String newPassword = in.next();
      changePassBackend(newPassword);
      System.out.println("Your password has been changed.");
      in.close();
   }
   
   public void changePassBackend(String newPassword){
   //this is here in case we ever need to change a password without going through the whole "asking the user stuff" business
   //for example: if we choose to implement a GUI
   //we would work this method in with that rather than calling the above one
      password = newPassword;
   }
   
   public int iterateCounter(){
   //adds 1 to the counter of (public?) messages posted
   //we're gonna call this whenever someone posts a public message
   //and this number is going to be displayed on the profile along with the other information
      messagesPosted++;
      return messagesPosted;
   }
   
   public String getName(){
      return username;
   }
   
   public String getPass(){
      return password;
   }
   
   public boolean doesUsernameExist(String new_username, Statement stat){
	   System.out.println("entering doesUsernameExist");
	
	   String lookupNameQuery = "SELECT username FROM userlist WHERE username = '" + new_username + "';";
	   ResultSet rs = null;
	   try{
		System.out.println("entering try");
	   	rs = stat.executeQuery(lookupNameQuery);
	   	
	   		
	   }catch(Exception e){
		   System.out.println("entering catch");
		   System.out.println(e);
	   }
	   
	   System.out.println(rs.toString());
	   
	   	if(rs.toString().equals(new_username)){
	   		return true;
	   	}else{
	   		return false;
	   	}
	   
   }
   
   
   public void followUser(UserAccount toBeFollowed){
      subscriptions.add(toBeFollowed.getName());
   }
   
   //DATA MEMBERS
   private String username;
   private String password;
   private int messagesPosted; //this should iterate every time the user posts a (public?) message with this account
   //there's a public method for that
   
   //ArrayList of objects of the PrivateMessage class
   //PLACEHOLDER BECAUSE I HAVEN'T WRITTEN THAT CLASS YET
   
   //ArrayList of names of fellow users that this user is following
   //store it as a username because there's really no point in storing pointers to user accounts when we will only be using the name
   private ArrayList<String> subscriptions = new ArrayList<String>();
}
