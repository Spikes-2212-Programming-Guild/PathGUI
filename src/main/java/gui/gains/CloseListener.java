package gui.gains;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseListener implements ActionListener {
    JDialog dialog;

    public CloseListener(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        dialog.dispose();
    }
}
