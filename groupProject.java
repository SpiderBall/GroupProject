package groupProject;
import java.util.*;
import java.sql.*;


public class GroupProject {


	   public static void main(String[] args){
	      //initializing the conditions of the program
	      loggedOn = false;
	      getConnection();
	      String menu_input = "";
	      Scanner input = new Scanner(System.in);
	      
	      while(!menu_input.equalsIgnoreCase("Q")){
			      while(!loggedOn){
			    	  
						System.out.println("Welcome to GoSin, enter one of these options to get started!");
						System.out.println("1: Register");
						System.out.println("2: Log in");
						System.out.println("3: Post anonymous message");
						System.out.println("4: Browse messages as guest");
						System.out.println("Q: Quit");
						
						menu_input = input.nextLine();
					
		
						if(menu_input.equals("1")){
							createAccount(currentUser);
						}
						else if(menu_input.equals("2")){
							login();
						}
						else if(menu_input.equals("3")){
							System.out.println("Message posting is currently unavailable, check in again soon!");
							
						}
						else if(menu_input.equals("4")){

							System.out.println("Message browsing is currently unavailable, check in again soon!");
							
						}
						else if(menu_input.equalsIgnoreCase("Q")){
							System.out.println("Have a nice day!");
							break;
						}else{
							System.out.println("Please enter one of the choices specified above.");
						}//end else
						
					}//end while not logged in
			      
			      while(loggedOn){
			    	  System.out.println("You are now logged on as " + currentUser.getName() + ".");
			    	  //menu_input = "";
			    	  	
						System.out.println("1: Change Password");
						System.out.println("2: Log off");
						System.out.println("3: Post message");
						System.out.println("4: Browse messages");
						System.out.println("Q: Quit");
						menu_input = input.nextLine();
						
						if(menu_input.equals("1")){
							
							currentUser.changePassword(stat);
							
						}
						else if(menu_input.equals("2")){
							
							loggedOn = false;
							
						}
						else if(menu_input.equals("3")){
							

							System.out.println("Message posting is currently unavailable, check in again soon!");
							
						}
						else if(menu_input.equals("4")){
							

							System.out.println("Message browsing is currently unavailable, check in again soon!");
							
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
	   //private ArrayList<Message> tweets; (not using these yet)
	   private static UserAccount currentUser = new UserAccount(); //the current account being used 
	   private static Connection con = null;
	   private static Statement stat = null;
	   private static boolean loggedOn = false; //this is a boolean to check if the user is online
	}

	

