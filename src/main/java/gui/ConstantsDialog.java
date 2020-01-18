package gui;

import javax.swing.*;

public class ConstantsDialog extends JPanel {
    private JTextField spacing;
    private JTextField smoothWeight;
    private JTextField tolerance;
    private JTextField maxVelocity;
    private JTextField turningConstant;
    private JTextField maxAcceleration;

    public ConstantsDialog() {
        spacing = new JTextField();
        smoothWeight = new JTextField();
        tolerance = new JTextField();
        maxVelocity = new JTextField();
        turningConstant = new JTextField();
        maxAcceleration = new JTextField();

        this.add(new JLabel("Spacing:"));
        this.add(spacing);
        this.add(new JLabel("Smooth Weight:"));
        this.add(smoothWeight);
        this.add(new JLabel("Tolerance:"));
        this.add(tolerance);
        this.add(new JLabel("Max Velocity:"));
        this.add(maxVelocity);
        this.add(new JLabel("Turning Constant:"));
        this.add(turningConstant);
        this.add(new JLabel("Max Acceleration:"));
        this.add(maxAcceleration);
    }

    public static Constants showDialog() throws InterruptedException {
        ConstantsDialog dialog = new ConstantsDialog();
        JFrame frame = new JFrame();
        frame.setContentPane(dialog);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);

        while(frame.isVisible())
            Thread.sleep(100);

        return new Constants(Double.parseDouble(dialog.spacing.getText()),
                Double.parseDouble(dialog.smoothWeight.getText()), Double.parseDouble(dialog.tolerance.getText()),
                Double.parseDouble(dialog.maxVelocity.getText()), Double.parseDouble(dialog.turningConstant.getText()),
                Double.parseDouble(dialog.maxAcceleration.getText()));
    }
}
