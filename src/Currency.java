//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Currency extends GeneralCurrency {
    private double amount;

    public Currency(String name, double conversionToUSD, double amount) {
        super(name, conversionToUSD);
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public static Currency convert(Currency startCurrency, GeneralCurrency endCurrency) {
        double amount = startCurrency.getAmount();
        amount /= startCurrency.getConversionToUSD();
        amount *= endCurrency.getConversionToUSD();
        Currency currency = new Currency(endCurrency.getName(), endCurrency.getConversionToUSD(), amount);
        System.out.println(currency);
        return currency;
    }

    public String toString() {
        return String.format("%.2f", amount) + " in " + this.getName();
    }
}