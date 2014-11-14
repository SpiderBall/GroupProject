import java.util.Scanner;

public class GoSin{

	public static void main(String[] args) {
		
	
		boolean loggedOn = false; 
		Scanner in = new Scanner(System.in);
		String menu_input = "";
		User currentUser;

		do{
			System.out.println("Welcome to GoSin, enter one of these options to get started!");
			System.out.println("1: Register");
			System.out.println("2: Log in");
			System.out.println("3: Post anonymous message");
			System.out.println("4: Browse messages as guest");
			System.out.println("Q: Quit");
			menu_input = in.nextLine();

		}while((!loggedOn)&&(!menu_input.equals("Q"))&&(!menu_input.equals("q")));
		//menu runs until the user quits or the user logs in

		if(menu_input.equals("1")){
			System.out.println("Please enter an original username, be creative! ");
			String new_username = in.nextLine();
			System.out.println("Please enter a password, don't forget it! ");
			String new_password = in.nextLine();
			currentUser = new User(new_username, new_password);
			System.out.println("Congratulations! You now have an account with GoSin. GO FORTH AND FUCK SHIT UP.");

		}


		if(menu_input.equals("Q")||menu_input.equals("q")){
			System.out.println("Have a nice day!");
		}
	}
	
}