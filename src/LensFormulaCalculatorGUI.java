import javax.swing.*;
import java.awt.*;

public class LensFormulaCalculatorGUI {
    private JFrame frame;
    private JTextField focalLengthTextField;
    private JTextField objectDistanceTextField;
    private JTextField imageDistanceTextField;
    private JButton calculateButton;
    private JButton refreshButton;
    private JLabel resultLabel;

    public void initialize() {
        frame = new JFrame("Lens Formula Calculator");
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

        JLabel focalLengthLabel = new JLabel("Focal Length (cm):");
        focalLengthLabel.setBounds(10, 20, 150, 25);
        panel.add(focalLengthLabel);

        focalLengthTextField = new JTextField(20);
        focalLengthTextField.setBounds(160, 20, 200, 25);
        panel.add(focalLengthTextField);

        JLabel objectDistanceLabel = new JLabel("Object Distance (cm):");
        objectDistanceLabel.setBounds(10, 50, 150, 25);
        panel.add(objectDistanceLabel);

        objectDistanceTextField = new JTextField(20);
        objectDistanceTextField.setBounds(160, 50, 200, 25);
        panel.add(objectDistanceTextField);

        JLabel imageDistanceLabel = new JLabel("Image Distance (cm):");
        imageDistanceLabel.setBounds(10, 80, 150, 25);
        panel.add(imageDistanceLabel);

        imageDistanceTextField = new JTextField(20);
        imageDistanceTextField.setBounds(160, 80, 200, 25);
        panel.add(imageDistanceTextField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 110, 150, 35);
        calculateButton.addActionListener(e -> calculateLensFormula());
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

        // Description of the lens formula
        String formulaDescription = "<html><b>Lens Formula:</b> <br>1/f = 1/v - 1/u</html>";
        JLabel formulaLabel = new JLabel(formulaDescription);
        formulaLabel.setBounds(10, 190, 300, 40);
        formulaLabel.setToolTipText(getFormulaComponentsDescription());
        panel.add(formulaLabel);

        // Brief hint about the lens formula
        JTextArea hintTextArea = new JTextArea("The lens formula relates the focal length (f) of a lens, the object distance (u), "
                + "and the image distance (v). It is a fundamental formula in optics and is used in lens calculations.");
        hintTextArea.setBounds(400, 20, 350, 120);
        hintTextArea.setEditable(false);
        hintTextArea.setLineWrap(true);
        hintTextArea.setWrapStyleWord(true);
        panel.add(hintTextArea);

        // Image of the lens
        ImageIcon lensImageIcon = new ImageIcon("Lens.png"); // Replace with the actual image path
        JLabel lensImageLabel = new JLabel(lensImageIcon);
        lensImageLabel.setBounds(380, 150, 400, 200);
        panel.add(lensImageLabel);
    }

    private void calculateLensFormula() {
        try {
            double focalLength = Double.parseDouble(focalLengthTextField.getText());
            double objectDistance = Double.parseDouble(objectDistanceTextField.getText());
            double imageDistance = Double.parseDouble(imageDistanceTextField.getText());

            int knownVariables = countKnownVariables(focalLength, objectDistance, imageDistance);

            if (knownVariables != 2) {
                throw new IllegalArgumentException("Please provide exactly 2 known values.");
            }

            if (focalLength == 0) {
                focalLength = calculateFocalLength(objectDistance, imageDistance);
                resultLabel.setText("<html><b>Focal Length:</b> " + String.format("%.2f", focalLength) + " cm</html>");
            } else if (objectDistance == 0) {
                objectDistance = calculateObjectDistance(focalLength, imageDistance);
                resultLabel.setText("<html><b>Object Distance:</b> " + String.format("%.2f", objectDistance) + " cm</html>");
            } else if (imageDistance == 0) {
                imageDistance = calculateImageDistance(focalLength, objectDistance);
                resultLabel.setText("<html><b>Image Distance:</b> " + String.format("%.2f", imageDistance) + " cm</html>");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    private void refreshInputs() {
        focalLengthTextField.setText("");
        objectDistanceTextField.setText("");
        imageDistanceTextField.setText("");
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

    private double calculateFocalLength(double objectDistance, double imageDistance) {
        return 1 / ((1 / objectDistance) + (1 / imageDistance));
    }

    private double calculateObjectDistance(double focalLength, double imageDistance) {
        return 1 / ((1 / focalLength) - (1 / imageDistance));
    }

    private double calculateImageDistance(double focalLength, double objectDistance) {
        return 1 / ((1 / focalLength) - (1 / objectDistance));
    }

    private String getFormulaComponentsDescription() {
        return "<html><b>Components of the Formula:</b><br>" +
                "- <b>Focal Length (f):</b> The distance from the lens to the point where parallel rays converge or diverge, measured in centimeters (cm).<br>" +
                "- <b>Object Distance (u):</b> The distance from the lens to the object, measured in centimeters (cm).<br>" +
                "- <b>Image Distance (v):</b> The distance from the lens to the image, measured in centimeters (cm).</html>";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LensFormulaCalculatorGUI lensFormulaCalculatorGUI = new LensFormulaCalculatorGUI();
            lensFormulaCalculatorGUI.initialize();
        });
    }
}
