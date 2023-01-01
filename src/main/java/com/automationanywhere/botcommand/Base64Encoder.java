package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.utils.Base64Utils;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.NUMBER;

//BotCommand makes a class eligible for being considered as an action.
@BotCommand

//CommandPks adds required information to be dispalable on GUI.
@CommandPkg(
        //Unique name inside a package and label to display.
        name = "Base64Encoder",
        label = "[[Base64EncoderDetails.label]]",
        node_label = "[[Base64EncoderDetails.node_label]]",
        description = "[[Base64EncoderDetails.description]]", icon = "pkg.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[Base64EncoderDetails.return_label]]",
        return_type = NUMBER,
        return_required = true,
        return_description = "[[Base64EncoderDetails.return_label_description]]"
)
public class Base64Encoder {

    //Identify the entry point for the action. Returns a Value<String> because the return type is String.
    @Execute
    public StringValue action(
            //Idx 1 would be displayed first, with a text box for entering the value.
            @Idx(index = "1", type = TEXT)
            //UI labels.
            @Pkg(label = "[[Base64EncoderDetails.filePath.label]]")
            //Ensure that a validation error is thrown when the value is null.
            @NotEmpty
            String message
    ) {

        //Internal validation, to disallow empty strings. No null check needed as we have NotEmpty on firstString.
        if ("".equals(message.trim()))
            throw new BotCommandException("A mensagem não pode ser vazia");


        //Business logic
        String base64 = doBusinessLogic(message);

        //Return NumberValue
        return new StringValue(base64);
    }

    private static String doBusinessLogic(String message) {
        String base64;
        try {
            base64 = Base64Utils.encode(message);
        } catch (Exception e) {
            throw new BotCommandException("Erro ao realizar encoding " + e);
        }
        return base64;
    }
}
