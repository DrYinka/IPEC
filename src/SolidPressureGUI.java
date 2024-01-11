import javax.swing.*;
import java.awt.*;

public class SolidPressureGUI {
    private JFrame frame;
    private JTextField forceTextField;
    private JTextField areaTextField;
    private JTextField pressureTextField; // Added field for Solid Pressure
    private JButton calculateButton;
    private JButton refreshButton;
    private JLabel resultLabel;

    public void initialize() {
        frame = new JFrame("Solid Pressure Calculator");
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

        JLabel pressureLabel = new JLabel("Solid Pressure (Pa):");
        pressureLabel.setBounds(10, 80, 150, 25);
        panel.add(pressureLabel);

        pressureTextField = new JTextField(20);
        pressureTextField.setBounds(160, 80, 200, 25);
        panel.add(pressureTextField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 110, 150, 35);
        calculateButton.addActionListener(e -> calculateSolidPressure());
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

        // Description of the equation for solid pressure
        String equationDescription = "<html><b>Equation for Solid Pressure:</b> <br>Pressure = Force / Area</html>";
        JLabel equationLabel = new JLabel(equationDescription);
        equationLabel.setBounds(10, 190, 350, 40);
        equationLabel.setToolTipText(getEquationComponentsDescription());
        panel.add(equationLabel);

        // Brief hint about the discovery of solid pressure
        JTextArea hintTextArea = new JTextArea("Solid pressure, also known as stress, was first formulated by "
                + "Robert Hooke, a 17th-century English scientist. Hooke's law "
                + "relates the force applied to a solid to the resulting deformation. "
                + "It is named after Robert Hooke, who discovered it in 1660.");
        hintTextArea.setBounds(400, 20, 350, 150);
        hintTextArea.setEditable(false);
        hintTextArea.setLineWrap(true);
        hintTextArea.setWrapStyleWord(true);
        panel.add(hintTextArea);

        // Image of Robert Hooke
        ImageIcon hookeImageIcon = new ImageIcon("Robert Hook.png"); // Replace with the actual image path
        JLabel hookeImageLabel = new JLabel(hookeImageIcon);
        hookeImageLabel.setBounds(450, 150, 250, 200);
        hookeImageLabel.setToolTipText("Robert Hooke (1635 - 1703)");
        panel.add(hookeImageLabel);
    }

    private void calculateSolidPressure() {
        try {
            double force = Double.parseDouble(forceTextField.getText());
            double area = Double.parseDouble(areaTextField.getText());
            double pressure = Double.parseDouble(pressureTextField.getText());

            int knownVariables = countKnownVariables(force, area, pressure);

            if (knownVariables != 2) {
                throw new IllegalArgumentException("Please provide exactly 2 known values.");
            }

            if (force == 0) {
                force = calculateForce(pressure, area);
                resultLabel.setText("<html><b>Force:</b> " + String.format("%.2f", force) + " N</html>");
            } else if (area == 0) {
                area = calculateArea(force, pressure);
                resultLabel.setText("<html><b>Area:</b> " + String.format("%.2f", area) + " m^2</html>");
            } else if (pressure == 0) {
                pressure = calculatePressure(force, area);
                resultLabel.setText("<html><b>Solid Pressure:</b> " + String.format("%.3f", pressure) + " Pa</html>");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    private void refreshInputs() {
        forceTextField.setText("");
        areaTextField.setText("");
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

    private double calculateForce(double pressure, double area) {
        return pressure * area;
    }

    private double calculateArea(double force, double pressure) {
        return force / pressure;
    }

    private double calculatePressure(double force, double area) {
        return force / area;
    }

    private String getEquationComponentsDescription() {
        return "<html><b>Components of the Equation:</b><br>" +
                "- <b>Solid Pressure:</b> The pressure applied to a solid material, measured in Pascal (Pa).<br>" +
                "- <b>Force:</b> The external load or push applied to the material, measured in Newtons (N).<br>" +
                "- <b>Area:</b> The surface over which the force is distributed, measured in square meters (m^2).</html>";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SolidPressureGUI solidPressureGUI = new SolidPressureGUI();
            solidPressureGUI.initialize();
        });
    }
}
