package groupProject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;


public class GroupProject {


	   public static void main(String[] args){
	      getConnection();
	      String menu_input = "";
	      Scanner input = new Scanner(System.in);
	      
	      while(!menu_input.equalsIgnoreCase("Q")){
			      while(!loggedOn){
			    	  
			    	  	printGuestMenu();

						menu_input = input.nextLine();					
		
						if(menu_input.equals("1")){
							createAccount(currentUser);
						}
						else if(menu_input.equals("2")){
							login();
						}
						else if(menu_input.equals("3")){
							postMessage();
							
						}
						else if(menu_input.equals("4")){
							showPublicMessages();
							
						}
						else if(menu_input.equals("5")){

							System.out.println("Please enter the name of the user you would like to search.");
							String searchedUserName = input.nextLine();
							searchUser(searchedUserName);
							System.out.println("Would you like to:");
							System.out.println("1: Subscribe to this user");
							System.out.println("2: View this user's profile description");
							System.out.println("3: View this user's messages?");
							
							String menu_input2 = input.nextLine();
							
							if(menu_input2.equals("1")){
								
								System.out.println("Subscribing to users is currently unavailable, but check again soon!");
								
							}else if(menu_input2.equals("2")){
								
								System.out.println("one moment...");
								viewDescription(searchedUserName);
								
								
							}else if(menu_input2.equals("3")){
								
								viewMessagesFromUser(searchedUserName);
								
							}else{
								System.out.println("Please enter one of the choices specified above.");
							}
							
							
							
							
						}else if(menu_input.equals("6")){
							

							System.out.println("Searching for a hashtag is currently unavailable, but check again soon!");
							
						}
						else if(menu_input.equalsIgnoreCase("Q")){
							System.out.println("Have a nice day!");
							break;
						}else{
							System.out.println("Please enter one of the choices specified above.");
						}//end else
						
					}//end while not logged in
			      
			      while(loggedOn){
			    	  
			    	  	printUserMenu();

						menu_input = input.nextLine();
						
						if(menu_input.equals("1")){
							
							currentUser.changePassword(stat);
							
						}
						else if(menu_input.equals("2")){
							
							loggedOn = false;
							
						}
						else if(menu_input.equals("3")){
							
							postMessage();
							
						}
						else if(menu_input.equals("4")){
							
							showPublicMessages();
						}
						else if(menu_input.equals("5")){
							System.out.println("Please enter the name of the user you would like to search.");
							String searchedUserName = input.nextLine();
							searchUser(searchedUserName);
							
							System.out.println("Would you like to:");
							System.out.println("1: Subscribe to this user");
							System.out.println("2: View this user's profile description");
							System.out.println("3: View this user's messages?");
							
							String menu_input2 = input.nextLine();
							
							if(menu_input2.equals("1")){
								
								System.out.println("Subscribing to users is currently unavailable, but check again soon!");
								
							}else if(menu_input2.equals("2")){
								
								//System.out.println("Viewing user descriptions is currently unavailable, but check again soon!");
								viewDescription(searchedUserName);
								
							}else if(menu_input2.equals("3")){
								
								viewMessagesFromUser(searchedUserName);
								
							}else{
								System.out.println("Please enter one of the choices specified above.");
							}
							
						}else if(menu_input.equals("6")){
							
							createDescription();
														
						}else if(menu_input.equals("7")){
							
							viewMessagesFromUser(currentUser.getName());
							
						}else if(menu_input.equals("8")){

							System.out.println("Searching for a hashtag is currently unavailable, but check again soon!");
							
						}
						else if(menu_input.equalsIgnoreCase("Q")){
							System.out.println("Have a nice day!");
							break;//breaks out of this while, should take the user to the not-logged-in menu
						}else{
							System.out.println("Please enter one of the choices specified above.");
						}//end else
			      }//end while logged in 
	      	}//end while not q
	   }//end main
///////////////////////////////////////////////////////////////////////////////////////////////	   
///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////	   
	   public static void getConnection(){
		   
		   try{
			   
			  con = DriverManager.getConnection("jdbc:mysql://localhost/users","root", "CPSC330");
		      stat = (Statement) con.createStatement();
		      
		   }catch(Exception e){
			   System.out.println(e);
		   }  
	   }
///////////////////////////////////////////////////////////////////////////////////////////////
	   
