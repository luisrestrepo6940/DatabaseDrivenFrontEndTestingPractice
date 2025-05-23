package co.com.certification.practiceautomatedtesting.questions;

import co.com.certification.practiceautomatedtesting.userinterfaces.CheckoutComplete;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

@AllArgsConstructor
public class CheckConfirmationMessage implements Question<Boolean> {
    public String confirmationMessage;
    @Override
    public Boolean answeredBy(Actor actor) {
        return CheckoutComplete.THANK_YOU_ORDER.resolveFor(actor).isVisible();
    }

    public static CheckConfirmationMessage getMessage(String confirmationMessage) {
        return new CheckConfirmationMessage(confirmationMessage);
    }
}
