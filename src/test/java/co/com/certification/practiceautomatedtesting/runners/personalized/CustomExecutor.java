package co.com.bancolombia.certificacion.botondetransferencia.runners.personalizados;

import co.com.certification.practiceautomatedtesting.utils.various.BeforeSuite;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Personalizacion del Runner con el cual se puede determinar que busque y modifique los archivos .feature antes de ser ejecutados.
 */
public class CustomExecutor extends Runner {

    private static final Logger LOGGER = LogManager.getLogger(co.com.bancolombia.certificacion.botondetransferencia.runners.personalizados.CustomExecutor.class.getName());
    private final Class<CucumberWithSerenity> classValue;
    private CucumberWithSerenity cucumberWithSerenity;

    public CustomExecutor(Class<CucumberWithSerenity> classValue) throws InitializationError {
        this.classValue = classValue;
        cucumberWithSerenity = new CucumberWithSerenity(classValue);
    }

    @Override
    public Description getDescription() {
        return cucumberWithSerenity.getDescription();
    }

    private void runAnnotatedMethods() throws InvocationTargetException, IllegalAccessException {
        if (!BeforeSuite.class.isAnnotation()) {
            return;
        }
        Method[] methods = this.classValue.getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation item : annotations) {
                if (item.annotationType().equals(BeforeSuite.class)) {
                    method.invoke(null);
                    break;
                }
            }
        }
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            runAnnotatedMethods();
            cucumberWithSerenity = new CucumberWithSerenity(classValue);
        } catch (InvocationTargetException | IllegalAccessException | InitializationError e) {
            LOGGER.info(e);
        }
        cucumberWithSerenity.run(notifier);
    }
}