package utils;

import entity.Register;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import swing.MyOptionPane;

import javax.swing.*;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    private final JPanel view;

    public Validator(JPanel view) {
        this.view = view;
    }

    public boolean isNotEmpty(JTextField field, String message) {
        String text = field.getText();
        if (text == null || "".equals(text.trim())) {
            field.requestFocus();
            MyOptionPane.showMessageDialog(view, message);
            return false;
        }
        return true;
    }

    public boolean isRegexMatch(JTextField field, String regex, String message) {
        String text = field.getText();
        if (!Pattern.matches(regex, text)) {
            field.requestFocus();
            MyOptionPane.showMessageDialog(view, message);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean isNotExisted(JTextField field, List<?> list, String property, String message) {
        String text = field.getText();
        List<String> listProperty = (List<String>) CollectionUtils.collect(list,
                new BeanToPropertyValueTransformer(property));
        if (listProperty.contains(text)) {
            field.requestFocus();
            MyOptionPane.showMessageDialog(view, message);
            return false;
        }
        return true;
    }

    // Only check existence for Register(username, password)
    public boolean isExistedRegister(JTextField usernameField, JTextField passwordField, List<Register> registerList,
                                     String message) {
        Register tempRegister = new Register(usernameField.getText(), passwordField.getText());
        if (registerList.stream().noneMatch(r -> r.equals2(tempRegister))) {
            usernameField.requestFocus();
            MyOptionPane.showMessageDialog(view, message);
            return false;
        }
        return true;
    }
}