	   public static void printGuestMenu(){
			System.out.println("Welcome to GoSin, enter one of these options to get started!");
			System.out.println("1: Register");
			System.out.println("2: Log in");
			System.out.println("3: Post anonymous message");
			System.out.println("4: Browse messages as guest");
			System.out.println("5: Search for a user");
			System.out.println("6: Search for a hashtag");
			System.out.println("Q: Quit");			
	   }

///////////////////////////////////////////////////////////////////////////////////////////////
	   
	   public static void printUserMenu(){
	    	  	System.out.println("You are now logged on as " + currentUser.getName() + ".");
				System.out.println("1: Change Password");
				System.out.println("2: Log off");
				System.out.println("3: Post message");
				System.out.println("4: Browse subscribed messages");
				System.out.println("5: Search for a user");
				System.out.println("6: Create/edit a profile description");
				System.out.println("7: View my messages");
				System.out.println("8: Search for a hashtag");
				System.out.println("Q: Quit");
	   }
	   
///////////////////////////////////////////////////////////////////////////////////////////////
	   public static void login(){
		   
		   Scanner input = new Scanner(System.in);
			if(!loggedOn){
				while(!loggedOn){
					System.out.println("Please enter your username");
					String new_username = input.nextLine();
					if(currentUser.doesUsernameExist(new_username, stat)){ //check if name matches a username in the system
						System.out.println("Please enter your password ");
						String new_password = input.nextLine();
						if(currentUser.isThisTheCorrectPassword(new_username, new_password, stat)){
							currentUser = new UserAccount(new_username, new_password);
							loggedOn = true;
							System.out.println("Woo! You logged on successfully.");
							
						}else{
							System.out.println("You entered the wrong password; please try again.");
						}
							
					}else{

						System.out.println("You entered the wrong username; please try again.");

					}
				}
			}else{//if the user is already logged on
					//this shouldn't ever happen however, as users aren't given this option when they are logged on
				System.out.println("You are already logged in as " + currentUser.getName() + " . If you want to switch users, please log out first.");
			}
	   }
///////////////////////////////////////////////////////////////////////////////////////////////
	   public static void createAccount(UserAccount newAccount){
		   Scanner input = new Scanner(System.in);
		   String new_username = "";
		   String new_password = "";

			boolean added = false;
			while(!added){
				System.out.println("Please enter an original username, be creative! ");
				new_username = input.nextLine();
				if(newAccount.doesUsernameExist(new_username, stat)){ //check if name is taken
					System.out.println("This username is taken, please try again.");
				}else{

					System.out.println("Please enter a password, don't forget it! ");
					new_password = input.next();
					
					newAccount = new UserAccount(new_username, new_password);
					loggedOn = true;
					System.out.println("Congratulations! You now have an account with GoSin. GO FORTH AND FUCK SHIT UP.");
					added = true;
				}
			}
	      
		      String insertQuery = "INSERT INTO userlist (username,password)" +
		    		  			"VALUES ( '" + new_username + "' , '" + new_password + "' );";
		      try{
		    	  stat.execute(insertQuery);		      
		    	  currentUser = newAccount;
		      }catch(Exception e){
		    	System.out.println(e);  
		      }
		   }
///////////////////////////////////////////////////////////////////////////////////////////////
	   
	   
	   public static void postMessage(){
		   boolean found = false;
		   
		   Scanner in = new Scanner(System.in);
		   System.out.println("Enter a message, please make it under 140 characters.");
		   String new_message = in.nextLine();
		   String originalPoster = currentUser.getName();

		   
		    java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    
		    Calendar cal = Calendar.getInstance();		    
		    
		    cal.set( cal.MILLISECOND, 0 );
		    
		    java.sql.Time sqlTime = 
		       new java.sql.Time( cal.getTime().getTime() );
		    
		   int id = currentUser.getUserID(stat);
		   
		   String insertmessageQuery = "INSERT INTO messagelist (username, datePosted, message_content)"
				   + "VALUES ( '"+ originalPoster +"', '" + sqlDate + " " + sqlTime + "' , '"+ new_message+ "');";
		   
		   try{
			   stat.execute(insertmessageQuery);
		   }catch(Exception e){
			   System.out.println(e);
		   }
		   
		   /*try{
	           while (rs.next()) {
	             postedmessage = rs.getString("message"); 
	             found = true;
	           }
	      }catch(Exception e2){
			   System.out.println(e2);
		   	}*/
		   
		   //return found;
	   }
	   

///////////////////////////////////////////////////////////////////////////////////////////////

