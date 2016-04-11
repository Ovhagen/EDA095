package Runnable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RunnableMain {
	
	public static void main(String[] args) {
		String url = "http://cs229.stanford.edu/materials.html";	

		FileFetcher fe = new FileFetcher();
		fe.download(url);
		
		RunnerURL r1 = new RunnerURL(fe);
		RunnerURL r2 = new RunnerURL(fe);
		RunnerURL r3 = new RunnerURL(fe);
		RunnerURL r4 = new RunnerURL(fe);
		RunnerURL r5 = new RunnerURL(fe);

		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		Thread t4 = new Thread(r4);
		Thread t5 = new Thread(r5);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}

}
