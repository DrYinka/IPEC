import javax.swing.*;
import java.awt.*;

public class ElectricityEnergyCalculatorGUI {
    private JFrame frame;
    private JTextField powerTextField;
    private JTextField timeTextField;
    private JTextField energyTextField; // Added field for Electricity Energy
    private JButton calculateButton;
    private JButton refreshButton;
    private JLabel resultLabel;

    public void initialize() {
        frame = new JFrame("Electricity Energy Calculator");
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

        JLabel powerLabel = new JLabel("Power (Watts):");
        powerLabel.setBounds(10, 20, 150, 25);
        panel.add(powerLabel);

        powerTextField = new JTextField(20);
        powerTextField.setBounds(160, 20, 200, 25);
        panel.add(powerTextField);

        JLabel timeLabel = new JLabel("Time (Seconds):");
        timeLabel.setBounds(10, 50, 150, 25);
        panel.add(timeLabel);

        timeTextField = new JTextField(20);
        timeTextField.setBounds(160, 50, 200, 25);
        panel.add(timeTextField);

        JLabel energyLabel = new JLabel("E. Energy (Joules):");
        energyLabel.setBounds(10, 80, 150, 25);
        panel.add(energyLabel);

        energyTextField = new JTextField(20);
        energyTextField.setBounds(160, 80, 200, 25);
        panel.add(energyTextField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 110, 150, 35);
        calculateButton.addActionListener(e -> calculateElectricityEnergy());
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

        // Description of the equation for electricity energy
        String equationDescription = "<html><b>Equation for Electricity Energy:</b> <br>Energy = Power * Time</html>";
        JLabel equationLabel = new JLabel(equationDescription);
        equationLabel.setBounds(10, 190, 300, 40);
        equationLabel.setToolTipText(getEquationComponentsDescription());
        panel.add(equationLabel);

        // Brief hint about the properties related to electricity energy
        JTextArea hintTextArea = new JTextArea("Electricity energy is the amount of electrical work done over a period of time. "
                + "It is commonly used in calculating electricity consumption and costs in various applications.");
        hintTextArea.setBounds(400, 20, 350, 100);
        hintTextArea.setEditable(false);
        hintTextArea.setLineWrap(true);
        hintTextArea.setWrapStyleWord(true);
        panel.add(hintTextArea);

        // Image of the father of electricity
        ImageIcon discovererImageIcon = new ImageIcon("Michael Faraday.png"); // Replace with the actual image path
        JLabel discovererImageLabel = new JLabel(discovererImageIcon);
        discovererImageLabel.setBounds(450, 130, 200, 200);
        discovererImageLabel.setToolTipText("Michael Faraday (1791–1867)");
        panel.add(discovererImageLabel);
    }

    private void calculateElectricityEnergy() {
        try {
            double power = Double.parseDouble(powerTextField.getText());
            double time = Double.parseDouble(timeTextField.getText());
            double energy = Double.parseDouble(energyTextField.getText());

            int knownVariables = countKnownVariables(power, time, energy);

            if (knownVariables != 2) {
                throw new IllegalArgumentException("Please provide exactly 2 known values.");
            }

            if (power == 0) {
                power = calculatePower(energy, time);
                resultLabel.setText("<html><b>Power:</b> " + power + " Watts</html>");
            } else if (time == 0) {
                time = calculateTime(energy, power);
                resultLabel.setText("<html><b>Time:</b> " + time + " Seconds</html>");
            } else if (energy == 0) {
                energy = calculateElectricityEnergy(power, time);
                resultLabel.setText("<html><b>Electricity Energy:</b> " + energy + " Joules</html>");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    private void refreshInputs() {
        powerTextField.setText("");
        timeTextField.setText("");
        energyTextField.setText("");
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

    private double calculatePower(double energy, double time) {
        return energy / time;
    }

    private double calculateTime(double energy, double power) {
        return energy / power;
    }

    private double calculateElectricityEnergy(double power, double time) {
        return power * time;
    }

    private String getEquationComponentsDescription() {
        return "<html><b>Components of the Equation:</b><br>" +
                "- <b>Energy:</b> The amount of electrical work done, measured in joules (J).<br>" +
                "- <b>Power:</b> The rate at which electrical energy is consumed or produced, measured in watts (W).<br>" +
                "- <b>Time:</b> The duration over which electrical energy is consumed or produced, measured in seconds (s).</html>";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElectricityEnergyCalculatorGUI electricityEnergyCalculatorGUI = new ElectricityEnergyCalculatorGUI();
            electricityEnergyCalculatorGUI.initialize();
        });
    }
}
