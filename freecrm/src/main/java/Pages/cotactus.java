
package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class cotactus {

	private WebDriver d1;
	  private WebDriverWait wait1;
	@FindBy(xpath = "//*[@href=\"/contacts\"]")
	WebElement btn_user;
	
	@FindBy(xpath = "//*[@id=\"main-nav\"]/div[3]/button/i")
	WebElement btn_contactus; 
	
	
	
	
	@FindBy(name="first_name")
	WebElement txt_firstname;
	
	@FindBy(name="last_name")
	WebElement txt_lastname;
	
	@FindBy(xpath= "//*[@class=\"save icon\"]")
	WebElement btn_save;
	
	
    @FindBy(xpath ="//*[@class=\"ui icon button\"]")
    WebElement btn_task;
    
	public cotactus(WebDriver d1)
	{
		this.d1 = d1;
        this.wait1 = new WebDriverWait(d1, Duration.ofSeconds(10));
		PageFactory.initElements(d1, this);
		
	}
	
	public void user()
	{
		wait1.until(ExpectedConditions.visibilityOf(btn_user));
		btn_user.click();
	}
	
	public void contactus()
	{
		wait1.until(ExpectedConditions.visibilityOf(btn_contactus));
		btn_contactus.click();
	}
	
	
	
	public void firstname(String firstname)
	{
		txt_firstname.sendKeys(firstname);
	}
	
	public void lastname(String lastname)
	{
		txt_lastname.sendKeys(lastname);
	}
	
	public void save()
	{
		btn_save.click();
	}
	
    public boolean task()
    {
    	boolean task = btn_task.isDisplayed();
        return task;	
    }
}
