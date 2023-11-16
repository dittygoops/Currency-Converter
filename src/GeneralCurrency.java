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

    public static ArrayList<GeneralCurrency> getCurrencyList() {
        ArrayList<GeneralCurrency> currencies = new ArrayList();
        GeneralCurrency usd = new GeneralCurrency("United States Dollar", 1.0);
        currencies.add(usd);

        try {
            String url = "https://www.x-rates.com/table/?from=USD&amount=1";
            Document doc = Jsoup.connect(url).get();
            Element currencyTable = doc.select("#content > div:nth-child(1) > div > div.col2.pull-right.module.bottomMargin > div.moduleContent > table:nth-child(4) > tbody").first();
            Elements currencyRows = currencyTable.children();
            Iterator var6 = currencyRows.iterator();

            while(var6.hasNext()) {
                Element currencyRow = (Element)var6.next();
                String[] data = currencyRow.text().split(" ");
                String name = "";
                double conversionToUSD = 0.0;

                for(int i = 0; i < data.length - 2; ++i) {
                    name = name + data[i];
                    if (i < data.length - 3) {
                        name = name + " ";
                    }
                }

                conversionToUSD = Double.parseDouble(data[data.length - 2]);
                GeneralCurrency currency = new GeneralCurrency(name, conversionToUSD);
                currencies.add(currency);
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        return currencies;
    }
}