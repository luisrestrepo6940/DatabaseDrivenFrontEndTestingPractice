package co.com.certification.practiceautomatedtesting.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class ShoppingCartPage extends PageObject {
    public static final Target QUANTITY_PRODUCTS = Target.the("quantity products").locatedBy("//span[@class='shopping_cart_badge']");
    public static final Target CHECKOUT = Target.the("checkout").locatedBy("//button[@id='checkout']");

}
