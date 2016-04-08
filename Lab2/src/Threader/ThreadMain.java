package Threader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Executor.Runner;

public class ThreadMain {

	public static void main(String[] args) {
		String url;
		Scanner scan = new Scanner(System.in);
		url = scan.nextLine();

		FileFetcher f = new FileFetcher();
		List<URL> links = new ArrayList<URL>();
		links = new ArrayList<URL>(f.getPdfLinks(url));

		int subSize = links.size()/3;
		ArrayList<URL> sub1 = new ArrayList<URL>(links.subList(0, subSize - 1));
		System.out.println(subSize-1);
		ArrayList<URL> sub2 = new ArrayList<URL>(links.subList(subSize, subSize*2 - 1));
		System.out.println(subSize*2-1);
		ArrayList<URL> sub3 = new ArrayList<URL>(links.subList(subSize*2, subSize*3 - 1));
		System.out.println("indexes: " + (subSize*3-1));
		
		RunnerURL thread1 = new RunnerURL(sub1);
		RunnerURL thread2 = new RunnerURL(sub2);
		RunnerURL thread3 = new RunnerURL(sub3);

		thread1.start();
		thread2.start();
		thread3.start();
	}

}
