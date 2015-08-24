package util;

public class InvalidInputException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7901701442161836333L;

	public InvalidInputException(){
    }

	public InvalidInputException(String message){
		super (message);
    }

	public InvalidInputException(Throwable cause){
		super (cause);
    }

	public InvalidInputException(String message, Throwable cause){
		super (message, cause);
    }
	
}
