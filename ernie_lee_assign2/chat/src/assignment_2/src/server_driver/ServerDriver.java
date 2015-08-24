package server_driver;

import server_client.Server;

/**
 * Main Driver for the server class
 * 
 */
public class ServerDriver{
	public static void main(String args[]){
		
		try{
			
			
			String port = args[0];
			int portNumber = Integer.parseInt(port);
			
			Server server = new Server(portNumber);
			server.start();
			
		}catch(NumberFormatException e){
			e.printStackTrace();
			System.exit(0);
		}catch(NullPointerException e){
			e.printStackTrace();
			System.exit(0);
		}
	}
}
