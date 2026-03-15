package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class ProductPage extends BasePage {
	public ProductPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@class='desktop-main'][normalize-space()='Men']")
	private WebElement menCategory;
	@FindBy(xpath = "//a[normalize-space()='Topwear']")
	private WebElement topwear;
	@FindBy(xpath = "//a[normalize-space()='T-Shirts']")
	private WebElement tshirts;

	public void navigateToTshirts() {
		Actions action = new Actions(driver);
		action.moveToElement(menCategory).perform();
		action.moveToElement(topwear).perform();
		tshirts.click();
	}

	@FindBy(xpath = "//li[@class='product-base']")
	private List<WebElement> productList;
	@FindBy(xpath = "//span[@class='product-discountedPrice']")
	private List<WebElement> productPrices;

	public List<Integer> getFirstFiveProductPrices() {
		List<Integer> priceList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			String priceText = productPrices.get(i).getText();
			priceText = priceText.replace("Rs. ", "").replace(",", "");
			int price = Integer.parseInt(priceText);
			priceList.add(price);
		}
		return priceList;
	}

	@FindBy(xpath = "//div[@class='sort-sortBy']")
	private WebElement sortDropDown;
	@FindBy(xpath = "//label[contains(text(),'Price: Low to High')]")
	private WebElement priceLowToHighOption;

	public List<Integer> sortProductByPriceLowToHigh() {
		sortDropDown.click();
		priceLowToHighOption.click();
		waitForProductsToReload();
		return getFirstFiveProductPrices();
	}

	public void waitForProductsToReload() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	            By.xpath("//li[@class='product-base']")));

	}

	public boolean verifyFirstFivePricesSorted() {
		List<Integer> actualPrices = sortProductByPriceLowToHigh();
		List<Integer> sortedPrices = new ArrayList<>(actualPrices);
		Collections.sort(sortedPrices);
		return actualPrices.equals(sortedPrices);
	}

	@FindBy(xpath = "//label[contains(text(),'Price: High to Low')]")
	private WebElement priceHighToLowOption;

	public List<Integer> sortProductByPriceHighToLow() {
		sortDropDown.click();
		priceHighToLowOption.click();
		waitForProductsToReload();
		return getFirstFiveProductPrices();
	}

	public boolean verifyFirstFivePricesSortedDescending() {
		List<Integer> actualPrices = sortProductByPriceHighToLow();
		List<Integer> sortedPrices = new ArrayList<>(actualPrices);
		Collections.sort(sortedPrices, Collections.reverseOrder());
		return actualPrices.equals(sortedPrices);
	}
}