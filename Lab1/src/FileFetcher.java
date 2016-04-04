package Lab1.src;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFetcher {
	private static String url;
	private Pattern pattern = Pattern.compile("<a[^>]* href=\"([^\"]*.pdf)\"");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		url = scan.nextLine();

		FileFetcher f = new FileFetcher();
		List<URL> links = new ArrayList<URL>();
		links = f.getPdfLinks(url);
		int i = 0;
		for (URL u : links) {
			downloadFile(u, ("Lab1/files/file" + i));
			i++;
		}
	}

	public List<URL> getPdfLinks(String url) {
		ArrayList<URL> links = new ArrayList<URL>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String s;
			while ((s = bufferedReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(s);
				if(matcher.find()){
					System.out.println(matcher.group(1));
					links.add(new URL(matcher.group(1)));
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return links;
	}

	private static void downloadFile(URL url, String fileName) {
		File file;
		FileOutputStream output;
		BufferedInputStream inputStream;

		try {
			file = new File(fileName);
			file.createNewFile();
			output = new FileOutputStream(file);
			inputStream = new BufferedInputStream(url.openStream());

			byte[] buffer = new byte[4096];
			int bytes;
			while ((bytes = inputStream.read(buffer)) != -1) {
				output.write(buffer, 0, bytes);
			}

			inputStream.close();
			output.flush();
			output.close();

			System.out.println("File downloaded");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
