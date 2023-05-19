import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

//me is here
@Listeners({SoftAsserts.class})
public class SelenidaTests {
    public SelenidaTests()
    {
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1280x800";
        Configuration.baseUrl = "http://the-internet.herokuapp.com";
        Configuration.reportsFolder = "src/main/resources/Failed";

    }

    @Test
    public void dropdownTest()
    {
        open("/dropdown");
        // Select 'Option 2' with containing text 'tion 2'
        $("#dropdown").selectOptionContainingText("tion 2");
        // Validate that 'Option 2' is selected
        try {
            $("#dropdown").shouldHave(value("2"));
        } catch (AssertionError e) {
            captureScreenshot();
            throw e;
        }

    }

    @Test
    public void inputTest() {
        open("/inputs");

        // Fill input field with text '100'
        $("#input").setValue("100");

        // Validate that this input element is empty (failed case for screenshot)
        try {
            $("#input").shouldHave(value(""));
        } catch (AssertionError e) {
            captureScreenshot();
            throw e;
        }
    }

    private void captureScreenshot() {
        String fileName = "FailedScreenshot_" + System.currentTimeMillis() + ".png";
        String folderPath = "src/main/resources/Failed/";
        screenshot(folderPath + fileName);
    }

}

