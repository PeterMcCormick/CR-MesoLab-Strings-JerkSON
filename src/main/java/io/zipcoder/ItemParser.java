package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    Pattern pattern;
    Matcher matcher;

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }
    //Splits string
    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        return response;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException{
        int count = 0;

        ArrayList<Item> myItem = new ArrayList<>();

        String name = "(N|n)..(E|e)";
        String strPrice = "\\d\\.\\d\\d";
        Double price = Double.valueOf(strPrice);
        String type = "(T|t)..(E|e)";
        String expiration = "(E|e)........(N|n)";
        ArrayList<String> temp = parseRawDataIntoStringArray(rawItem);

        for (int i = 0; i < temp.size(); i++) {
            if (findKeyValuePairsInRawItemData(temp.get(i)).equals(null))  {
                count++;
                throw new ItemParseException();
            }
            else if (findKeyValuePairsInRawItemData(temp.get(i)).size() == 4) {
                for (int j = 0; j < temp.size() ; j++) {
                    findKeyValuePairsInRawItemData(temp.get(i));
                }
            }
        }
        return new Item (name, price, type, expiration);
    }
    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[@|;|^|%|*|%]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);
        return response;
    }

    public Object checkSpelling() {
        return null;
    }

    public Object checkName(String nameInput){
        String regexName = "(N|n)..(E|e)";
        Pattern patternName = Pattern.compile(regexName);
        pattern = patternName.compile(regexName);
        Matcher matcherName = patternName.matcher(nameInput);
        return matcher.replaceAll("Name");

    }

}
