package gui.gains;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action that closes a dialog.
 */
public class CloseListener implements ActionListener {
    /**
     * The dialog to close.
     */
    JDialog dialog;

    public CloseListener(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        dialog.dispose();
    }
}
