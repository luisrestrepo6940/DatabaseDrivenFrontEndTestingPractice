package co.com.certification.practiceautomatedtesting.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class CheckoutComplete extends PageObject {
    public static final Target THANK_YOU_ORDER = Target.the("Thank you for your order").locatedBy("//h2[@class='complete-header']");

}
