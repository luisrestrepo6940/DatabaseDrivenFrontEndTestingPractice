package co.com.certification.practiceautomatedtesting.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class ProductsPage extends PageObject {
    public static final Target SHOPPING_CART = Target.the("password").locatedBy("//div[@id='shopping_cart_container']");
    static String xpathAddToCart = "(//button[@class='btn btn_primary btn_small btn_inventory '])[%s]";
    static String xpathProductName = "(//div[@class='inventory_item_name '])[%s]";

    public static String addToCart(String value) {
        return String.format(xpathAddToCart, value);
    }

    public static Target getProductName(String value) {
        String xpath = String.format(xpathProductName, value);
        return Target.the("user name").locatedBy(xpath);
    }

}
