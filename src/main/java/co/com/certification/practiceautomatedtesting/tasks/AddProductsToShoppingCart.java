package co.com.certification.practiceautomatedtesting.tasks;

import co.com.certification.practiceautomatedtesting.interactions.AddProduct;
import co.com.certification.practiceautomatedtesting.questions.CheckNameProduct;
import co.com.certification.practiceautomatedtesting.utils.various.EnumVariablesSesion;
import lombok.AllArgsConstructor;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;


@AllArgsConstructor
public class AddProductsToShoppingCart implements Task {
    private final int products;

    public <T extends Actor> void performAs(T actor) {
        for (int i = 1; i <= products; i++) {
            String productId = getProductPosition(i);
            actor.attemptsTo(
                    AddProduct.buyProduct(productId)
            );
            Serenity.setSessionVariable(EnumVariablesSesion.SESSION_PRODUCT.getValue().concat(String.valueOf(i))).
                    to(CheckNameProduct.productName(productId).answeredBy(actor));
        }
    }

    public static String getProductPosition(int iteration) {
        String productId;
        switch (iteration) {
            case 1:
            case 4:
                productId = "1";
                break;
            case 2:
            case 5:
                productId = "2";
                break;
            case 3:
            case 6:
                productId = "3";
                break;
            default:
                throw new IllegalArgumentException("NUMBER INVALID PRODUCTS");
        }
        return productId;
    }

    public static AddProductsToShoppingCart addProductCart(int products) {
        return instrumented(AddProductsToShoppingCart.class, products);
    }

}
