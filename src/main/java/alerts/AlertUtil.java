package alerts;
import org.openqa.selenium.Alert;
import base.DriverFactory;
public class AlertUtil {
    public static void acceptAlert() {
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.accept();
    }
    public static void dismissAlert() {
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.dismiss();
    }
    public  static String getAlertText() {
        return DriverFactory.getDriver().switchTo().alert().getText();
    }
}
