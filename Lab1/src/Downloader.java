import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.security.auth.callback.Callback;

public class Downloader {
	private URL url;
	private String fileName;
	private Callback callback;
	
	public Downloader(URL url, String fileName, Callback callback) {
		this.url = url;
		this.fileName = fileName;
		this.callback = callback;
	}

	public void run() {
		File file;
		FileOutputStream fout;
		BufferedInputStream bis;

		try {
			file = new File(fileName);
			file.createNewFile();
			fout = new FileOutputStream(file);
			bis = new BufferedInputStream(url.openStream());
			
			byte[] buffer = new byte[4096];
			int bytes;
			while ((bytes = bis.read(buffer)) != -1) {
				fout.write(buffer, 0, bytes);
			}

			bis.close();
			fout.flush();
			fout.close();

			System.out.println(fileName + " done!");
			callback.isDone();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
