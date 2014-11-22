package groupProject;
import java.util.*;
import java.sql.*;


public class GroupProject {


	   public static void main(String[] args){
	      //initializing the conditions of the program
	      loggedOn = false;
	      getConnection();
	      String menu_input;
	      Scanner in = new Scanner(System.in);
	      do{
	    	  	menu_input = "";
	    	  	//Scanner in = new Scanner(System.in);
				System.out.println("Welcome to GoSin, enter one of these options to get started!");
				System.out.println("1: Register");
				System.out.println("2: Log in");
				System.out.println("3: Post anonymous message");
				System.out.println("4: Browse messages as guest");
				//System.out.println("5: Log Off");
				System.out.println("Q: Quit");
				//System.out.println(in.nextLine());
				menu_input = in.nextLine();
			

				if(menu_input.equals("1")){
					createAccount(currentUser);
				}
				else if(menu_input.equals("2")){
					login();
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
	      
	      Scanner input = new Scanner(System.in);
	      while(loggedOn && (!menu_input.equals("Q"))&&(!menu_input.equals("q"))){
	    	  System.out.println("You are now logged on as" + currentUser.getName() + ".");
	    	  menu_input = "";
	    	  	
				System.out.println("1: Change Password");
				System.out.println("2: Log off");
				System.out.println("3: Post message");
				System.out.println("4: Browse messages");
				System.out.println("Q: Quit");
				//System.out.println(in.nextLine());
				menu_input = input.nextLine();
				
				if(menu_input.equalsIgnoreCase("1")){
					
				}
				else if(menu_input.equals("2")){
					
				}
				else if(menu_input.equals("3")){
					
				}
				else if(menu_input.equals("4")){
					
				}
				else if(menu_input.equals("Q")||menu_input.equals("q")){
					System.out.println("Have a nice day!");
				}
	    	  
	      }
	      input.close();  
     
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
		 //boolean currentlyLoggedOn = loggedOn;
		   Scanner in = new Scanner(System.in);
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
						loggedOn = true;
					}else{

						System.out.println("You entered the wrong username; please try again.");

					}
				}
			}else{//if the user is already logged on
				System.out.println("You are already logged in as " + currentUser.getName() + " . If you want to switch users, please log out first.");
			}
			in.close();


	      
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
					new_password = in.next();
					
				    // UserAccount newAccount = new UserAccount(username, password);
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
		      }finally{
		    	  in.close();
		      }
		   }
	   
	   private ArrayList<Message> tweets;
	   private static UserAccount currentUser = new UserAccount(); //the currently logged in account (changed the name from loggedIn, i found that confusing)
	   //private static boolean signedIn;
	   private static Connection con = null;
	   public static Statement stat = null;
	   private static ResultSet resultSet = null;
	   private static boolean loggedOn = false; //this is a boolean to check if the use is online
	}

	

