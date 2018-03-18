package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.HashMap;



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
                Item newItem = hurtLocker.parseStringIntoItem(aLS)

            } catch (ItemParseException e) {

            }
        return result;
    }

//    public String groceryListFormat() {
//        StringBuilder sb = new StringBuilder();
//        for (Object item :hurtLocker.namePrice.keySet()) {
//            sb.append("name:%12s", item);
//            System.out.println(sb.toString());
//        } return sb.toString();
//    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
       // System.out.println(output);
        Main main = new Main();

        main.groceryListFormat();

        // TODO: parse the data in output into items, and display to console.

    }
}
