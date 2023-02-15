package ex03_Reader;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		System.out.println();
		try {
		    File file = new File("/Users/mong/Documents" + File.separator + "storage", "product.csv");
		    String strings = FileUtils.readFileToString(file, "UTF-8");
		    System.out.println(strings);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
