package Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteMain {
	
	public static void main(String[] args) {
		String url = "http://cs229.stanford.edu/materials.html";	

		FileFetcher fe = new FileFetcher();
		fe.download(url);
		
	}

}
