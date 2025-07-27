package extensions;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class DriverExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(DriverExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        WebDriver driver = DriverManager.getDriver();
        context.getStore(NAMESPACE).put("driver", driver);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        WebDriver driver = (WebDriver) context.getStore(NAMESPACE).remove("driver", WebDriver.class);
        if (driver != null) {
            DriverManager.quitDriver();
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == WebDriver.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return extensionContext.getStore(NAMESPACE).get("driver", WebDriver.class);
    }

    public static WebDriver getDriver(ExtensionContext context) {
        return context.getStore(NAMESPACE).get("driver", WebDriver.class);
    }
}
