package co.com.certification.practiceautomatedtesting.tasks;

import co.com.certification.practiceautomatedtesting.interactions.PressButton;
import co.com.certification.practiceautomatedtesting.questions.CheckNameProduct;
import co.com.certification.practiceautomatedtesting.questions.CheckQuantityProducts;
import co.com.certification.practiceautomatedtesting.userinterfaces.ProductsPage;
import co.com.certification.practiceautomatedtesting.userinterfaces.ShoppingCartPage;
import co.com.certification.practiceautomatedtesting.utils.various.EnumVariablesSesion;
import lombok.AllArgsConstructor;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class CheckShoppingCart implements Task {
    private final String quantity;

    @Override
    @Step("check shopping cart")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Ensure.that(CheckQuantityProducts.quantityProducts().answeredBy(actor)).isEqualTo(quantity)
        );

        for (int i = 1; i <= Integer.parseInt(quantity); i++) {
            String productNumber = AddProductsToShoppingCart.getProductPosition(i);
            actor.attemptsTo(
                    Ensure.that(CheckNameProduct.productName(productNumber).answeredBy(actor)).
                            isEqualTo(Serenity.sessionVariableCalled(EnumVariablesSesion.SESSION_PRODUCT.getValue().
                                    concat(productNumber)))
            );
        }

        actor.attemptsTo(
                PressButton.button(ProductsPage.SHOPPING_CART)
        );

        actor.attemptsTo(
                PressButton.button(ShoppingCartPage.CHECKOUT)
        );
    }

    public static CheckShoppingCart checkShopping(String quantity) {
        return instrumented(CheckShoppingCart.class, quantity);
    }

}
