import javax.swing.*;
import java.awt.*;

public class WaveCalculatorGUI {
    private JFrame frame;
    private JTextField frequencyTextField;
    private JTextField wavelengthTextField;
    private JTextField waveSpeedTextField; // New text field for WaveSpeed
    private JButton calculateButton;
    private JButton refreshButton; // New Refresh button
    private JLabel resultLabel;

    public void initialize() {
        frame = new JFrame("Wave Calculator");
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

        JLabel frequencyLabel = new JLabel("Frequency (Hz):");
        frequencyLabel.setBounds(10, 20, 150, 25);
        panel.add(frequencyLabel);

        frequencyTextField = new JTextField(20);
        frequencyTextField.setBounds(160, 20, 200, 25);
        panel.add(frequencyTextField);

        JLabel wavelengthLabel = new JLabel("Wavelength (m):");
        wavelengthLabel.setBounds(10, 50, 150, 25);
        panel.add(wavelengthLabel);

        wavelengthTextField = new JTextField(20);
        wavelengthTextField.setBounds(160, 50, 200, 25);
        panel.add(wavelengthTextField);

        JLabel waveSpeedLabel = new JLabel("Wave Speed (m/s):");
        waveSpeedLabel.setBounds(10, 80, 150, 25);
        panel.add(waveSpeedLabel);

        waveSpeedTextField = new JTextField(20);
        waveSpeedTextField.setBounds(160, 80, 200, 25);
        panel.add(waveSpeedTextField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 110, 150, 35);
        calculateButton.addActionListener(e -> calculateWaveSpeed());
        panel.add(calculateButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(170, 110, 350, 35);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 16)); // Increase font size
        resultLabel.setForeground(Color.BLUE);
        panel.add(resultLabel);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(10, 150, 150, 35);
        refreshButton.addActionListener(e -> refreshInputs());
        panel.add(refreshButton);

        // Description of the equation for wave speed
        String equationDescription = "<html><b>Equation for Wave Speed:</b> <br>Speed = Frequency * Wavelength</html>";
        JLabel equationLabel = new JLabel(equationDescription);
        equationLabel.setBounds(10, 190, 300, 40);
        equationLabel.setToolTipText(getEquationComponentsDescription());
        panel.add(equationLabel);

        // Brief hint about the properties related to wave speed
        JTextArea hintTextArea = new JTextArea("Wave speed is the speed at which a wave travels through a medium. "
                + "It is influenced by the frequency of the wave and its wavelength. "
                + "Understanding wave speed is crucial in fields such as physics and telecommunications.");
        hintTextArea.setBounds(400, 20, 350, 150);
        hintTextArea.setEditable(false);
        hintTextArea.setLineWrap(true);
        hintTextArea.setWrapStyleWord(true);
        panel.add(hintTextArea);

        // Image of the person associated with the discovery
        ImageIcon discovererImageIcon = new ImageIcon("JC Maxwel.png"); // Replace with the actual image path
        JLabel discovererImageLabel = new JLabel(discovererImageIcon);
        discovererImageLabel.setBounds(450, 100, 200, 300);
        discovererImageLabel.setToolTipText("James Clerk Maxwell (1831-1879)");
        panel.add(discovererImageLabel);
    }

    private void calculateWaveSpeed() {
        try {
            double frequency = Double.parseDouble(frequencyTextField.getText());
            double wavelength = Double.parseDouble(wavelengthTextField.getText());
            double waveSpeed = Double.parseDouble(waveSpeedTextField.getText());

            // Check for at least two known variables
            int knownVariables = countKnownVariables(frequency, wavelength, waveSpeed);

            if (knownVariables < 2) {
                throw new IllegalArgumentException("Please provide at least two known values.");
            }

            // Calculate the missing variable
            if (waveSpeed == 0) {
                waveSpeed = frequency * wavelength;
                resultLabel.setText("<html><b>Wave Speed:</b> " + String.format("%.2f", waveSpeed) + " m/s</html>");
            } else if (frequency == 0) {
                frequency = waveSpeed / wavelength;
                resultLabel.setText("<html><b>Frequency:</b> " + String.format("%.2f", frequency) + " Hz</html>");
            } else if (wavelength == 0) {
                wavelength = waveSpeed / frequency;
                resultLabel.setText("<html><b>Wavelength:</b> " + String.format("%.2f", wavelength) + " m</html>");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    private void refreshInputs() {
        frequencyTextField.setText("");
        wavelengthTextField.setText("");
        waveSpeedTextField.setText("");
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

    private String getEquationComponentsDescription() {
        return "<html><b>Components of the Equation:</b><br>" +
                "- <b>Speed:</b> The speed at which a wave travels through a medium, measured in meters per second (m/s).<br>" +
                "- <b>Frequency:</b> The number of oscillations or cycles of a wave per unit of time, measured in hertz (Hz).<br>" +
                "- <b>Wavelength:</b> The distance between two consecutive peaks (or troughs) of a wave, measured in meters (m).</html>";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WaveCalculatorGUI waveCalculatorGUI = new WaveCalculatorGUI();
            waveCalculatorGUI.initialize();
        });
    }
}
