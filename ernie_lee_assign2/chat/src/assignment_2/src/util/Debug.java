package util;

import java.util.logging.Logger;

public abstract class Debug extends Logger{
	
	private static int DEBUG_VALUE;
	
	/**
	 * debug constructor
	 */
	protected Debug(String name, String resourceBundleName) {
		super(name, resourceBundleName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Sets debug value of the Logger
	 * 
	 * @param val
	 */
	public static void setDebugValue(int val){
		DEBUG_VALUE = val;
	}
	
	/**
	 * Gets the debug value set by the main class
	 * 
	 * @return int DEBUG_VALUE
	 */
	public static int getDebugValue(){
		return DEBUG_VALUE;		
	}
	
	/**
	 * Prints out the message specified by the debug value
	 *  
	 * DEBUG_VALUE=4 [Print to stdout, using the Logger, every time a constructor is called]
	 * DEBUG_VALUE=3 [Print to stdout, using the Logger, every time a thread's run() method is called]
	 * DEBUG_VALUE=2 [Print to stdout, using the Logger, every time client sends a message]
	 * DEBUG_VALUE=1 [Print to stdout, using the Logger, every time a server sends a message]
	 * DEBUG_VALUE=0 [No output should be printed from the application] 
	 * 
	 * @param value
	 * @param message
	 */
	public static void dump(int value, String message){
		
		if(value == DEBUG_VALUE){
			
			System.out.print("Debug Level " + DEBUG_VALUE + " " + message + ": ");
			
			if(DEBUG_VALUE == 4){	
				
				System.out.println("Constructor called");
				
			}else if(DEBUG_VALUE == 3){
				
				System.out.println("run() method called");
				
			}else if(DEBUG_VALUE == 2){
				
				System.out.println("Client sent message to server");
				
			}else if(DEBUG_VALUE == 1){
				
				System.out.println("Server sent message to client");
				
			}
		}
	}
	
	/**
	 * returns the debug value of the debug logger
	 * 
	 * @return String
	 */
	public String toString(){
		
		return String.valueOf(DEBUG_VALUE);
	}
}
