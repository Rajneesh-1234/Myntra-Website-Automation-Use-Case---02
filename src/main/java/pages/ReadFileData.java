package pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileData {
	public static String[] readProducts(String filePath) {
		String[] products = new String[30];
		int index = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				products[index] = line;
				index++;
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		return products;
	}
}
