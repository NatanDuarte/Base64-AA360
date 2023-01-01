import com.automationanywhere.botcommand.Base64Decoder;
import com.automationanywhere.botcommand.data.impl.StringValue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBase64Decoder {
    @Test
    public void ShouldEncodeAnStringMessage() {
        String encodedMessage = "QSB0ZXN0IG1lc3NhZ2U=";

        Base64Decoder decoder = new Base64Decoder();
        StringValue decodedMessage = decoder.action(encodedMessage);

        Assert.assertEquals(decodedMessage.get(), "A test message");
    }
}
