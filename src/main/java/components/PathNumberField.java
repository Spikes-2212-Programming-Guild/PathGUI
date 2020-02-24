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
    /**
     * The {@code double} value stored in this number field.
     */
    private double val;

    public PathNumberField(double number) {
        super(NumberFormat.getInstance());
        setColumns(Globals.TEXTFIELD_COLUMNS);
        setText(String.valueOf(number));
        val = number;
    }

    public PathNumberField() {
        this(0);
    }

    public double getNumber() {
        try {
            val = Double.parseDouble(getText());
        } catch(NumberFormatException ignored) {
            // if the value cannot be parsed, return the previous value
        }

        return val;
    }

    public void setNumber(double number) {
        setText(String.valueOf(number));
        val = number;
    }
}
