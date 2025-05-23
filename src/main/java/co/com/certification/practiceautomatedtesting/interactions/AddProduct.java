package co.com.certification.practiceautomatedtesting.interactions;

import co.com.certification.practiceautomatedtesting.userinterfaces.ProductsPage;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class AddProduct implements Interaction {
    private String productPosition;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(ProductsPage.addToCart(productPosition))
        );
    }

    public static AddProduct buyProduct(String productPosition) {
        return instrumented(AddProduct.class, productPosition);
    }

}
