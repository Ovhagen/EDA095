package Executor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFetcher {
	private Pattern pattern = Pattern.compile("<a[^>]* href=\"([^\"]*.pdf)\"");
	private ArrayList<URL> links;
	public FileFetcher (){}

	public void download(String url) {
		links = new ArrayList<URL>();
		try {
			URL link = new URL(url);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(link.openStream()));
			String s;
			while ((s = bufferedReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(s);
				while(matcher.find()){
					System.out.println(matcher.group(1));
					links.add(new URL(link, matcher.group(1)));
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ExecutorService service = Executors.newFixedThreadPool(7);
		for(URL u : links){
			service.submit(new Runner(u));
		}
		service.shutdown();
		
	}

}
