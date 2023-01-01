import com.automationanywhere.utils.Base64Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBase64Utils {

    @Test
    public void ShouldEncodeAnStringMessage() {
        String message = "A test message";

        String base64Message = Base64Utils.encode(message);

        Assert.assertEquals(base64Message, "QSB0ZXN0IG1lc3NhZ2U=");
    }

    @Test
    public void ShouldDecodeAnStringMessage() {
        String base64Message = "QSB0ZXN0IG1lc3NhZ2U=";

        String message = Base64Utils.decode(base64Message);

        Assert.assertEquals(message, "A test message");
    }
}
