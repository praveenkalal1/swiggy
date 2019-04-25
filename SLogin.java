package swiggy.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swiggy.helper.LoggerHelper;
import swiggy.helper.WaitHelper;


public class SLogin {
	
	private WebDriver	driver;
	
	private static Logger log= LoggerHelper.getLogger(SLogin.class);
	
	@FindBy(xpath="//*[@id='root']/div[1]/div[1]/div/div[1]/div[1]/div/div[1]/div/a[1]")
	WebElement signIn;
	
	@FindBy(id="mobile")
	WebElement userName;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(xpath="//*[@id='overlay-sidebar-root']/descendant::a[2]")
	WebElement logIn;

	
	public SLogin(WebDriver driver) throws Exception {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WaitHelper wait=new WaitHelper(driver);
		wait.waitForElement(driver, signIn, 200);
		
		
	}
	
	public void clickSignIn(){
		log.info("click on sign in");
		this.signIn.click();
	}
	
	public void enterUsername(String userName){
		log.info("Enter Username:  "+userName);
		this.userName.sendKeys(userName);
	}
	
	public void enterPassword(String password){
		log.info("Enter Password:  "+password);
		this.password.sendKeys(password);
	}
	
	public void clickLogIn(){
		log.info("click on Login");
		this.logIn.click();
	}
	
	public Management loginModule(String userName){
		clickSignIn();
		enterUsername(userName);		
		clickLogIn();		
		return new Management(driver);
		
	}

}
