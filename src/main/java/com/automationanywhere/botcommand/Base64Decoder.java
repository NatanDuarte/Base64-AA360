package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.utils.Base64Utils;

import java.util.Objects;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

//BotCommand makes a class eligible for being considered as an action.
@BotCommand

//CommandPks adds required information to be dispalable on GUI.
@CommandPkg(
        //Unique name inside a package and label to display.
        name = "Base64Decoder",
        label = "[[Base64DecoderDetails.label]]",
        node_label = "[[Base64DecoderDetails.node_label]]",
        description = "[[Base64DecoderDetails.description]]", icon = "pkg.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[Base64DecoderDetails.return_label]]",
        return_type = STRING,
        return_required = true,
        return_description = "[[Base64DecoderDetails.return_label_description]]"
)
public class Base64Decoder {

    //Identify the entry point for the action. Returns a Value<String> because the return type is String.
    @Execute
    public StringValue action(
            //Idx 1 would be displayed first, with a text box for entering the value.
            @Idx(index = "1", type = TEXT)
            //UI labels.
            @Pkg(label = "[[Base64DecoderDetails.string_message.label]]")
            //Ensure that a validation error is thrown when the value is null.
            @NotEmpty
            String base64String
    ) {
        //Internal validation, to disallow empty strings. No null check needed as we have NotEmpty on firstString.
        if ("".equals(base64String.trim()))
            throw new BotCommandException("[[Base64DecoderDetails.error_string_empty]]");

        //Business logic
        String revealedMessage = doBusinessLogic(base64String);

        //Return NumberValue
        return new StringValue(revealedMessage);
    }

    private static String doBusinessLogic(String base64String) {
        Objects.requireNonNull(base64String, "[[Base64DecoderDetails.error_string_empty]]");
        String revealedMessage;
        try {
            revealedMessage = Base64Utils.decode(base64String);
        } catch (Exception e) {
            throw new BotCommandException("[[Base64DecoderDetails.error_on_decoding]]" + e);
        }
        return revealedMessage;
    }
}
