package co.com.certification.practiceautomatedtesting.stepdefinitions;

import co.com.certification.practiceautomatedtesting.questions.CheckConfirmationMessage;
import co.com.certification.practiceautomatedtesting.tasks.*;
import co.com.certification.practiceautomatedtesting.utils.various.ManageParametersConnection;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.model.util.EnvironmentVariables;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static co.com.certification.practiceautomatedtesting.utils.constants.Constants.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class BuyProductsWebsite {
    protected EnvironmentVariables environmentVariables;
    private List<Map<String, String>> mapList;

    @Before
    public void setUpStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(PRODUCT_OWNER);
    }
    @Given("the user access to the web site")
    public void theUserAccessToTheWebSite() {
        theActorInTheSpotlight().attemptsTo(OpenTheApplication.startApplication(ManageParametersConnection.getUrlBase(BUY_PRODUCTS_URL)));
    }

    @And("user login")
    public void userLogin(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(Login.authenticating(mapList.get(0).get(USER), mapList.get(0).
                get(PASSWORD)));
    }

    @When("user adds the products to the cart")
    public void userAddsTheProductsToTheCart(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(AddProductsToShoppingCart.addProductCart(Integer.parseInt(mapList.get(0).
                get(AMOUNT))));
    }

    @Then("user can view the products added to the shopping cart")
    public void userCanViewTheProductsAddedToTheShoppingCart(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(CheckShoppingCart.checkShopping(mapList.get(0).get(AMOUNT)));
    }

    @And("user fills out the purchase form")
    public void userFillsOutThePurchaseForm(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(CheckOutInformation.information(mapList.get(0).get(FIRST_NAME),
                mapList.get(0).get(LAST_NAME), mapList.get(0).get(POSTAL_CODE)));
    }

    @And("user can Checkout until confirmation")
    public void userCanCheckoutUntilConfirmation(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().should(seeThat(CheckConfirmationMessage.getMessage(mapList.get(0).
                get(CONFIRMATION_MESSAGE)), Matchers.is(true)));
    }
}
