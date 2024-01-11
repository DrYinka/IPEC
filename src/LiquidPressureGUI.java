import javax.swing.*;
import java.awt.*;

public class LiquidPressureGUI {
    private JFrame frame;
    private JTextField densityTextField;
    private JTextField heightTextField;
    private JTextField pressureTextField;
    private JButton calculateButton;
    private JButton refreshButton;
    private JLabel resultLabel;

    // Assuming constant gravity
    private static final double GRAVITY = 9.8;

    public void initialize() {
        frame = new JFrame("Liquid Pressure Calculator");
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

        JLabel densityLabel = new JLabel("Density (kg/m^3):");
        densityLabel.setBounds(10, 20, 150, 25);
        panel.add(densityLabel);

        densityTextField = new JTextField(20);
        densityTextField.setBounds(160, 20, 200, 25);
        panel.add(densityTextField);

        JLabel heightLabel = new JLabel("Height (m):");
        heightLabel.setBounds(10, 50, 150, 25);
        panel.add(heightLabel);

        heightTextField = new JTextField(20);
        heightTextField.setBounds(160, 50, 200, 25);
        panel.add(heightTextField);

        JLabel pressureLabel = new JLabel("Liquid Pressure (Pa):");
        pressureLabel.setBounds(10, 80, 150, 25);
        panel.add(pressureLabel);

        pressureTextField = new JTextField(20);
        pressureTextField.setBounds(160, 80, 200, 25);
        panel.add(pressureTextField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 110, 150, 35);
        calculateButton.addActionListener(e -> calculatePressure());
        panel.add(calculateButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(170, 110, 350, 35);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 16));
        resultLabel.setForeground(Color.BLUE);
        panel.add(resultLabel);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(10, 150, 150, 35);
        refreshButton.addActionListener(e -> refreshInputs());
        panel.add(refreshButton);

        // Description of the equation for liquid pressure
        String equationDescription = "<html><b>Equation for Liquid Pressure:</b> <br>Pressure = Density * Gravity * Height</html>";
        JLabel equationLabel = new JLabel(equationDescription);
        equationLabel.setBounds(10, 190, 400, 40);
        equationLabel.setToolTipText(getEquationComponentsDescription());
        panel.add(equationLabel);

        // Brief hint about the properties related to liquid pressure
        JTextArea hintTextArea = new JTextArea("Liquid pressure is influenced by the density of the liquid, "
                + "the acceleration due to gravity, and the height of the liquid column. "
                + "This equation is fundamental in understanding hydrostatics. One key"
                + " figure in fluid mechanics is Blaise Pascal, who made significant"
                + " contributions to the understanding of fluid behavior.");
        hintTextArea.setBounds(400, 20, 350, 150);
        hintTextArea.setEditable(false);
        hintTextArea.setLineWrap(true);
        hintTextArea.setWrapStyleWord(true);
        panel.add(hintTextArea);

        // Image of the person associated with the discovery
        ImageIcon discovererImageIcon = new ImageIcon("Pascal.png"); // Replace with the actual image path
        JLabel discovererImageLabel = new JLabel(discovererImageIcon);
        discovererImageLabel.setBounds(450, 150, 250, 200);
        discovererImageLabel.setToolTipText("Blaise Pascal (1623 - 1662)");
        panel.add(discovererImageLabel);
    }

    private void calculatePressure() {
        try {
            double density = Double.parseDouble(densityTextField.getText());
            double height = Double.parseDouble(heightTextField.getText());
            double pressure = Double.parseDouble(pressureTextField.getText());

            int knownVariables = countKnownVariables(density, height, pressure);

            if (knownVariables != 2) {
                throw new IllegalArgumentException("Please provide exactly 2 known values.");
            }

            if (density == 0) {
                density = calculateDensity(pressure, height);
                resultLabel.setText("<html><b>Density:</b> " + String.format("%.2f", density) + " kg/m^3</html>");
            } else if (height == 0) {
                height = calculateHeight(pressure, density);
                resultLabel.setText("<html><b>Height:</b> " + String.format("%.2f", height) + " m</html>");
            } else if (pressure == 0) {
                pressure = calculatePressure(density, height);
                resultLabel.setText("<html><b>Liquid Pressure:</b> " + String.format("%.3f", pressure) + " Pa</html>");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    private void refreshInputs() {
        densityTextField.setText("");
        heightTextField.setText("");
        pressureTextField.setText("");
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

    private double calculateDensity(double pressure, double height) {
        return pressure / (GRAVITY * height);
    }

    private double calculateHeight(double pressure, double density) {
        return pressure / (GRAVITY * density);
    }

    private double calculatePressure(double density, double height) {
        return density * GRAVITY * height;
    }

    private String getEquationComponentsDescription() {
        return "<html><b>Components of the Equation:</b><br>" +
                "- <b>Liquid Pressure:</b> The pressure in a liquid, measured in Pascal (Pa).<br>" +
                "- <b>Density:</b> The density of the liquid, measured in kilograms per cubic meter (kg/m^3).<br>" +
                "- <b>Gravity:</b> The acceleration due to gravity, measured in meters per second squared (m/s^2).<br>" +
                "- <b>Height:</b> The height of the liquid column, measured in meters (m).</html>";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LiquidPressureGUI liquidPressureGUI = new LiquidPressureGUI();
            liquidPressureGUI.initialize();
        });
    }
}
