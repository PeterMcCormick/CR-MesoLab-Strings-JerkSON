package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.HashMap;



public class Main {

    public HashMap<String, ArrayList<Double>> namePrice;
    HashMap<HashMap<String, ArrayList<Double>>, Integer> namePriceOccurence = new HashMap<>();

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        System.out.println(output);
        // TODO: parse the data in output into items, and display to console.


    }

    public HashMap getNamePrice(String name, Double price) {

        if (!namePrice.containsKey(name)) {
            namePrice.put(name, new ArrayList<>());
            namePrice.get(name).add(price);
        } else {
            namePriceOccurence.put(namePriceOccurence.containsKey(;
        }
    }
}
