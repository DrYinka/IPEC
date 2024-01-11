import javax.swing.*;

public class SolidPressureGUIold {
    private JFrame frame;
    private JTextField forceTextField;
    private JTextField areaTextField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public void initialize() {
        frame = new JFrame("Solid Pressure Calculator");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel forceLabel = new JLabel("Force (N):");
        forceLabel.setBounds(10, 20, 150, 25);
        panel.add(forceLabel);

        forceTextField = new JTextField(20);
        forceTextField.setBounds(160, 20, 200, 25);
        panel.add(forceTextField);

        JLabel areaLabel = new JLabel("Area (m^2):");
        areaLabel.setBounds(10, 50, 150, 25);
        panel.add(areaLabel);

        areaTextField = new JTextField(20);
        areaTextField.setBounds(160, 50, 200, 25);
        panel.add(areaTextField);

        calculateButton = new JButton("Calculate Pressure");
        calculateButton.setBounds(10, 80, 150, 25);
        calculateButton.addActionListener(e -> calculatePressure());
        panel.add(calculateButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(10, 110, 350, 25);
        panel.add(resultLabel);
    }

    private void calculatePressure() {
        try {
            double force = Double.parseDouble(forceTextField.getText());
            double area = Double.parseDouble(areaTextField.getText());

            if (force < 0 || area <= 0) {
                throw new IllegalArgumentException("Force must be a non-negative value, and area must be a positive value.");
            }

            double pressure = force / area;

            // Format the pressure to four decimal places
            String formattedPressure = String.format("%.4f", pressure);

            resultLabel.setText("Pressure: " + formattedPressure + " Pascal");
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }
}
