package helper;

import org.zkoss.zul.Textbox;

/**
 *
 * @author joser
 */
public class Validation  {
    public static boolean ValidarCamposVacios(Textbox... textbox) {
        for (Textbox textField : textbox) {
            if (textField.getText().isEmpty()) {
                return true;
            }
        }
    return false;
    }
}