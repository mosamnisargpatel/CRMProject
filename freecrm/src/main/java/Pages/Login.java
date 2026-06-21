package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
   private WebDriver d1;
   private WebDriverWait wait;
	@FindBy(name="email")
	WebElement txt_username;
	
	@FindBy(name="password")
	WebElement txt_password;
	
	@FindBy(xpath ="//*[@class=\"ui fluid large blue submit button\"]")
	WebElement btn_login;
	
    @FindBy(xpath = "//*[@class=\"ui basic button item\"]")
    WebElement btn_Home_Help;
    

	
	public Login(WebDriver d1)
	{

        this.d1 = d1;
        this.wait = new WebDriverWait(d1, Duration.ofSeconds(10));
        PageFactory.initElements(d1, this);
    }

    public void enterusername(String username)
    {
    	wait.until(ExpectedConditions.visibilityOf(txt_username));
        txt_username.sendKeys(username);
    }

    public void enterpassword(String password)
    {
    	wait.until(ExpectedConditions.visibilityOf(txt_password));
        txt_password.sendKeys(password);
    }

    public void clickloginbutton()
    {
    	wait.until(ExpectedConditions.visibilityOf(btn_login));
        btn_login.click();
    }
    
    public boolean help() {
    	boolean button = btn_Home_Help.isDisplayed();
    	return button;
    }
    

    
}
