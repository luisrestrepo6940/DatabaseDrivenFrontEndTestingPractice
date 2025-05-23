package co.com.certification.practiceautomatedtesting.interactions;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class OpenApplication implements Interaction {
    private String url;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }

    public static OpenApplication startBrowser(String url) {
        return instrumented(OpenApplication.class, url);
    }

}
