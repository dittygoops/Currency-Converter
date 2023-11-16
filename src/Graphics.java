//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

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
            startingCurrencyButtonsPanel.add(toggleButton);
            startingCurrencyButtons.add(toggleButton);
        }

        textFieldPanel = new JPanel();
        amountField = new JTextField(10);
        textFieldPanel.add(amountField);

        endingCurrencyButtonsPanel = new JPanel();
        endingCurrencyButtons = new ButtonGroup();

        for (GeneralCurrency currency : currencies) {
            JToggleButton toggleButton = new JToggleButton(currency.getName());
            endingCurrencyButtonsPanel.add(toggleButton);
            endingCurrencyButtons.add(toggleButton);
        }

        convertButtonPanel = new JPanel();
        convertButton = new JButton("Convert!");
        convertButton.addActionListener((e) -> {
            System.out.println("Converting!");
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
}