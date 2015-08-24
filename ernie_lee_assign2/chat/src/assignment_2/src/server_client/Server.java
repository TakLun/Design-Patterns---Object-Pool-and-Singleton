package server_client;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import util.MenuDisplay;
import util.ThreadPool;

public class Server implements Runnable{

	private ServerSocket serverSocket;
	private Socket handle_to_client;
	
	private PrintWriter out;
	private BufferedReader in;
	
	private MenuDisplay menu;
	private int input;
	private int portNumber;
	private String server_to_client_message;
	private String client_to_server_message;
	
	private StringBuffer strBuffer;
	
	/**
	 * Server Constructor
	 * Initializes the Server
	 * 
	 * @param portNumber
	 */
	public Server(int portNumber){
		serverSocket = null;
		handle_to_client = null;
		
		this.portNumber = portNumber;
		
		menu = null;
		input = -1;
		
		strBuffer = new StringBuffer("");
	}
	
	/**
	 * Server constructor for the borrowed thread
	 * 
	 * @param handle
	 */
	public Server(Socket handle){
	
		handle_to_client = handle;
		
		in = null;
		out = null;
		
		menu = null;
		input = -1;
		
		server_to_client_message = "";
		client_to_server_message = "";
		
		strBuffer = new StringBuffer("");
	}
	
	/**
	 * Method called to start running the server
	 * Initialized the ServerSocket and gets handles the clients coming into the port
	 */
	public void start(){
		try{
		
			serverSocket = new ServerSocket(portNumber);
			System.out.println("listening on port: " + serverSocket.getLocalPort() + "\nAddress: " + serverSocket.getInetAddress());
			System.out.println("Waiting for client to connect...\n\n");
			
			
			while(true){
				handle_to_client = null;
				
				try{
				
					handle_to_client = serverSocket.accept();
					
					Thread borrowed = ThreadPool.getInstance().borrowThread();
					
					if(ThreadPool.getInstance().checkAvailable() == false){
						System.out.println("\nNo Available Threads In Pool for new Client\nInput from incoming client ignored\n\n");
						
					}else{
					
						borrowed = new Thread(new Server(handle_to_client));
						borrowed.start();
					}
					
				}catch(IOException e){
					System.out.println("Could not listen on port: " + portNumber);
					//System.exit(1);
					return;
				}
			}
		
		}catch(IOException e){
					System.out.println("Could not listen on port: 5677");
					System.exit(1);
		}finally{
		
			try{
				serverSocket.close();			
			}catch(IOException e){
				System.exit(1);
			}
	
		}
	}
	
	/**
	 * Gets the message from the BufferedReader and prints out the message
	 * 
	 * @param in
	 */
	private void recieve_message_from_client(BufferedReader in){
	
		try {
			boolean waitForInput = true;
			
			while(waitForInput){
				client_to_server_message = in.readLine();
				
				if(client_to_server_message != null){
				
					if(client_to_server_message.endsWith("BACKUP")){
						System.out.println(strBuffer);
					}else{
						printServerMessage(client_to_server_message);
					}
					
					waitForInput = false;
				}
			}
			//get string from buffer
		} catch (IOException e) {


			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Prints out the message from the buffered reader
	 * @param message
	 */
	private void printServerMessage(String message){
	
		strBuffer.append(message);
		System.out.println(message + "\n");
	
	}
	
	/**
	 * Method the borrowed thread runs
	 * Initializes the printwriter and bufferedreader to allow input from the client and output from the server 
	 * Calls the Server_Menu class to display the server menu and handle input from the user
	 */
	@Override
	public void run() {
	
		try{
	
			out = new PrintWriter(handle_to_client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(handle_to_client.getInputStream()));
		
			menu = new Server_Menu();
	
			while(!menu.Quit()){
				menu.displayOptions();
				input = menu.getInput();
					
				if(input == 1){
					server_to_client_message = menu.get_message();
				
					out.println(server_to_client_message);
				}
					
				if(input == 2){
					recieve_message_from_client(in);
				}
			}
		
		}catch(IOException e){
			System.exit(1);
		}
	}
}
