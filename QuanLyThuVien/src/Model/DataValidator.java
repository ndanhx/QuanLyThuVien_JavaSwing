
package Model;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.*;
/**
 *
 * @author Duy Anh
 */

public class DataValidator {
    
    public static void validateEmpty(JTextField field, StringBuilder sb, String errorMessage){
        if(field.getText().equals("")){
            sb.append(errorMessage).append("\n");
            field.setBackground(Color.pink);
            field.requestFocus();
        }else{
            field.setBackground(Color.white);
        }
    }
    
    public static void validateEmpty(JPasswordField field, StringBuilder sb, String errorMessage){
        String password = new String(field.getPassword());
        if(password.equals("")){
            sb.append(errorMessage).append("\n");
            field.setBackground(Color.red);
            field.requestFocus();
        }else{
            field.setBackground(Color.white);
        }
    }
    
}
