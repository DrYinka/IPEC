import javax.swing.*;
import java.awt.*;

public class LinearMotionCalculatorGUI {
    private JFrame frame;
    private JTextField initialVelocityTextField;
    private JTextField finalVelocityTextField;
    private JTextField accelerationTextField;
    private JTextField timeTextField;
    private JButton calculateButton;
    private JButton refreshButton;
    private JLabel resultLabel;

    public void initialize() {
        frame = new JFrame("Acceleration in Uniform Linear Motion Calculator");
        frame.setResizable(false);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel initialVelocityLabel = new JLabel("Initial Velocity (m/s):");
        initialVelocityLabel.setBounds(10, 20, 150, 25);
        panel.add(initialVelocityLabel);

        initialVelocityTextField = new JTextField(20);
        initialVelocityTextField.setBounds(160, 20, 200, 25);
        panel.add(initialVelocityTextField);

        JLabel finalVelocityLabel = new JLabel("Final Velocity (m/s):");
        finalVelocityLabel.setBounds(10, 50, 150, 25);
        panel.add(finalVelocityLabel);

        finalVelocityTextField = new JTextField(20);
        finalVelocityTextField.setBounds(160, 50, 200, 25);
        panel.add(finalVelocityTextField);

        JLabel accelerationLabel = new JLabel("Acceleration (m/s^2):");
        accelerationLabel.setBounds(10, 80, 150, 25);
        panel.add(accelerationLabel);

        accelerationTextField = new JTextField(20);
        accelerationTextField.setBounds(160, 80, 200, 25);
        panel.add(accelerationTextField);

        JLabel timeLabel = new JLabel("Time (s):");
        timeLabel.setBounds(10, 110, 150, 25);
        panel.add(timeLabel);

        timeTextField = new JTextField(20);
        timeTextField.setBounds(160, 110, 200, 25);
        panel.add(timeTextField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 140, 150, 35);
        calculateButton.addActionListener(e -> calculateAcceleration());
        panel.add(calculateButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(170, 140, 350, 35);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 16));
        resultLabel.setForeground(Color.BLUE);
        panel.add(resultLabel);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(10, 180, 150, 35);
        refreshButton.addActionListener(e -> refreshInputs());
        panel.add(refreshButton);

        // Description of the equation for linear motion
        String equationDescription = "<html><b>Equation for Acceleration in Uniform Linear Motion:</b> <br>Acceleration = (Final Velocity - Initial Velocity) / Time</html>";
        JLabel equationLabel = new JLabel(equationDescription);
        equationLabel.setBounds(10, 220, 400, 40);
        equationLabel.setToolTipText(getEquationComponentsDescription());
        panel.add(equationLabel);

        // Brief hint about the properties related to linear motion
        JTextArea hintTextArea = new JTextArea("Acceleration in uniform linear motion represents the rate at which an object's velocity changes over time. "
                + "It is a fundamental concept in physics and is crucial for understanding motion and forces.");
        hintTextArea.setBounds(400, 20, 350, 150);
        hintTextArea.setEditable(false);
        hintTextArea.setLineWrap(true);
        hintTextArea.setWrapStyleWord(true);
        panel.add(hintTextArea);

        // Image of the person associated with the discovery
        ImageIcon discovererImageIcon = new ImageIcon("Newton.png"); // Replace with the actual image path
        JLabel discovererImageLabel = new JLabel(discovererImageIcon);
        discovererImageLabel.setBounds(450, 170, 200, 200);
        discovererImageLabel.setToolTipText("Isaac Newton (1642–1727)");
        panel.add(discovererImageLabel);
    }

    private void calculateAcceleration() {
        try {
            double initialVelocity = Double.parseDouble(initialVelocityTextField.getText());
            double finalVelocity = Double.parseDouble(finalVelocityTextField.getText());
            double acceleration = Double.parseDouble(accelerationTextField.getText());
            double time = Double.parseDouble(timeTextField.getText());

            int knownVariables = countKnownVariables(initialVelocity, finalVelocity, acceleration, time);

            if (knownVariables != 3) {
                throw new IllegalArgumentException("Please provide exactly 3 known values.");
            }

            if (acceleration == 0) {
                acceleration = calculateAcceleration(initialVelocity, finalVelocity, time);
                resultLabel.setText("<html><b>Acceleration:</b> " + String.format("%.2f", acceleration) + " m/s^2</html>");
            } else if (initialVelocity == 0) {
                initialVelocity = calculateInitialVelocity(finalVelocity, acceleration, time);
                resultLabel.setText("<html><b>Initial Velocity:</b> " + String.format("%.2f", initialVelocity) + " m/s</html>");
            } else if (finalVelocity == 0) {
                finalVelocity = calculateFinalVelocity(initialVelocity, acceleration, time);
                resultLabel.setText("<html><b>Final Velocity:</b> " + String.format("%.2f", finalVelocity) + " m/s</html>");
            } else if (time == 0) {
                time = calculateTime(initialVelocity, finalVelocity, acceleration);
                resultLabel.setText("<html><b>Time:</b> " + String.format("%.2f", time) + " s</html>");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    private void refreshInputs() {
        initialVelocityTextField.setText("");
        finalVelocityTextField.setText("");
        accelerationTextField.setText("");
        timeTextField.setText("");
        resultLabel.setText("");
    }

    private int countKnownVariables(double... values) {
        int count = 0;
        for (double value : values) {
            if (value != 0) {
                count++;
            }
        }
        return count;
    }

    private double calculateAcceleration(double initialVelocity, double finalVelocity, double time) {
        return (finalVelocity - initialVelocity) / time;
    }

    private double calculateInitialVelocity(double finalVelocity, double acceleration, double time) {
        return finalVelocity - (acceleration * time);
    }

    private double calculateFinalVelocity(double initialVelocity, double acceleration, double time) {
        return initialVelocity + (acceleration * time);
    }

    private double calculateTime(double initialVelocity, double finalVelocity, double acceleration) {
        return (finalVelocity - initialVelocity) / acceleration;
    }

    private String getEquationComponentsDescription() {
        return "<html><b>Components of the Equation:</b><br>" +
                "- <b>Acceleration:</b> The rate at which an object's velocity changes, measured in meters per second squared (m/s^2).<br>" +
                "- <b>Initial Velocity:</b> The velocity of the object at the beginning of the motion, measured in meters per second (m/s).<br>" +
                "- <b>Final Velocity:</b> The velocity of the object at the end of the motion, measured in meters per second (m/s).<br>" +
                "- <b>Time:</b> The duration of the motion, measured in seconds (s).</html>";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LinearMotionCalculatorGUI linearMotionCalculatorGUI = new LinearMotionCalculatorGUI();
            linearMotionCalculatorGUI.initialize();
        });
    }
}
