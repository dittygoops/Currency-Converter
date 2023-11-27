//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// This class represents currencies without an amount
public class GeneralCurrency {
    private String name;
    private double conversionToUSD;

    public GeneralCurrency(String name, double conversionToUSD) {
        this.name = name;
        this.conversionToUSD = conversionToUSD;
    }

    public String getName() {
        return this.name;
    }

    public double getConversionToUSD() {
        return this.conversionToUSD;
    }

    public String toString() {
        return this.name + " --> " + this.conversionToUSD;
    }

    // Get a list of 10 popular currencies and their conversion rates from online source
    public static ArrayList<GeneralCurrency> getCurrencyList() {
        ArrayList<GeneralCurrency> currencies = new ArrayList();
        GeneralCurrency usd = new GeneralCurrency("United States Dollar", 1.0);
        currencies.add(usd);

        try {
            String url = "https://www.x-rates.com/table/?from=USD&amount=1";
            // doc is the code for the above url
            Document doc = Jsoup.connect(url).get();
            // Using css selectors, we find the specific currencies on the page we are looking for
            Element currencyTable = doc.select("#content > div:nth-child(1) > div > div.col2.pull-right.module.bottomMargin > div.moduleContent > table:nth-child(4) > tbody").first();
            Elements currencyRows = currencyTable.children();
            Iterator it = currencyRows.iterator();

            while(it.hasNext()) {
                // each row has the name of the currency and the conversion factor
                Element currencyRow = (Element)it.next();
                String[] data = currencyRow.text().split(" ");
                String name = "";
                double conversionToUSD = 0.0;

                // concatenate all strings before the conversion factors to find the full name of the currency
                for(int i = 0; i < data.length - 2; ++i) {
                    name = name + data[i];
                    if (i < data.length - 3) {
                        name = name + " ";
                    }
                }

                // find conversion factor
                conversionToUSD = Double.parseDouble(data[data.length - 2]);

                // create GeneralCurrency objects and add them to the list
                GeneralCurrency currency = new GeneralCurrency(name, conversionToUSD);
                currencies.add(currency);
            }
        } catch (IOException e) {
            // in case the website has some error, we catch the error here
            e.printStackTrace();
        }

        return currencies;
    }
}