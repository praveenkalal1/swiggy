package swiggy.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swiggy.helper.LoggerHelper;
import swiggy.helper.WaitHelper;


public class Search {
	
	WebDriver driver;
	
	private static Logger log= LoggerHelper.getLogger(Search.class);
	
		
	@FindBy(xpath="//*[@id='root']/descendant::input")
	WebElement inputSearch;
	
	@FindBy(xpath="//*[@id='root']/div[1]/descendant::div[30]")//div[Contains(text(),'Hotel Swagath')]
	WebElement swagath;
	
	@FindBy(xpath="//*[starts-with(text(),'Habsiguda')]")
	WebElement habsigudaSwagath;
	
	@FindBy(xpath="//*[@id='h-1950595611']/descendant::div[13]")
	WebElement addPaneer;
     
	@FindBy(xpath="//*[@id='h-1950595611']/descendant::div[15]")
	WebElement increaseQuantity;
	
	@FindBy(xpath="//*[@id='h-1950595611']/descendant::div[17]")
	WebElement checkQuantity;
	
	@FindBy(xpath="//*[@id='h-1950595611']/descendant::div[131]")
	WebElement addBiryani;
	
	@FindBy(xpath="//*[@id='cust_id_603941']/label[1]/descendant::label")
	WebElement selectFamilyPack;
	
	@FindBy(xpath="//*[@id='modal-placeholder']/descendant::span[9]")
	WebElement addItem;
	
	@FindBy(xpath="//*[@id='menu-content']/div[2]/div/div[3]/div[3]")
	WebElement checkOut;
	
	@FindBy(xpath="//*[Contains(text(),'Deliver Here')]")
	WebElement deliveryPage;
	
	
	public Search(WebDriver driver) throws Exception {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WaitHelper wait=new WaitHelper(driver);
		wait.waitForElement(driver, inputSearch, 200);
		
	}
	
	public void enterRestaurant(String restaurant) throws InterruptedException{		
		log.info("Enter Restaurant Name: "+restaurant);
		this.inputSearch.sendKeys(restaurant);
		Thread.sleep(2000);
		
	}
	
	public void clickSwagath(){
		log.info("click on swagath");
		this.swagath.click();
		
	}
	
	public void clickHabsigudaSwagath() throws InterruptedException{
		log.info("click on habsiguda swagath");
		this.habsigudaSwagath.click();
		Thread.sleep(2000);
		
	}
	
	public void clickPaneer(){
		WebElement e=driver.findElement(By.xpath("//*[@id='h-1950595611']/descendant::div[13]"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", e);
		log.info("Add Paneer Tikka to cart");
		this.addPaneer.click();
		
	}
	
	public void clickIncrease(){
		log.info("Increase the quantity of paneer tikka");
		this.increaseQuantity.click();
		
	}
	
	public void verify_Qty() throws InterruptedException{
		log.info("Quantity should be increased by '2'");
		String x=this.checkQuantity.getText();
		System.out.println(x);
		Thread.sleep(5000);
		
	}
	
	public void clickBiryani(){		
		log.info("move to the biryani");
		WebElement e=driver.findElement(By.xpath("//*[@id='h-1950595611']/descendant::div[131]"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", e);
		log.info("Add biryani to the cart");
		this.addBiryani.click();
		
		
	}
	
	public void selelctBiryani(){
		log.info("select family pack Biryani");
		this.selectFamilyPack.click();
		try {
			this.selectFamilyPack.isSelected();
		} catch (Exception e) {
			System.out.println("Not Selected");
		}
	}
	
	public void clickAddItem(){
		log.info("Add family pack  to cart");
		this.addItem.click();
		
	}
	
	public void clickCheckOut() throws InterruptedException{
		log.info("click on check out");
		this.checkOut.click();
		Thread.sleep(5000);
		
	}
	
	public void verify_Delivery(){
		WaitHelper wait=new WaitHelper(driver);
		wait.waitForElement(driver, deliveryPage, 200);
		log.info("click on check out");
		String x=this.deliveryPage.getText();
		System.out.println(x);
		
	}
	
	public void deliveryPage(String restaurant) throws InterruptedException{
		enterRestaurant(restaurant);
		clickSwagath();
		clickHabsigudaSwagath();
		clickPaneer();
		clickIncrease();
		verify_Qty();
		clickBiryani();
		selelctBiryani();
		clickAddItem();
		clickCheckOut();
		verify_Delivery();
	}


}
