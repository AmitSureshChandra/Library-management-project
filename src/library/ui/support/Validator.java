package library.ui.support;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

public class Validator {
    public static void fieldValidator(JFXTextField tf) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Required");

        tf.getValidators().add(validator);

        tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                tf.validate();
        });

    }
}
