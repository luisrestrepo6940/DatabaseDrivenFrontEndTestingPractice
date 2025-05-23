package co.com.certification.practiceautomatedtesting.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class FillOutForm extends PageObject {
    public static final Target FIRST_NAME = Target.the("first name").locatedBy("//input[@id='first-name']");
    public static final Target LAST_NAME = Target.the("last name").locatedBy("//input[@id='last-name']");
    public static final Target POSTAL_CODE = Target.the("postal code").locatedBy("//input[@id='postal-code']");
    public static final Target CONTINUE = Target.the("continue").locatedBy("//input[@id='continue']");

}
