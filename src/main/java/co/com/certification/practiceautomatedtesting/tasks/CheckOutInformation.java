package co.com.certification.practiceautomatedtesting.tasks;

import co.com.certification.practiceautomatedtesting.interactions.EnterInformation;
import co.com.certification.practiceautomatedtesting.interactions.PressButton;
import co.com.certification.practiceautomatedtesting.userinterfaces.CheckoutOverview;
import co.com.certification.practiceautomatedtesting.userinterfaces.FillOutForm;
import lombok.AllArgsConstructor;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class CheckOutInformation implements Task {
    private final String firstName;
    private final String lastName;
    private final String postalCode;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                EnterInformation.information(firstName, FillOutForm.FIRST_NAME),
                EnterInformation.information(lastName, FillOutForm.LAST_NAME),
                EnterInformation.information(postalCode, FillOutForm.POSTAL_CODE)
        );

        actor.attemptsTo(
                PressButton.button(FillOutForm.CONTINUE)
        );

        actor.attemptsTo(
                PressButton.button(CheckoutOverview.FINISH_PURCHASE)
        );
    }

    public static CheckOutInformation information(String firstName, String lastName, String postalCode) {
        return instrumented(CheckOutInformation.class, firstName, lastName, postalCode);
    }

}
