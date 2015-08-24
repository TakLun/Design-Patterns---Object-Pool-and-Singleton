package client_driver;

import server_client.Client;

/**
 * Main driver for the client 
 *
 */
public class ClientDriver{
	public static void main(String args[]){
		
		try{
		
			String hostname = args[0];			
			String port = args[1];
			int portNumber = Integer.parseInt(port);
			
			Client client = new Client(hostname, portNumber);
			client.start();
			
		}catch(NumberFormatException e){
			e.printStackTrace();
			System.exit(0);
		}
	}
}
