package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Main {

    ItemParser hurtLocker;
    ArrayList<String> groceryAccountedFor;

    public Main() {
        hurtLocker = new ItemParser();
        groceryAccountedFor = new ArrayList<>();
    }

    public String readRawDataToString(String file) throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream(file));

        ArrayList<String> rawDataToString = hurtLocker.parseRawDataIntoStringArray(result);
        ArrayList<ArrayList<String>> namePriceTypeExpiration = new ArrayList<>();

        for (String itemInfo : rawDataToString) {
            namePriceTypeExpiration.add(hurtLocker.findKeyValuePairsInRawItemData(itemInfo));
        }

        for (ArrayList<String> aLS: namePriceTypeExpiration)
            try {
                Item newItem = hurtLocker.parseStringIntoItem(aLS.toString());
                if(!groceryAccountedFor.contains(newItem.getName())) {
                    groceryAccountedFor.add(newItem.getName());
                }

            } catch (ItemParseException e) {

            }
        return groceryListFormat();
    }

    public String groceryListFormat() {
        StringBuilder sb = new StringBuilder();
        for (String item : groceryAccountedFor) {
            int timesSeen = hurtLocker.namePrice.get(item).size();
            sb.append(String.format("name:%8s     seen:%2d %s \n", item, timesSeen, timeOrTimes(timesSeen)));

            //sb.append(String.format("      seen:%3d %s \n", timesSeen, timeOrTimes(timesSeen)));
            sb.append(String.format("=============     =============\n"));
            sb.append(countOccurrences(item));
        }
        int errorCount = hurtLocker.getExceptionsThrown();
        sb.append(String.format("Errors            seen:%2d %s\n", errorCount, timeOrTimes(errorCount)));
        return sb.toString();
    }
    public String countOccurrences(String item) {
        StringBuilder sb = new StringBuilder();
        HashMap<Double, Integer> priceOccurrence = hurtLocker.getPriceOccurrence(item);
        Iterator<Double> itemPriceIterator = priceOccurrence.keySet().iterator();
        int count = 0;
        while (itemPriceIterator.hasNext()) {
            count++;
            Double price = itemPriceIterator.next();
            sb.append(String.format("Price:%7.2f     seen:%2d %s\n", price, priceOccurrence.get(price), timeOrTimes(priceOccurrence.get(price))));
            if (count < 2) sb.append(String.format("-------------     -------------\n"));
            sb.append("\n");
        }
        return sb.toString();
    }

    public String timeOrTimes(int timesSeen) {
        String times;
        if (timesSeen == 1) return "time";
        else return "times";
    }

    public static void main(String[] args) throws Exception{

        Main main = new Main();
        String output = main.readRawDataToString("RawData.txt");
        System.out.println(output);


        // TODO: parse the data in output into items, and display to console.

    }
}
