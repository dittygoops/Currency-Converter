//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Graphics {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel startingCurrencyButtonsPanel;
    private static ArrayList<GeneralCurrency> currencies;
    private static ButtonGroup startingCurrencyButtons;
    private static JPanel textFieldPanel;
    private static JTextField amountField;
    private static JPanel endingCurrencyButtonsPanel;
    private static ButtonGroup endingCurrencyButtons;
    private static JPanel convertButtonPanel;
    private static JButton convertButton;

    public Graphics() {
    }

    public static void createPage() {
        frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(3);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, 1));
        startingCurrencyButtonsPanel = new JPanel();
        currencies = Currency.getCurrencyList();
        startingCurrencyButtons = new ButtonGroup();

        for (GeneralCurrency currency : currencies) {
            JToggleButton toggleButton = new JToggleButton(currency.getName());
            toggleButton.putClientProperty("GeneralCurrency Object", currency);
            startingCurrencyButtonsPanel.add(toggleButton);
            startingCurrencyButtons.add(toggleButton);
        }

        textFieldPanel = new JPanel();
        amountField = createNumericInputField();
        textFieldPanel.add(amountField);

        endingCurrencyButtonsPanel = new JPanel();
        endingCurrencyButtons = new ButtonGroup();

        for (GeneralCurrency currency : currencies) {
            JToggleButton toggleButton = new JToggleButton(currency.getName());
            toggleButton.putClientProperty("GeneralCurrency Object", currency);
            endingCurrencyButtonsPanel.add(toggleButton);
            endingCurrencyButtons.add(toggleButton);
        }

        convertButtonPanel = new JPanel();
        convertButton = new JButton("Convert!");
        convertButton.addActionListener((e) -> {
            convertButton();
        });
        convertButtonPanel.add(convertButton);

        mainPanel.add(startingCurrencyButtonsPanel);
        mainPanel.add(textFieldPanel);
        mainPanel.add(endingCurrencyButtonsPanel);
        mainPanel.add(convertButtonPanel);

        frame.getContentPane().add(mainPanel);
        frame.setSize(1200, 600);
        frame.setVisible(true);
    }

    public static JTextField createNumericInputField() {
        JTextField textField = new JTextField(10); // Set preferred width

        // Create a document filter to allow only digits and a single decimal point
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                currentText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

                if (isValidNumericInput(currentText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                currentText = currentText.substring(0, offset) + string + currentText.substring(offset);

                if (isValidNumericInput(currentText)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            // Validate the input for digits and a single decimal point
            private boolean isValidNumericInput(String input) {
                return input.matches("\\d*\\.?\\d*");
            }
        });

        return textField;
    }

    private static void convertButton() {
        JToggleButton selectedStartButton = getSelectedToggleButton(startingCurrencyButtons);
        if (selectedStartButton == null) {
            System.out.println("No Starting Currency selected!");
            return;
        }
        GeneralCurrency startGeneralCurrency = (GeneralCurrency) selectedStartButton.getClientProperty("GeneralCurrency Object");

        String textInput = amountField.getText();
        if (textInput.isEmpty()) {
            textInput = "0";
        }
        double amount = Double.parseDouble(textInput);

        Currency startCurrency = new Currency(startGeneralCurrency.getName(), startGeneralCurrency.getConversionToUSD(), amount);

        JToggleButton selectedEndButton = getSelectedToggleButton(endingCurrencyButtons);
        if (selectedEndButton == null) {
            System.out.println("No End Currency selected!");
            return;
        }
        GeneralCurrency endCurrency = (GeneralCurrency) selectedEndButton.getClientProperty("GeneralCurrency Object");

        Currency result = Currency.convert(startCurrency, endCurrency);
        displayResult(result);
    }

    public static JToggleButton getSelectedToggleButton(ButtonGroup buttonGroup) {
        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            JToggleButton button = (JToggleButton) buttons.nextElement();
            if (button.isSelected()) {
                return button;
            }
        }
        return null; // No button selected
    }

    public static void displayResult(Currency resultCurrency) {

    }
}