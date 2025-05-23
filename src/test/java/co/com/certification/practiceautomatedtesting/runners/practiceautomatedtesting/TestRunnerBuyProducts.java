package co.com.certification.practiceautomatedtesting.runners.practiceautomatedtesting;

import co.com.certification.practiceautomatedtesting.utils.various.BeforeSuite;
import co.com.certification.practiceautomatedtesting.utils.various.GetDataForFeature;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static co.com.certification.practiceautomatedtesting.utils.constants.Constants.RUNNER_CLASS;

@RunWith(co.com.bancolombia.certificacion.botondetransferencia.runners.personalizados.CustomExecutor.class)
@CucumberOptions(
        features = "src/test/resources/features/BuyProducts.feature",
        glue = "co/com/certification/practiceautomatedtesting/stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = "pretty"
)
public class TestRunnerBuyProducts {
    public TestRunnerBuyProducts() {
        throw new UnsupportedOperationException(RUNNER_CLASS);
    }

    @BeforeSuite
    public static void test() {
        GetDataForFeature.writeFeatureWithDataDriven("./src/test/resources/features/BuyProducts.feature");
    }
}
