package groupProject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Message{

	//the private message class will inherit from this class
	//and it will add a data member "recipient"
	//which is the username of the person it was sent to
	//and when an account logs in only those PMs will be loaded

	//this is the class that our "tweets" will use

	   Message(String inContent, String inUser){
	      messageContent = inContent;
	      userPosted = inUser;
	      
	      //down here we store the date and time that the message was created
	      //but I haven't done that yet
	   }
	   
	   

	   
	   
	   
	   
	   public void deleteMessage(){
	      Scanner in = new Scanner(System.in);
	      System.out.println("Why is this message being removed?");
	      String reason = in.nextLine();
	      deleteMessageBackend(reason);
	      System.out.println("Message removed.");
	      //in.close();
	   }
	   
	   public void deleteMessageBackend(String reason){
	      messageContent = ("Message has been removed. Reason given: '"+reason+"'.");
	   }

	   //DATA MEMBERS
	   protected String messageContent;
	   protected String userPosted;

	}
