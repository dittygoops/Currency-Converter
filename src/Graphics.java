//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Graphics {
    // all variables are in the class itself so they can be used by any of the methods below
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
    private static JLabel resultLabel;

    public static void createPage() {
        frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(3);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        createStartCurrencyButtons();

        createTextField();

        createEndCurrencyButtons();

        createConvertButton();

        frame.getContentPane().add(mainPanel);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }

    private static void createStartCurrencyButtons() {
        startingCurrencyButtonsPanel = new JPanel();
        startingCurrencyButtonsPanel.setBorder(BorderFactory.createTitledBorder("Starting Currency"));
        startingCurrencyButtonsPanel.setLayout(new GridLayout(0, 3));
        currencies = Currency.getCurrencyList();
        startingCurrencyButtons = new ButtonGroup();

        // for each currency, make a toggle button and add to the button group
        for (GeneralCurrency currency : currencies) {
            JToggleButton toggleButton = new JToggleButton(currency.getName());
            toggleButton.putClientProperty("GeneralCurrency Object", currency);
            startingCurrencyButtonsPanel.add(toggleButton);
            startingCurrencyButtons.add(toggleButton);
        }

        // add the startingCurrencyButtonsPanel to the mainPanel and make it left-justified
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(startingCurrencyButtonsPanel, gbc);
    }

    private static void createTextField() {
        textFieldPanel = new JPanel();
        amountField = createNumericInputField();
        textFieldPanel.setBorder(BorderFactory.createTitledBorder("Amount"));
        textFieldPanel.add(amountField);

        // add the textFieldPanel to the mainPanel and make it left-justified
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(textFieldPanel, gbc);
    }

    private static void createEndCurrencyButtons() {
        endingCurrencyButtonsPanel = new JPanel();
        endingCurrencyButtonsPanel.setBorder(BorderFactory.createTitledBorder("Ending Currency"));
        endingCurrencyButtonsPanel.setLayout(new GridLayout(0, 3));
        endingCurrencyButtons = new ButtonGroup();

        // for each currency, make a toggle button and add to the button group
        for (GeneralCurrency currency : currencies) {
            JToggleButton toggleButton = new JToggleButton(currency.getName());
            toggleButton.putClientProperty("GeneralCurrency Object", currency);
            endingCurrencyButtonsPanel.add(toggleButton);
            endingCurrencyButtons.add(toggleButton);
        }

        // add the endingCurrencyButtonsPanel to the mainPanel and make it left-justified
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(endingCurrencyButtonsPanel, gbc);
    }

    private static void createConvertButton() {
        convertButtonPanel = new JPanel();
        // convertButtonPanel will also contain the conversion result to the right of it
        convertButtonPanel.setLayout(new BoxLayout(convertButtonPanel, BoxLayout.X_AXIS));

        // when convertButton is clicked, call convertButton()
        convertButton = new JButton("Convert!");
        convertButton.addActionListener((e) -> {
            convertButton();
        });

        // Push convertButton and resultLabel as far apart from each other -> convertButton is left justified and resultLabel is right justified
        convertButtonPanel.add(convertButton);
        convertButtonPanel.add(Box.createHorizontalGlue());
        resultLabel = new JLabel("");
        convertButtonPanel.add(resultLabel);

        // Make the convertButtonPanel the same width as the above panels
        int preferredWidth = startingCurrencyButtonsPanel.getPreferredSize().width;
        convertButtonPanel.setPreferredSize(new Dimension(preferredWidth, convertButton.getPreferredSize().height));

        // add the convertButtonPanel to the mainPanel and make it left-justified
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(convertButtonPanel, gbc);
    }

    // Create a document filter to allow only digits and a single decimal point
    private static JTextField createNumericInputField() {
        JTextField textField = new JTextField(10);

        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                currentText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

                // if the given input is valid, replace the text in the amountField with the correct input
                if (isValidNumericInput(currentText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean isValidNumericInput(String input) {
                // if input is any number of digits, one decimal point, and any number of digits, return true
                return input.matches("\\d*\\.?\\d*");
            }
        });

        return textField;
    }

    private static void convertButton() {
        JToggleButton selectedStartButton = getSelectedToggleButton(startingCurrencyButtons);

        // if no starting currency is selected, return out of the function
        if (selectedStartButton == null) {
            return;
        }
        GeneralCurrency startGeneralCurrency = (GeneralCurrency) selectedStartButton.getClientProperty("GeneralCurrency Object");

        // if no amount is specified, return out of the function
        String textInput = amountField.getText();
        if (textInput.isEmpty()) {
            return;
        }
        double amount = Double.parseDouble(textInput);

        Currency startCurrency = new Currency(startGeneralCurrency.getName(), startGeneralCurrency.getConversionToUSD(), amount);

        // if no end currency is selected, return out of the function
        JToggleButton selectedEndButton = getSelectedToggleButton(endingCurrencyButtons);
        if (selectedEndButton == null) {
            return;
        }
        GeneralCurrency endCurrency = (GeneralCurrency) selectedEndButton.getClientProperty("GeneralCurrency Object");

        Currency result = Currency.convert(startCurrency, endCurrency);
        displayResult(startGeneralCurrency, amount, result);
    }

    private static JToggleButton getSelectedToggleButton(ButtonGroup buttonGroup) {
        // go through the button group to find and return the selected button
        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            JToggleButton button = (JToggleButton) buttons.nextElement();
            if (button.isSelected()) {
                return button;
            }
        }
        return null;
    }

    private static void displayResult(GeneralCurrency startCurrency, double amount, Currency resultCurrency) {
        String str = String.format("%.2f", amount) + " in " + startCurrency.getName() + " is " + resultCurrency.toString();
        resultLabel.setText(str);

        // after changing the result label, repaint the entire application to reflect changes
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}