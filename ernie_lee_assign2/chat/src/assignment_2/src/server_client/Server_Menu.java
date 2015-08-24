package server_client;

import java.util.NoSuchElementException;
import java.util.Scanner;

import util.InvalidInputException;
import util.MenuDisplay;


public class Server_Menu  implements MenuDisplay{

	private Scanner scanner;
	private int input;
	private boolean quit;
	private String name;
	
	/**
	 * Server Menu Constructor
	 * Initialized the variables needed by the server
	 * 
	 */
	public Server_Menu(){
		try{
			scanner = new Scanner(System.in);
			
			name = "Server";
			input = -1;
			quit = false;
		}catch(NullPointerException e){
			System.exit(0);
		}
	}
	
	/**
	 * Displays the options for the server
	 * Server Menu
	 * 1) Send message to client
	 * 2) Print Messages from a client
	 * 0) Quit
	 */
	@Override
	public void displayOptions() {
		System.out.println("Server Menu");
		//System.out.println("1) Send Message to all clients");
		System.out.println("1) Send message to client");
		System.out.println("2) Print Messages from a client");
		System.out.println("0) Quit ");		
	}
	
	
	/**
	 * Gets input from the server
	 * 
	 * @return int
	 * 
	 */
	@Override
	public int getInput() {
		String c_in = "";
		
		System.out.println("Option: ");
		try{
			c_in = scanner.nextLine();
			
			input = Integer.parseInt(c_in);
			
			if(input == 0)
				quit = true;

			else if(input != 1 && input != 2 && input != 3)
				throw new InvalidInputException("Invalid Client Input");

		}catch(NullPointerException e){
			
			System.exit(0);
			
		} catch (NumberFormatException e) {
			
			System.exit(0);
			
		} catch (InvalidInputException e) {
			
			System.exit(0);
			
		}catch(NoSuchElementException e){
		
			System.exit(0);
		
		}
		
		return input;
		
	}


	/**
	 * Gets the message inputed by the server
	 * 
	 * @return String
	 */
	@Override
	public String get_message() {
		
		String c_in = "";
		String message = name + ": ";
		
		System.out.println("Message: ");
		
		try{
			c_in = scanner.nextLine();

			message = message + c_in;
			
		}catch(NullPointerException e){
			
			System.exit(0);
			
		} catch (NumberFormatException e) {
			
			System.exit(0);
			
		}
		
		return message;
	}

	/**
	 * Disconnects the server from the socket
	 * returns a boolean to signal the server to quit.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean Quit() {
		
		if(quit)
			System.out.println("Done");
		
		return quit;		
	}
}
