package co.com.certification.practiceautomatedtesting.questions;

import co.com.certification.practiceautomatedtesting.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CheckApplicationOpening implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        return HomePage.HOME_PAGE.resolveFor(actor).isVisible();
    }

    public static CheckApplicationOpening applicationOpening() {
        return new CheckApplicationOpening();
    }

}
