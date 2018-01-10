package helper;

import org.zkoss.zul.Label;
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
    
    public static boolean ValidarLabelsNulos(Label... label) {
        for (Label labelField : label) {
            if (labelField.getValue().isEmpty()) {
                return true;
            }
        }
    return false;
    }
    
    public static boolean ValidarStringsVacios(String... cadena) {
        for (String cadenaField : cadena) {
            if (cadenaField.isEmpty()) {
                return true;
            }
        }
    return false;
    }
}