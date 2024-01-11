import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class MainCalculatorWin {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            createMainForm();
        });
    }

    private static void createMainForm() {
        JFrame frame = new JFrame("Interactive Physics Equation Calculator (IPEC 1.0)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);

        // Set a background image on the main form
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("IPEC.png"));
            JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            // Center the background image and make it occupy 2/3 of the area
            //int width = (int) (frame.getWidth() * 2.0 / 3.0);
            //int height = (int) (frame.getHeight() * 2.0 / 3.0);
            //backgroundLabel.setBounds((frame.getWidth() - width) / 2, (frame.getHeight() - height) / 2, width, height);
            frame.getContentPane().setLayout(null);
            frame.setContentPane(backgroundLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JMenuBar menuBar = new JMenuBar();

        // Mechanics Menu
        JMenu mechanicsMenu = new JMenu("Mechanics");
        JMenuItem densityItem = new JMenuItem("Density");
        JMenuItem motionItem = new JMenuItem("Motion");
        JMenuItem pressureMenu = new JMenu("Pressure");
        JMenuItem liquidPressureItem = new JMenuItem("Liquid Pressure");
        JMenuItem solidPressureItem = new JMenuItem("Solid Pressure");

        pressureMenu.add(liquidPressureItem);
        pressureMenu.add(solidPressureItem);

        mechanicsMenu.add(densityItem);
        mechanicsMenu.add(motionItem);
        mechanicsMenu.add(pressureMenu);

        // Electromagnetism Menu
        JMenu electromagnetismMenu = new JMenu("Electromagnetism");
        JMenuItem electricityItem = new JMenuItem("Electricity");
        electromagnetismMenu.add(electricityItem);

        // Optic Menu
        JMenu opticMenu = new JMenu("Optic");
        JMenuItem lightItem = new JMenuItem("Light");
        opticMenu.add(lightItem);

        // Wave Menu
        JMenu waveMenu = new JMenu("Wave");
        JMenuItem waveItem = new JMenuItem("Wave");
        waveMenu.add(waveItem);

//        // About Menu
//        JMenu aboutMenu = new JMenu("About");
//        JMenuItem aboutItem = new JMenuItem("About IPEC 1.0");
//        aboutMenu.add(aboutItem);

        // Action Listeners for Menu Items

        // Add Menus to Menu Bar
        menuBar.add(mechanicsMenu);
        menuBar.add(electromagnetismMenu);
        menuBar.add(opticMenu);
        menuBar.add(waveMenu);

        frame.setJMenuBar(menuBar);

        densityItem.addActionListener(e -> openDensityCalculator());
        motionItem.addActionListener(e -> openLinearMotionCalculator());
        lightItem.addActionListener(e -> openLensFormulaCalculator());
        electricityItem.addActionListener(e -> openElectricityEnergyCalculator());
        liquidPressureItem.addActionListener(e -> openLiquidPressureCalculator());
        solidPressureItem.addActionListener(e -> openSolidPressureCalculator());
        waveItem.addActionListener(e -> openWaveCalculator());


        frame.setVisible(true);
    }


    private static void openDensityCalculator() {
        DensityCalculatorGUI densityGUI = new DensityCalculatorGUI();
        densityGUI.initialize();
    }

    private static void openLinearMotionCalculator() {
        LinearMotionCalculatorGUI LinearMotionGUI = new LinearMotionCalculatorGUI();
        LinearMotionGUI.initialize();
    }

    private static void openLensFormulaCalculator() {
        LensFormulaCalculatorGUI LensFormulaGUI = new LensFormulaCalculatorGUI();
        LensFormulaGUI.initialize();
    }

    private static void openElectricityEnergyCalculator() {
        ElectricityEnergyCalculatorGUI ElectricityEnergyGUI = new ElectricityEnergyCalculatorGUI();
        ElectricityEnergyGUI.initialize();
    }

    private static void openLiquidPressureCalculator() {
        LiquidPressureGUI liquidGUI = new LiquidPressureGUI();
        liquidGUI.initialize();
    }

    private static void openSolidPressureCalculator() {
        SolidPressureGUI solidGUI = new SolidPressureGUI();
        solidGUI.initialize();
    }
    private static void openWaveCalculator() {
        WaveCalculatorGUI waveGUI = new WaveCalculatorGUI();
        waveGUI.initialize();
    }
}
