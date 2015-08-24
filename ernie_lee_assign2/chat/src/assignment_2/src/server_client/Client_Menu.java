package server_client;

import java.util.Scanner;
import java.util.NoSuchElementException;

import util.InvalidInputException;
import util.MenuDisplay;

/**
 * Runs the menu for the client 
 *
 */
public class Client_Menu implements MenuDisplay{

	private Scanner scanner;
	
	private String name;
	private int input;
	private boolean quit; 
	
	/**
	 * Client constructor
	 * Initializes variables needed to run the menu for the client
	 * 
	 */
	public Client_Menu(){
		try{
			scanner = new Scanner(System.in);
			
			name = "Anonymous";
			input = -1;
			quit = false;
		}catch(NullPointerException e){
			System.exit(0);
		}
	}
	
	/**
	 * Displays menu for client
	 * Client Menu
	 * 1) Give me a name
	 * 2) Send Message to Server
	 * 3) Print Message from Server
	 * 0) Quit
	 */
	@Override
	public void displayOptions() {
		System.out.println("Client Menu");
		System.out.println("1) Give me a name");
		System.out.println("2) Send Message to Server");
		System.out.println("3) Print Message from Server");
		System.out.println("0) Quit ");
	}
	
	/**
	 * Gets the name of the client when option is chosen
	 */
	private void give_name(){
		
		System.out.println("Name: ");
		
		name = scanner.nextLine();
	}

	/**
	 * Gets the input from the client after the menu is displayed
	 * 
	 * @return int input 
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
			else if(input == 1)
				give_name();

			else if(input != 2 && input != 3)
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
	 * Gets the message received from the client once option is chosen
	 * 
	 * @return String message
	 */
	@Override
	public String get_message() {
		
		String c_in = "";
		String message = name + ": ";
		
		System.out.println("Message: ");
		
		try{
			//c_in = console_in.readLine("Option: ");
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
	 * Disconnects the client from the server
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
