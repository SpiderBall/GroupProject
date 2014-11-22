package groupProject;
import java.util.*;
import java.sql.*;


public class GroupProject {



	   public static void main(String[] args){
	      //initializing the conditions of the program
	      signedIn = false;
	      getConnection();
	      String menu_input = "";
	      Scanner in = new Scanner(System.in);
	      do{
				System.out.println("Welcome to GoSin, enter one of these options to get started!");
				System.out.println("1: Register");
				System.out.println("2: Log in");
				System.out.println("3: Post anonymous message");
				System.out.println("4: Browse messages as guest");
				//System.out.println("5: Log Off");
				System.out.println("Q: Quit");
				menu_input = in.nextLine();
			

				if(menu_input.equals("1")){
					createAccount(currentUser);

				}
				else if(menu_input.equals("2")){

					//boolean currentlyLoggedOn = loggedOn;
					if(!loggedOn){
						while(!loggedOn){
							System.out.println("Please enter your username");
							String new_username = in.nextLine();
							if(currentUser.doesUsernameExist(new_username, stat)){ //check if name matches a username in the system
								System.out.println("Please enter your password ");
								String new_password = in.nextLine();
								currentUser = new UserAccount(new_username, new_password);
								loggedOn = true;
								System.out.println("Woo! You logged on successfully.");
								//currentlyLoggedOn = true;
							}else{

								System.out.println("You entered the wrong username; please try again.");

							}
						}
					}else{//if the user is already logged on
						System.out.println("You are already logged in as " + currentUser.getName() + ". If you want to switch users, please log out first.");
					}


				}
				else if(menu_input.equals("3")){
					
				}
				else if(menu_input.equals("4")){
					
				}
				else if(menu_input.equals("Q")||menu_input.equals("q")){
					System.out.println("Have a nice day!");
				}
			}while(!loggedOn &&(!menu_input.equals("Q"))&&(!menu_input.equals("q")));
			//menu runs until the user quits or the use
	      
	      
	   }
	   public static void getConnection(){
	      //loads every tweet saved to the computer into an arraylist of tweets
	      
		   try{
			   System.out.println("entering try");
			  con = DriverManager.getConnection("jdbc:mysql://localhost/users","root", "CPSC330");
		      stat = (Statement) con.createStatement();
		   }catch(Exception e){
			   System.out.println(e);
		   }
	      
	   }
	   /*
	   public static void saveData(){
	      //saves every tweet in the ArrayList of tweets to a text file on the computer
	      //ideally this would be repeated after every other action taken
	      //in order to make sure no data is lost
	      //"jesus saves and so should GoSin"
	   } */
	   public static void login(){
	      //asks for a username and password and then finds that account in the text file for accounts
	      
	   }
	   public static void createAccount(UserAccount newAccount){
		   Scanner in = new Scanner(System.in);
		   String new_username = "";
		   String new_password = "";

			boolean added = false;
			while(!added){
				System.out.println("Please enter an original username, be creative! ");
				new_username = in.nextLine();
				if(newAccount.doesUsernameExist(new_username, stat)){ //check if name is taken
					System.out.println("This username is taken, please try again.");
				}else{

					System.out.println("Please enter a password, don't forget it! ");
					new_password = in.nextLine();
					
				    // UserAccount newAccount = new UserAccount(username, password);
					newAccount = new UserAccount(new_username, new_password);
					//newAccount.setLoggedOnTrue();
					System.out.println("Congratulations! You now have an account with GoSin. GO FORTH AND FUCK SHIT UP.");
					added = true;
				}
			}
			
	      //asks for info to make a new account
	      

	      //CHECK TO SEE IF THE USERNAME ALREADY EXISTS
	      

	      

	      
	      String insertQuery = "INSERT INTO usernames" +
	    		  			"VALUES (" + new_username + ", " + new_password + ");";
	      try{
	    	  resultSet = stat.executeQuery(insertQuery);
	      //CREATE A NEW TEXT FILE DEDICATED TO THE USER ACCOUNT
	      //I'M TOO LAZY TO DO THAT RIGHT NOW THOUGH
	      
	    	  currentUser = newAccount;
	      }catch(Exception e){
	    	System.out.println(e);  
	      }finally{
	    	  in.close();
	      }
	      //^Holy shit that is confusing to say aloud^
	      
	   }
	   private ArrayList<Message> tweets;
	   private static UserAccount currentUser = new UserAccount(); //the currently logged in account (changed the name from loggedIn, i found that confusing)
	   private static boolean signedIn;
	   private static Connection con = null;
	   public static Statement stat = null;
	   private static ResultSet resultSet = null;
	   private static boolean loggedOn = false; //this is a bool to check if the use is online
	}

	

