package co.com.certification.practiceautomatedtesting.tasks;

import co.com.certification.practiceautomatedtesting.interactions.EnterInformation;
import co.com.certification.practiceautomatedtesting.interactions.PressButton;
import co.com.certification.practiceautomatedtesting.questions.CheckApplicationAccess;
import co.com.certification.practiceautomatedtesting.userinterfaces.LoginPage;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.Tasks.instrumented;


@AllArgsConstructor
public class Login implements Task {
    private final String username;
    private final String password;

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                EnterInformation.information(username, LoginPage.USER_NAME),
                EnterInformation.information(password, LoginPage.PASSWORD),
                PressButton.button(LoginPage.LOGIN_BUTTON)
        );
        actor.attemptsTo(
                Ensure.that(CheckApplicationAccess.applicationAccess().answeredBy(actor)).isEqualTo("Products")
        );
    }

    public static Login authenticating(String username, String password) {
        return instrumented(Login.class, username, password);
    }

}
