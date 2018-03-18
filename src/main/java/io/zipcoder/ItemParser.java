package io.zipcoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    Pattern pattern;
    Matcher matcher;
    Integer countExceptionsThrown = 0;

    HashMap<Double, Integer> priceOccurrence = new HashMap<>();
    HashMap<String, ArrayList<Double>> namePrice = new HashMap<>();

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString) {
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }

    public ArrayList<String> parseRawDataIntoStringArray(String rawData) {
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, rawData);
        return response;
    }

    public Integer getExceptionsThrown(String rawData) {

        ArrayList<String> parsedRawData = parseRawDataIntoStringArray(rawData);
        for (int i = 0; i < parsedRawData.size(); i++)
            try {
                parseStringIntoItem(parsedRawData.get(i));
            } catch (ItemParseException e) {
            }
        return this.countExceptionsThrown;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException {

        String name = checkName(rawItem);
        Double price = Double.valueOf(checkPrice(rawItem));
        String type = checkType(rawItem);
        String expiration = checkExpiration(rawItem);
        getNamePrice(name, price);
        return new Item(name, price, type, expiration);

    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem) {
        String stringPattern = "[@|;|^|%|*]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, rawItem);
        return response;
    }


    public String checkName(String input) throws ItemParseException {

        Pattern patternName = Pattern.compile("([Nn]..[Ee]:)([a-zA-Z\\s|\\d|\\-|_]+)");

        Matcher matcherName = patternName.matcher(input);

        if (matcherName.find())
            if (matcherName.group(2).contains("0"))
                return matcherName.group(2).replace('0', 'o');
            else
                return matcherName.group(2).toLowerCase();
        else {
            countExceptionsThrown++;
            throw new ItemParseException();
        }

    }

    public String checkPrice(String input) throws ItemParseException {
        Pattern patternPrice = Pattern.compile("([Pp]...[Ee]:)(\\d{1,6}\\.\\d{2})");
        Matcher matcherPrice = patternPrice.matcher(input);

        if (matcherPrice.find())
            return matcherPrice.group(2);
        else {
            countExceptionsThrown++;
            throw new ItemParseException();
        }
    }


    public String checkType(String input) throws ItemParseException {
        Pattern patternType = Pattern.compile("([Tt]..[Ee]:)(\\w+)");
        Matcher matcherType = patternType.matcher(input);

        if (matcherType.find())
            return matcherType.group(2).toLowerCase();
        else {
            countExceptionsThrown++;
            throw new ItemParseException();
        }
    }

    public String checkExpiration(String input) throws ItemParseException {
        Pattern patternExpiration = Pattern.compile("([Ee]........[Nn]:)(\\d{1,2}\\/\\d{2}\\/\\d{4})");
        Matcher matcherExpiration = patternExpiration.matcher(input);

        if (matcherExpiration.find())
            return matcherExpiration.group(2);
        else {
            countExceptionsThrown++;
            throw new ItemParseException();
        }
    }


    public void getNamePrice(String name, Double price) {
        if (!namePrice.containsKey(name)) {
            namePrice.put(name, new ArrayList<>());
            namePrice.get(name).add(price);
        } else {
            namePrice.get(name).add(price);
        }
    }

    public HashMap getPriceOccurrence(HashMap namePrice, String name) {

        ArrayList<Double> prices = (ArrayList<Double>) namePrice.get(name);
        System.out.println(prices);
        for (int i = 0; i < prices.size(); i++) {
            double price = prices.get(i);
            if (!priceOccurrence.containsKey(prices.get(i))) {
                priceOccurrence.put(price, 1);
            } else {
                priceOccurrence.put(price, priceOccurrence.get(price) + 1);
            }
        }
        return priceOccurrence;
    }

    public HashMap<String, ArrayList<Double>> getNamePrice() {
        return namePrice;
    }


    public static void main(String[] args) {
        HashMap<String, ArrayList<Double>> map = new HashMap<>();
        String item = "Milk";
        ArrayList<Double> prices = new ArrayList<>();

        Double one = 2.15;
        Double two = 3.15;
        Double three = 3.21;
        Double four = 2.45;

        prices.add(one);
        prices.add(two);
        prices.add(three);
        prices.add(four);

        map.put(item, prices);
        ItemParser itemParser = new ItemParser();

        System.out.println(itemParser.getPriceOccurrence(map, item));

    }


}