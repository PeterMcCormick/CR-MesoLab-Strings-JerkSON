package io.zipcoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    Pattern pattern;
    Matcher matcher;
    Integer countExceptionsThrown = 0;



    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }
    //Splits string
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

        return new Item(name, price, type, expiration);

    }
    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[@|;|^|%|*|%]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);
        return response;
    }

    public String fixCookie(String input) {
        Pattern patternCookies = Pattern.compile("[Cc][oO0][oO0][kK][iI][eE][sS]");
        Matcher matcherCookie = patternCookies.matcher(input);
        return matcherCookie.replaceAll("cookies");
    }

    public String checkName(String input) throws ItemParseException{

        String newInput = fixCookie(input);
        Pattern patternName = Pattern.compile("([Nn]..[Ee]:)(\\w+)");
        Matcher matcherName = patternName.matcher(input);

        if(matcherName.find())
            return matcherName.group(2).toLowerCase();
        else {
            countExceptionsThrown++;
            throw new ItemParseException();
        }

    }

    public String checkPrice(String input) throws ItemParseException{
        Pattern patternPrice = Pattern.compile("([Pp]...[Ee]:)(\\d\\.\\d{2})");
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
        Pattern patternExpiration = Pattern.compile("([Ee]........[Nn]:)(\\d\\/\\d{2}\\/\\d{4})");
        Matcher matcherExpiration = patternExpiration.matcher(input);

        if(matcherExpiration.find())
            return matcherExpiration.group(2);
        else {
            countExceptionsThrown++;
            throw new ItemParseException();
        }

    }



}




//    int count = 0;
//
//    ArrayList<Item> myItem = new ArrayList<>();
//
//    String name = "(N|n)..(E|e)";
//    String strPrice = "\\d\\.\\d\\d";
//    Double price = Double.valueOf(strPrice);
//    String type = "(T|t)..(E|e)";
//    String expiration = "(E|e)........(N|n)";
//    ArrayList<String> temp = parseRawDataIntoStringArray(rawItem);
//
//        for (int i = 0; i < temp.size(); i++) {
//        if (findKeyValuePairsInRawItemData(temp.get(i)).equals(null))  {
//        count++;
//        throw new ItemParseException();
//        }
//        else if (findKeyValuePairsInRawItemData(temp.get(i)).size() == 4) {
//        for (int j = 0; j < temp.size() ; j++) {
//        findKeyValuePairsInRawItemData(temp.get(i));
//        }
//        }
//        }
//        return new Item (name, price, type, expiration);