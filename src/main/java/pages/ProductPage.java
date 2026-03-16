package pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;
import utils.WaitUtils;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
	private WebElement searchBox;

	public void searchProduct(String itemName) {
		WaitUtils.waitForElementToBeVisible(searchBox);
		searchBox.clear();
		searchBox.sendKeys(itemName);
		searchBox.sendKeys(Keys.ENTER);
	}

	@FindBy(xpath = "//div[@class='product-productMetaInfo']")
	private List<WebElement> productList;

	public void allProductList() {
		WaitUtils.waitForElementToBeVisible(productList.get(0));
		System.out.println("Product Size: " + productList.size());
		productList.get(0).click();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String window : windowHandles) {
			driver.switchTo().window(window);
		}
	}

	@FindBy(xpath = "//div[normalize-space()='ADD TO BAG']")
	private WebElement addToBag;

	public void addTocart() {
		WaitUtils.waitForElementToBeClickable(addToBag);
		addToBag.click();
	}

	@FindBy(xpath = "//span[normalize-space()='Bag']")
	private WebElement bagButton;

	public void openBag() {
		bagButton.click();
	}

	public void refreshCartPage() {
		driver.navigate().refresh();
	}

	@FindBy(xpath = "//button[normalize-space()='REMOVE']")
	private List<WebElement> removeButtons;
	@FindBy(xpath = "//button[normalize-space()='REMOVE']")
	private WebElement confirmRemoveButton;
	@FindBy(xpath = "//div[contains(text(),'ADD ITEMS FROM WISHLIST')]")
	private WebElement addToWishlist;

	public void removeProduct() {
		WaitUtils.waitForUrlContains("cart");
		List<WebElement> removeBtns = driver.findElements(By.xpath("//button[text()='REMOVE']"));
		while (removeBtns.size() > 0) {
			WaitUtils.waitForElementToBeClickable(removeBtns.get(0));
			removeBtns.get(0).click();
			WebElement confirmBtn = driver.findElement(By.xpath("//div[@role='dialog']//button[text()='REMOVE']"));
			WaitUtils.waitForElementToBeClickable(confirmBtn);
			confirmBtn.click();
			removeBtns = driver.findElements(By.xpath("//button[text()='REMOVE']"));
		}
	}
	public void visitHomePage() {
		driver.navigate().to("https://www.myntra.com/");
	}

	@FindBy(xpath = "//div[contains(text(),'ADD ITEMS FROM WISHLIST')]")
	private WebElement emptyCartMessage;
	@FindBy(xpath = "//span[contains(@class,'desktop-badge')]")
	private WebElement cartBadge;
	@FindBy(xpath = "//a[contains(@class,'desktop-cart')]")
	private WebElement cartIcon;

	public void validateEmptyCartState() {
		WaitUtils.waitForUrlContains("cart");
		WaitUtils.waitForElementToBeVisible(emptyCartMessage);
		if (emptyCartMessage.isDisplayed()) {
			System.out.println("Cart is empty");
		} else {
			System.out.println("Cart is NOT empty");
		}
		try {
			WaitUtils.waitForElementToBeVisible(cartBadge);
			String badgeCount = cartBadge.getText();
			if (badgeCount.equals("0")) {
				System.out.println("Cart badge count is 0");
			} else {
				System.out.println("Cart badge count is: " + badgeCount);
			}
		} catch (Exception e) {
			System.out.println("Cart badge not visible → Cart is empty");
		}
	}
}