	   public static void showPublicMessages(){
		   String showPublicMessagesQuery = "SELECT * FROM messagelist;";
		   String postedMessage = "";
		   String originalPoster = "";
		   Date postedDate = null;
		   Time postedTime = null;
		   
		   
		   try{//try 1
		   		ResultSet rs = stat.executeQuery(showPublicMessagesQuery);
		   		
		   		try{
		           while (rs.next()) {
		        	 originalPoster = rs.getString("username");
		        	
		        	 
		        	 postedDate = rs.getDate("datePosted");
		        	 postedTime = rs.getTime("datePosted");
		             postedMessage = rs.getString("message_content"); 

		             System.out.print(originalPoster + " | " + postedDate + " | " + postedTime);
		             System.out.println();
		             System.out.println(postedMessage);
		             System.out.println();
		           }
		      }catch(Exception e2){
		    	  System.out.println("e2:" + e2);
			   	}
	   
		   }catch(Exception e){
			   System.out.println("e:" + e);
		   }
	   }
	   
///////////////////////////////////////////////////////////////////////////////////////////////	   
	   
	   public static void searchUser(String enteredUsername){
		   
		   String searchForUserQuery = "SELECT * FROM userlist WHERE username = '" + enteredUsername + "';";
		   String searchedUserName = "";
				   
		   
		   try{//try 1
		   		ResultSet rs = stat.executeQuery(searchForUserQuery);
		   		
		   		try{
		           while (rs.next()) {
		        	 searchedUserName = rs.getString("username");
		        	 System.out.println(searchedUserName);
		        	 
		        	 
		           }
		      }catch(Exception e2){
		    	  System.out.println("e2:" + e2);
			   	}
	   
		   }catch(Exception e){
			   System.out.println("e:" + e);
		   }
	   }	
//////////////////////////////////////////////////////////////////////////////////////////////////
	   public static void createDescription(){
		   Scanner input = new Scanner(System.in);
		   System.out.println("Please enter a brief description that you would like people to see on your GoSin profile:");
		   String new_description = input.nextLine();
		   String updateDescriptionQuery = "UPDATE userlist SET description = '" + new_description + "' WHERE username = '" + currentUser.getName() + "';";
		   try{
			   stat.execute(updateDescriptionQuery);
		   }catch(Exception e){System.out.println("CD e:" + e);}
		   
		  }//end createDescription	   	   
	   
///////////////////////////////////////////////////////////////////////////////////////////////
	   
	   public static void viewMessagesFromUser(String entered_username){
		   Scanner input = new Scanner(System.in);
		   String selectMessagesQuery = "SELECT * FROM messagelist WHERE username = '" + entered_username + "';";
		   String postedMessage = "";
		   String originalPoster = "";
		   Date postedDate = null;
		   Time postedTime = null;
		   
		   
		   try{//try 1
		   		ResultSet rs = stat.executeQuery(selectMessagesQuery);
		   		
		   		try{
		           while (rs.next()) {
		        	 originalPoster = rs.getString("username");
		        	
		        	 
		        	 postedDate = rs.getDate("datePosted");
		        	 postedTime = rs.getTime("datePosted");
		             postedMessage = rs.getString("message_content"); 

		             System.out.print(originalPoster + " | " + postedDate + " | " + postedTime);
		             System.out.println();
		             System.out.println(postedMessage);
		             System.out.println();
		           }
		      }catch(Exception e2){
		    	  System.out.println("VMe2:" + e2);
			   	}
	   
		   }catch(Exception e){
			   System.out.println("VMe:" + e);
		   }
	   }
///////////////////////////////////////////////////////////////////////////////////////////////
	   public static void viewDescription(String entered_username){
		   Scanner input = new Scanner(System.in);
		   ResultSet rs = null;
		   String searched_description = "";
		   String showDescriptionQuery = "SELECT description FROM userlist WHERE username = '"  + "';";
		   try{
			   System.out.println("entering first try");
			   
			   rs = stat.executeQuery(showDescriptionQuery);
			   //System.out.println(rs);
			   try{
				   System.out.println("entering second try");
				   while(rs!=null){
					   System.out.println("entering while");
					   searched_description = rs.getString("description");
					   System.out.println(searched_description);
				   }
				   
			   }catch(Exception e2){
				   System.out.println("VDe2:" + e2);
			   }
			   
		   }catch(Exception e){
			   System.out.println("VDe:" + e);
		   }
	   }
///////////////////////////////////////////////////////////////////////////////////////////////
	   
	   
	   
	   
	   
	   private static UserAccount currentUser = new UserAccount(); //the current account being used 
	   private static Connection con = null;
	   private static Statement stat = null;
	   private static boolean loggedOn = false; //this is a boolean to check if the user is online
	}

	

