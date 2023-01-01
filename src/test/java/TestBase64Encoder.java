import com.automationanywhere.botcommand.Base64Encoder;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBase64Encoder {
    @Test
    public void ShouldEncodeAnStringMessage() {
        String message = "A test message";

        Base64Encoder encoder = new Base64Encoder();
        StringValue base64Message = encoder.action(message);

        Assert.assertEquals(base64Message.get(), "QSB0ZXN0IG1lc3NhZ2U=");
    }

    @Test
    public void ShouldNotEncodeAnEmptyString() {
        String message = "";

        Base64Encoder encoder = new Base64Encoder();

        BotCommandException e = Assert.expectThrows(BotCommandException.class, () -> encoder.action(message));
        Assert.assertTrue(e.getMessage().contentEquals("A mensagem não pode ser vazia"));
    }
}
