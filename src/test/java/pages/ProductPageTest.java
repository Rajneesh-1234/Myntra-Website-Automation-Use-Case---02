package pages;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;

public class ProductPageTest extends BaseTest {
	private ProductPage product;

	@BeforeClass
	public void initPage() {
		product = new ProductPage(DriverFactory.getDriver());
	}

	@Test(priority = 1)
	public void navigateToTshirtsTest() {
		product.navigateToTshirts();
	}

	@Test(priority = 2)
	public void firstFiveProductPricesTest() {
		List<Integer> priceList = product.getFirstFiveProductPrices();
		System.out.println("First Five PriceList: ");
		System.out.println(priceList);
	}

	@Test(priority = 3)
	public void sortProductByPriceLowToHighTest() {
		product.sortProductByPriceLowToHigh();
	}

	@Test(priority = 4)
	public void waitForProductsToReloadTest() {
		product.waitForProductsToReload();
	}

	@Test(priority = 5)
	public void verifyFirstFivePricesSorted() {
		Assert.assertTrue(product.verifyFirstFivePricesSorted(), "product price are not sorted in ascending order");
	}

	@Test(priority = 6)
	public void sortProductByPriceHighToLowTest() {
		product.sortProductByPriceHighToLow();
	}

	@Test(priority = 7)
	public void verifyFirstFivePricesSortedDescending() {
		Assert.assertTrue(product.verifyFirstFivePricesSortedDescending(),
				"product price are not sorted in descending order");
	}

	/*-----------Negative Test Cases------------*/
	@Test(priority = 8)
	public void verifyAscendingSortingWithoutSelectingSortOptionTest() {
		List<Integer> priceList = product.getFirstFiveProductPrices();
		System.out.println("Prices without applying sorting:");
		System.out.println(priceList);
		Assert.assertFalse(product.verifyFirstFivePricesSorted(),
				"Products should not be sorted when sort option is not applied");
	}

	@Test(priority = 9)
	public void verifyDescendingSortingWithoutSelectingSortOptionTest() {
		List<Integer> priceList = product.getFirstFiveProductPrices();
		System.out.println("Prices without applying descending sorting:");
		System.out.println(priceList);
		Assert.assertFalse(product.verifyFirstFivePricesSortedDescending(),
				"Products should not be sorted in descending order without selecting sort option");
	}

	@Test(priority = 10)
	public void verifyPriceListIsNotEmptyTest() {
		List<Integer> priceList = product.getFirstFiveProductPrices();
		Assert.assertFalse(priceList.isEmpty(), "Price list should not be empty");
	}

	@Test(priority = 11)
	public void verifyLessThanFiveProductsScenarioTest() {
		List<Integer> priceList = product.getFirstFiveProductPrices();
		Assert.assertTrue(priceList.size() >= 5, "Less than 5 products displayed on the page");
	}

	@Test(priority = 12)
	public void verifySortingFailsIfPageNotLoadedTest() {
		product.sortProductByPriceLowToHigh();
		List<Integer> priceList = product.getFirstFiveProductPrices();
		Assert.assertNotNull(priceList, "Price list should not be null after sorting");
	}

	@Test(priority = 13)
	public void verifyDescendingSortingWithoutReloadTest() {
		product.sortProductByPriceHighToLow();
		List<Integer> priceList = product.getFirstFiveProductPrices();
		Assert.assertFalse(product.verifyFirstFivePricesSorted(),
				"Ascending verification should fail for descending sorted list");
	}

	@Test(priority = 14)
	public void verifyAscendingSortingFailsForDescendingOrderTest() {
		product.sortProductByPriceHighToLow();
		Assert.assertFalse(product.verifyFirstFivePricesSorted(),
				"Ascending order validation should fail for descending sorted products");
	}
}
