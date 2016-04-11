package Executor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Runner implements Runnable{
	private URL url;

	public Runner(URL url) {
		this.url = url;
	}

	@Override
	public void run() {
		File file;
		String fileName;
		FileOutputStream fout;
		BufferedInputStream bis;

		try {
				String[] fPath = url.toString().split("/");
				fileName = "Lab2/files/" + fPath[fPath.length - 1];
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
