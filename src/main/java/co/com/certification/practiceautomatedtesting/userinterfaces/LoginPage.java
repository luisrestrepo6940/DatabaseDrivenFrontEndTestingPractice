package co.com.certification.practiceautomatedtesting.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class LoginPage extends PageObject {

    public static Target USER_NAME = Target.the("user name").locatedBy("//div[@class='form_group']//input[@id='user-name']");
    public static final Target PASSWORD = Target.the("password").locatedBy("//div[@class='form_group']//input[@id='password']");
    public static final Target LOGIN_BUTTON = Target.the("login button").locatedBy("//input[@id='login-button']");
    public static final Target SESSION_STARTED = Target.the("session started").locatedBy("//span[@class='title']");
}
