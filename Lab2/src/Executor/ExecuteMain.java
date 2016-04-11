package Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteMain {
	
	public static void main(String[] args) {
		String url = "http://cs229.stanford.edu/materials.html";	

		FileFetcher fe = new FileFetcher();
		fe.download(url);
		
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		Runnable task = new Runner(fe);
		service.submit(task);
		service.shutdown();
	}

}
