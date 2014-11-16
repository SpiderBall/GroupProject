import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class User{

	public User(String new_username, String new_password){ //constructor that creates a user with a username and password
		username = new_username;
		password = new_password;

		//add to an arraylist of usernames to access later
		
		boolean added = false;
		
		while(!added){
			if(!ListOfUsernames.contains(username)){ //an easy way to check if a username has been taken yet

				ListOfUsernames.add(username);

				//creating the text file for the user data
				String userDataFile = username + ".txt";
				File newTextFile = new File(userDataFile);

				//enables writing to the created text file
				try{
					BufferedWriter writer = 
							new BufferedWriter(new FileWriter(newTextFile));
					writer.write("Username: " + username);
					writer.write();
					writer.write("Password: " + password);
					writer.close();
					added = true;
				}catch(IOException e){
					e.printStackTrace();
				}
			}else{
				System.out.println("This username is already taken, please enter another one.");
			}
		}
	}

	public User(){ //default constructor used for non-logged in users
		
	}


	public void setPassword(String new_password){ //we would first need to make sure the user is logged in
		password = new_password;
	}

/* THIS MIGHT NEED TO GO IN THE MAIN PROGRAM

	public boolean verifyUser(entered_username, entered_password){

		if((entered_username.equals(username))&&(entered_password))

	} 
*/

	public void setLoggedOnTrue(){

		loggedOn = true;
	}

	public void setLoggedOnFalse(){
		loggedOn = false;

	}


	public boolean checkIfLoggedOn(){
		return loggedOn;
	}

	public boolean doesUsernameExist(String new_username){
		if(ListOfUsernames.contains(new_username)){
			return false;
		}else{
			return true;
		}
	}


	public void verifyUser(String new_username, String new_password){

		
		if(ListOfUsernames.contains(new_username)){

			username = new_username;
			try {
			    File file = new File(username + ".txt");
			    BufferedReader reader = new BufferedReader(new FileReader(file));

			    String line;
			    while ((line = reader.readLine()) != null) {
			        System.out.println(line);
			    }
			    reader.close();

			} catch (IOException e) {
			    e.printStackTrace();
			} 

		}
	}

/*
	public User getUser(String new_username){

		username = new_username;

	} */

	public String getUsername(){
		return username;
	}


	protected String username;
	protected String password;
	protected boolean loggedOn = false; 

	protected List<String> ListOfUsernames = new ArrayList<String>();
	//somehow we need to make sure this data doesn't get deleted at the end of runtime
	//maybe we could have it read from a file that has a list of the usernames?
}