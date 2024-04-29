package Ispirer.Test.SortByteArrayApp;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;

public class SortByteArrayApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Byte Array Sorter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton sortButton = new JButton("Sort Array");
            sortButton.addActionListener(e -> {
                try {
                    sortByteArray();
                    JOptionPane.showMessageDialog(frame, "Array sorted and saved to output.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error sorting array: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            frame.getContentPane().add(sortButton);
            frame.setSize(300, 100);
            frame.setVisible(true);
        });
    }

    private static void sortByteArray() throws IOException {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        FileInputStream input = new FileInputStream(inputFile);
        FileOutputStream output = new FileOutputStream(outputFile);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] byteValues = line.trim().split("\\s+");
                byte[] bytes = new byte[byteValues.length];
                for (int i = 0; i < byteValues.length; i++) {
                    bytes[i] = Byte.parseByte(byteValues[i]);
                }
                Arrays.sort(bytes);
                for (byte b : bytes) {
                    output.write(Byte.toString(b).getBytes());
                    output.write(" ".getBytes());
                }
            }
        }

        input.close();
        output.close();
    }
}
