package Executor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.security.auth.callback.Callback;

public class Runner extends Thread{
	private URL url;
	private String fileName;
	
	public Runner(URL url, String fileName) {
		this.url = url;
		this.fileName = fileName;
	}

	@Override
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

			System.out.println("Runner Downloaded: " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
