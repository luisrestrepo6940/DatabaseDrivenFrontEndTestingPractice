package co.com.certification.practiceautomatedtesting.interactions;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class EnterInformation implements Interaction {
    private String value;
    private Target target;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(value).into(target)
        );
    }

    public static EnterInformation information(String value, Target target) {
        return instrumented(EnterInformation.class, value, target);
    }

}
