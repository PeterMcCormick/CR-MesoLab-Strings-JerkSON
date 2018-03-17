package io.zipcoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    Pattern pattern;
    Matcher matcher;
    Integer countExceptionsThrown = 0;

    HashMap<HashMap<String, ArrayList<Double>>, Integer> namePriceOccurence = new HashMap<>();
    public HashMap<String, ArrayList<Double>> namePrice;

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }

    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        return response;
    }

    public Integer getExceptionsThrown(String rawData)  {

        ArrayList<String> parsedRawData = parseRawDataIntoStringArray(rawData);
        for (int i = 0; i < parsedRawData.size(); i++)
            try {
            parseStringIntoItem(parsedRawData.get(i));
            } catch (ItemParseException e){
        }
        return this.countExceptionsThrown;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException{

        String name = checkName(rawItem);
        Double price = Double.valueOf(checkPrice(rawItem));
        String type = checkType(rawItem);
        String expiration = checkExpiration(rawItem);
       // getNamePrice(name, price);
        return new Item(name, price, type, expiration);

    }
    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[@|;|^|%|*]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);
        return response;
    }



    public String checkName(String input) throws ItemParseException{

        Pattern patternName = Pattern.compile("([Nn]..[Ee]:)([a-zA-Z\\s|\\d|\\-|_]+)");

        Matcher matcherName = patternName.matcher(input);

        if(matcherName.find())
            if (matcherName.group(2).contains("0"))
                return matcherName.group(2).replace('0', 'o');
            else
            return matcherName.group(2).toLowerCase();
        else {
            countExceptionsThrown++;
            throw new ItemParseException();
        }

    }

    public String checkPrice(String input) throws ItemParseException{
        Pattern patternPrice = Pattern.compile("([Pp]...[Ee]:)(\\d{1,6}\\.\\d{2})");
        Matcher matcherPrice = patternPrice.matcher(input);

        if(matcherPrice.find())
            return matcherPrice.group(2);
        else {
            countExceptionsThrown++;
            throw new ItemParseException();
        }
    }


    public String checkType(String input) throws ItemParseException {
        Pattern patternType = Pattern.compile("([Tt]..[Ee]:)(\\w+)");
        Matcher matcherType = patternType.matcher(input);

        if(matcherType.find())
            return matcherType.group(2).toLowerCase();
        else {
            countExceptionsThrown++;
            throw new ItemParseException();
        }
    }

    public String checkExpiration(String input) throws ItemParseException {
        Pattern patternExpiration = Pattern.compile("([Ee]........[Nn]:)(\\d{1,2}\\/\\d{2}\\/\\d{4})");
        Matcher matcherExpiration = patternExpiration.matcher(input);

        if(matcherExpiration.find())
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

    public void countPriceOccurrence(HashMap namePrice) {
        int count = 0;
        for (Object entry : namePrice.values()) {
            ArrayList<Double> prices = (ArrayList<Double>)entry;
            for(Double price : prices) {
                getNumberOfOccurrences(prices, price);
            }
        }
    }

    public int getNumberOfOccurrences(ArrayList<Double> list, Double valueToCheck) {
        int counter = 0;
        for(Double price : list) {
            if(price == valueToCheck) {
                counter++;
            }
        }
        return counter;
    }

public static void main(String[]args){
        HashMap<String, ArrayList<Double>> map = new HashMap<>();
        String item = "Milk";
        ArrayList<Double> prices = new ArrayList<>();

        Double one = 2.15;
        Double two = 3.15;

        prices.add(one);
        prices.add(two);

        map.put(item, prices);
        ItemParser itemParser = new ItemParser();
        itemParser.countPriceOccurrence(map);
    }


}