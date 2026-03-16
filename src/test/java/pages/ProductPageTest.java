package pages;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;

@Listeners(listeners.TestListener.class)
public class ProductPageTest extends BaseTest {
	private ProductPage product;

	@BeforeClass
	public void initPage() {
		product = new ProductPage(DriverFactory.getDriver());
	}

	String filePath = "testData/products.txt";
	String[] products = ReadFileData.readProducts(filePath);

	@Test(priority = 1)
	public void searchProductTest() {
		String itemName = products[0];
		product.searchProduct(itemName);
	}

	@Test(priority = 2)
	public void allProductListTest() {
		product.allProductList();
	}

	@Test(priority = 3)
	public void addTocartTest() {
		product.addTocart();
	}

	@Test(priority = 4)
	public void addMultipleProductsTest() {
		int count = 0;
		for (String item : products) {
			product.searchProduct(item);
			product.allProductList();
			product.addTocart();
			count++;
			if (count >= 3) {
				break;
			}
		}
	}

	@Test(priority = 5)
	public void refreshCartPageTest() {
		product.refreshCartPage();
	}

	@Test(priority = 6)
	public void openBagTest() {
		product.openBag();
	}

	@Test(priority = 7)
	public void removeProductTest() {
		product.removeProduct();
	}

	@Test(priority = 8)
	public void visitHomePageTest() {
		product.visitHomePage();
	}

	@Test(priority = 9)
	public void validateEmptyCartStateTest() {
		product.validateEmptyCartState();
	}

	/* -----------------Negative Test Cases-------- */
	@Test(priority = 10)
	public void searchEmptyProductTest() {
		product.searchProduct("");
	}

	@Test(priority = 11)
	public void searchInvalidProductTest() {
		product.searchProduct("asdfghjkl12345");
	}

	@Test(priority = 12)
	public void searchSpecialCharactersTest() {
		product.searchProduct("@#$%^&*");
	}

	@Test(priority = 13)
	public void productListEmptyTest() {
		product.searchProduct("invalidproductname");
		product.allProductList();
	}

	@Test(priority = 14)
	public void addProductWithoutSizeTest() {
		product.searchProduct("Tshirt");
		product.allProductList();
		product.addTocart();
	}

	@Test(priority = 15)
	public void addSameProductMultipleTimesTest() {
		product.searchProduct(products[0]);
		product.allProductList();
		product.addTocart();
		product.addTocart();
	}

	@Test(priority = 16)
	public void removeFromEmptyCartTest() {
		product.openBag();
		product.removeProduct();
	}

	@Test(priority = 17)
	public void refreshEmptyCartTest() {
		product.openBag();
		product.refreshCartPage();
	}

	@Test(priority = 18)
	public void visitHomePageWithoutCartTest() {
		product.visitHomePage();
	}

	@Test(priority = 19)
	public void invalidFilePathTest() {
		String[] products = ReadFileData.readProducts("invalid/path.txt");
	}

	@Test(priority = 20)
	public void searchLongStringTest() {
		product.searchProduct("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}

	@Test(priority = 21)
	public void validateEmptyCartWithoutAddingProductTest() {
		product.openBag();
		product.validateEmptyCartState();
	}
}