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
    private static JTextField amountField;

    public Graphics() {
    }

    public static void createPage() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(3);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, 1));
        JPanel startingCurrencyButtonsPanel = new JPanel();
        ArrayList<GeneralCurrency> currencies = Currency.getCurrencyList();
        ButtonGroup startingCurrencyButtons = new ButtonGroup();
        Iterator var5 = currencies.iterator();

        while(var5.hasNext()) {
            GeneralCurrency currency = (GeneralCurrency)var5.next();
            JToggleButton toggleButton = new JToggleButton(currency.getName());
            startingCurrencyButtonsPanel.add(toggleButton);
            startingCurrencyButtons.add(toggleButton);
        }

        JPanel textFieldPanel = new JPanel();
        amountField = new JTextField(10);
        textFieldPanel.add(amountField);
        JPanel endingCurrencyButtonsPanel = new JPanel();
        ButtonGroup endingCurrencyButtons = new ButtonGroup();
        Iterator var8 = currencies.iterator();

        while(var8.hasNext()) {
            GeneralCurrency currency = (GeneralCurrency)var8.next();
            JToggleButton toggleButton = new JToggleButton(currency.getName());
            endingCurrencyButtonsPanel.add(toggleButton);
            endingCurrencyButtons.add(toggleButton);
        }

        JPanel convertButtonPanel = new JPanel();
        JButton convertButton = new JButton("Convert!");
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