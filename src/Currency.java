//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

// This class represents currencies with amounts
public class Currency extends GeneralCurrency {
    private double amount;

    public Currency(String name, double conversionToUSD, double amount) {
        super(name, conversionToUSD);
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    // convert the start currency to USD and then to the end currency
    public static Currency convert(Currency startCurrency, GeneralCurrency endCurrency) {
        double amount = startCurrency.getAmount();
        amount /= startCurrency.getConversionToUSD();
        amount *= endCurrency.getConversionToUSD();
        Currency currency = new Currency(endCurrency.getName(), endCurrency.getConversionToUSD(), amount);
        return currency;
    }

    public String toString() {
        return String.format("%.2f", amount) + " in " + this.getName();
    }
}