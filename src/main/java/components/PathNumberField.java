package components;

import gui.Globals;

import javax.swing.*;
import java.text.NumberFormat;

/**
 * A text field that only accepts numbers.
 *
 * @author Eran Goldstein
 */
public class PathNumberField extends JFormattedTextField {
    public PathNumberField(double value) {
        super(NumberFormat.getInstance());
        setColumns(Globals.TEXTFIELD_COLUMNS);
        setText(String.valueOf(value));
    }

    public PathNumberField() {
        this(0);
    }

    public double getNumber() {
        return Double.parseDouble(getText());
    }

    public void setNumber(double number) {
        setText(String.valueOf(number));
    }
}
