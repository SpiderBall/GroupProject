import java.util.Scanner;

public class GoSin{

	boolean loggedOn = false; 
	Scanner in = new Scanner(System.in);
	String menu_input = "";

	do{
		System.out.println("Welcome to GoSin, enter one of these options to get started!");
		System.out.println("1: Register/Log in");
		System.out.println("2: Post message");
		System.out.println("3: Browse messages");
		System.out.println("Q: Quit");
		menu_input = in.nextLine();

	}while((!loggedOn)&&(!menu_input.equals("Q"))&&(!menu_input.equals("q")));
	//menu runs until the user quits or the user logs in

	if(menu_input.equals("1")){

	}


	if(menu_input.equals("Q")||menu_input.equals("q")){
		System.out.println("Have a nice day!");
	}
	
}