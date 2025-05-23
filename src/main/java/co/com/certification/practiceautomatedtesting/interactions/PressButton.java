package co.com.certification.practiceautomatedtesting.interactions;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class PressButton implements Interaction {
    private Target button;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(button)
        );
    }

    public static PressButton button(Target target) {
        return instrumented(PressButton.class, target);
    }

}
