package util;

public class ThreadPool{
	
	final int NUM_OF_THREADS = 5;
	
	private static ThreadPool threadPoolInstance;
	private static boolean available;
	private Thread[] threadPool; 
	private boolean[] taken_flag;
	
	/**
	 * ThreadPool Constructor
	 * 
	 * Private constructor so it can create an instance of itself
	 */
	private ThreadPool(){
		threadPool = new Thread[NUM_OF_THREADS];
		taken_flag = new boolean[NUM_OF_THREADS];
		
		available = true;
		
		for(int i=0;i<NUM_OF_THREADS;i++)
			taken_flag[i] = false;
		
	}
	
	/**
	 * Checks if there is an instance of ThreadPool
	 * Creates a new ThreadPool if there is not a ThreadPool already created 
	 * 
	 * @return ThreadPool
	 */
	public static synchronized ThreadPool getInstance() {
			
			if(threadPoolInstance == null){
				threadPoolInstance = new ThreadPool();
			}
			
			return threadPoolInstance;
	}
	
	/**
	 * Checks the availability of the thread pool
	 * 
	 * @return boolean
	 */
	public boolean checkAvailable(){
		
		return available;
	}
	
	/**
	 * Sets the available to true once a client has ended its connection to the server
	 */
	public void setAvailable(){
		available = true;
	}
	
	/**
	 * Returns a thread from the thread pool
	 * Returns null if all threads in the pool are being used 
	 * 
	 * @return Thread
	 */
	public synchronized Thread borrowThread(){
		
		int thread_num = -1;
		
		for(int i=0;i<NUM_OF_THREADS;i++){
			if(taken_flag[i] == false){
				thread_num = i;
				taken_flag[thread_num] = true;
				break;
			}
		}
		
		if(thread_num == -1){
			available = false;
			return null;
		}else{
			return threadPool[thread_num];
		}
	}
}
