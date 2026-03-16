package pages;

public class ReadFileDataTest {
	public static void main(String[] args) {
		String filePath = "testData/products.txt";
		String[] products = ReadFileData.readProducts(filePath);
		System.out.println("Products from file:");
		System.out.println("==========================");
		for (String product : products) {
			if (product != null) {
				System.out.println(product);
			}
		}
	}
}
