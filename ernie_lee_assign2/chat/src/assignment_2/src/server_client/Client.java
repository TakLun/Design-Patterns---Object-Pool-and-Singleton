package server_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;

import util.MenuDisplay;
import util.ThreadPool;

import server_client.Client_Menu;

public class Client{

	private Socket client_socket;
	private PrintWriter out;
	private BufferedReader in;
	
	private MenuDisplay menu;
	private int input;
	private String client_to_server_message;
	private String server_to_client_message;
	
	private StringBuffer strBuffer;
	
	private String host;
	private int portNumber;
	
	/**
	 * Client Constructor
	 * Gets the host name and port number from the command line arguments and connects to the specified port.
	 * 
	 * @param hostname
	 * @param portNumber
	 */
	public Client(String hostname, int portNumber){
		
		client_socket = null;
		out = null;
		in = null;
		
		host = hostname;
		this.portNumber = portNumber;
		
		menu = null;
		input = 0;
		client_to_server_message = "";
		server_to_client_message = "";

		strBuffer = new StringBuffer("");
	}

	/**
	 * Method to be called to start the client.
	 * Initializes the client socket and connects the client to the port in the host
	 * Calls the Client_Menu class to display the client menu and handle input from the user
	 */
	public void start(){
		
		try {
			
			InetAddress address = InetAddress.getByName(host);
			System.out.println("host: " + address);
			client_socket = new Socket(address, portNumber);
			
			out = new PrintWriter(client_socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
			
			System.out.println("listening on port: "+client_socket.getLocalPort() + "\n\n");
			
			
			menu = new Client_Menu();
			
			while(!menu.Quit()){
				
				menu.displayOptions();
				input = menu.getInput();
				
				if(input == 2){
					client_to_server_message = menu.get_message();
					
					send_message_to_server(client_to_server_message);
				}
				if(input == 3){
					recieve_message_from_server();
				}
				
			}
			
			ThreadPool.getInstance().setAvailable();
			
		} catch (UnknownHostException e) {


			e.printStackTrace();
		} catch (IOException e) {


			e.printStackTrace();
		} 
	}
	
	/**
	 * Sends a message inputed by the user and sends it to the server 
	 * 
	 * @param message
	 */
	private void send_message_to_server(String message){
		
		out.println(message);
	}
	
	/**
	 * Receives the message passed from the server to the client
	 * Prints out the server message afterwards
	 */
	private void recieve_message_from_server(){
		
		try {
			boolean waitForInput = true;
			
			while(waitForInput){
				server_to_client_message = in.readLine();
				
				if(server_to_client_message != null){
					if(server_to_client_message.endsWith("BACKUP")){
						System.out.println(strBuffer);
					}else{
						printServerMessage(server_to_client_message);
					}
					
					waitForInput = false;
				}
			}
			//get string from buffer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.exit(1);
		}
		
	}
	
	/**
	 * Prints out the message passed by the server to the client
	 * 
	 * @param message
	 */
	private void printServerMessage(String message){
		
		strBuffer.append(message);
		
		System.out.println(message + "\n");
	}

}
