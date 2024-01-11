import javax.swing.*;
import java.awt.*;

public class DensityCalculatorGUI {
    private JFrame frame;
    private JTextField massTextField;
    private JTextField volumeTextField;
    private JTextField densityTextField; // Added field for Density
    private JButton calculateButton;
    private JButton refreshButton;
    private JLabel resultLabel;

    public void initialize() {
        frame = new JFrame("Density Calculator");
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

        JLabel massLabel = new JLabel("Mass (kg):");
        massLabel.setBounds(10, 20, 150, 25);
        panel.add(massLabel);

        massTextField = new JTextField(20);
        massTextField.setBounds(160, 20, 200, 25);
        panel.add(massTextField);

        JLabel volumeLabel = new JLabel("Volume (m^3):");
        volumeLabel.setBounds(10, 50, 150, 25);
        panel.add(volumeLabel);

        volumeTextField = new JTextField(20);
        volumeTextField.setBounds(160, 50, 200, 25);
        panel.add(volumeTextField);

        JLabel densityLabel = new JLabel("Density (kg/m^3):");
        densityLabel.setBounds(10, 80, 150, 25);
        panel.add(densityLabel);

        densityTextField = new JTextField(20);
        densityTextField.setBounds(160, 80, 200, 25);
        panel.add(densityTextField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 110, 150, 35);
        calculateButton.addActionListener(e -> calculateDensity());
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

        // Description of the density equation
        String equationDescription = "<html><b>Equation for Density:</b> <br>Density = Mass / Volume</html>";
        JLabel equationLabel = new JLabel(equationDescription);
        equationLabel.setBounds(10, 190, 400, 40);
        equationLabel.setToolTipText(getEquationComponentsDescription());
        panel.add(equationLabel);

        // Brief hint about the properties related to density
        JTextArea hintTextArea = new JTextArea("Density is a measure of how much mass is contained in a given volume. "
                + "It is an important property in physics, engineering, and various scientific disciplines."
                + "Archimedes' principle and the understanding of density are fundamental to fluid mechanics.");
        hintTextArea.setBounds(400, 20, 350, 120);
        hintTextArea.setEditable(false);
        hintTextArea.setLineWrap(true);
        hintTextArea.setWrapStyleWord(true);
        panel.add(hintTextArea);

        // Image of the person associated with the discovery
        ImageIcon discovererImageIcon = new ImageIcon("Archimedes.png"); // Replace with the actual image path
        JLabel discovererImageLabel = new JLabel(discovererImageIcon);
        discovererImageLabel.setBounds(450, 140, 200, 220);
        discovererImageLabel.setToolTipText("Archimedes of Syracuse (287 BC - 212 BC)");
        panel.add(discovererImageLabel);
    }

    private void calculateDensity() {
        try {
            double mass = Double.parseDouble(massTextField.getText());
            double volume = Double.parseDouble(volumeTextField.getText());
            double density = Double.parseDouble(densityTextField.getText());

            int knownVariables = countKnownVariables(mass, volume, density);

            if (knownVariables != 2) {
                throw new IllegalArgumentException("Please provide exactly 2 known values.");
            }

            if (mass == 0) {
                mass = calculateMass(density, volume);
                resultLabel.setText("<html><b>Mass:</b> " + String.format("%.2f", mass) + " kg</html>");
            } else if (volume == 0) {
                volume = calculateVolume(mass, density);
                resultLabel.setText("<html><b>Volume:</b> " + String.format("%.3f", volume) + " m^3</html>");
            } else if (density == 0) {
                density = calculateDensity(mass, volume);
                resultLabel.setText("<html><b>Density:</b> " + String.format("%.2f", density) + " kg/m^3</html>");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    private void refreshInputs() {
        massTextField.setText("");
        volumeTextField.setText("");
        densityTextField.setText("");
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

    private double calculateMass(double density, double volume) {
        return density * volume;
    }

    private double calculateVolume(double mass, double density) {
        return mass / density;
    }

    private double calculateDensity(double mass, double volume) {
        return mass / volume;
    }

    private String getEquationComponentsDescription() {
        return "<html><b>Components of the Equation:</b><br>" +
                "- <b>Density:</b> The density of a substance, measured in kilograms per cubic meter (kg/m^3).<br>" +
                "- <b>Mass:</b> The mass of the substance, measured in kilograms (kg).<br>" +
                "- <b>Volume:</b> The volume occupied by the substance, measured in cubic meters (m^3).</html>";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DensityCalculatorGUI densityCalculatorGUI = new DensityCalculatorGUI();
            densityCalculatorGUI.initialize();
        });
    }
}
