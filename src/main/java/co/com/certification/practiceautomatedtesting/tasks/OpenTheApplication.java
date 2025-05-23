package co.com.certification.practiceautomatedtesting.tasks;

import co.com.certification.practiceautomatedtesting.interactions.OpenApplication;
import co.com.certification.practiceautomatedtesting.questions.CheckApplicationOpening;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.hamcrest.CoreMatchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;

@Slf4j
@AllArgsConstructor
public class OpenTheApplication implements Task {
    private final String url;

    public <T extends Actor> void performAs(T actor) {
        log.info("BUY PRODUCTS URL: " + url);
        actor.attemptsTo(OpenApplication.startBrowser(url));
        actor.should(seeThat(CheckApplicationOpening.applicationOpening(), CoreMatchers.equalTo(true)));
    }

    public static OpenTheApplication startApplication(String url) {
        return instrumented(OpenTheApplication.class, url);
    }

}
