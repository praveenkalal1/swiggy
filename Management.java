package swiggy.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swiggy.helper.LoggerHelper;
import swiggy.helper.WaitHelper;


public class Management {
	
	WebDriver driver;
	private Logger log= LoggerHelper.getLogger(Management.class);
	
	@FindBy(xpath="//*[@id='root']/div[1]/descendant::span[14]")
	WebElement search;
	
	@FindBy(xpath="//*[@id='root']/div[1]/descendant::span[9]")
	WebElement profileName;
	
	@FindBy(xpath="//*[@id='root']/div[1]/descendant::span[14]")
	WebElement clickSearch;
	
	
	public Management(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WaitHelper wait=new WaitHelper(driver);
		wait.waitForElement(driver, search, 200);
		
	}
	
	public void readText(){
		log.info("read the profile name");
		String x=this.profileName.getText();
		System.out.println(x);
	}
	
	public void clickSearchButton(){
		log.info("click on search");
		this.clickSearch.click();
	}
	
	public Search mainPage() throws Exception{
		readText();
		clickSearchButton();
		return new Search(driver);
	}
	
	

}
