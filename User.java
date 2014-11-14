import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
							new BufferedWriter(new FileWriter(newTextFIle));
					writer.write("Username: " + username);
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

	public void setPassword(new_password){ //we would first need to make sure the user is logged in
		password = new_password;
	}

/* THIS MIGHT NEED TO GO IN THE MAIN PROGRAM

	public boolean verifyUser(entered_username, entered_password){

		if((entered_username.equals(username))&&(entered_password))

	} 
*/


	protected String username;
	protected String password;
	package boolean loggedOn = false; //this just saves us the hassle of using getters and setters
										//to check if the user is logged in since 
										//this value can be changed in the main program

	protected List<String> ListOfUsernames = new ArrayList<String>();
	//somehow we need to make sure this data doesn't get deleted at the end of runtime
	//maybe we could have it read from a file that has a list of the usernames?
}