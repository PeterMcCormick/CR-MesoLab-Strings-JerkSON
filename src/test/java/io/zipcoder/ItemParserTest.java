package io.zipcoder;


import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemParserTest {

    private String rawSingleItem = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawSingleItemIrregularSeperatorSample = "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##";

    private String rawBrokenSingleItem = "naMe:;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawMultipleItems = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##"
            + "naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##"
            + "NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##";

    private String getRawMultipleBrokenItems = "naMe:;price:3.23;type:Food;expiration:1/25/2016##"
            + "naME:BreaD;price:1.23;type:;expiration:1/02/2016##"
            + "NAMe:BrEAD;price:;type:Food;expiration:2/25/2016##";
    private ItemParser itemParser;

    @Before
    public void setUp() {
        itemParser = new ItemParser();
    }

    @Test
    public void parseRawDataIntoStringArrayTest() {
        Integer expectedArraySize = 3;
        ArrayList<String> items = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        Integer actualArraySize = items.size();
        assertEquals(expectedArraySize, actualArraySize);
    }

    @Test
    public void parseStringIntoItemTest() throws ItemParseException {
        Item expected = new Item("milk", 3.23, "food", "1/25/2016");
        Item actual = itemParser.parseStringIntoItem(rawSingleItem);
        System.out.println(actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void parseStringIntoItemTest1() throws ItemParseException {
        Item expected = new Item("root-_beer", 232321.34, "drink", "12/23/2019");
        Item actual = itemParser.parseStringIntoItem("NAME:ro0t-_beer;pRICE:232321.34;type:dRiNK;expiration:12/23/2019");
        System.out.println(actual.toString());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test(expected = ItemParseException.class)
    public void parseBrokenStringIntoItemTest() throws ItemParseException {
        itemParser.parseStringIntoItem(rawBrokenSingleItem);
    }

    @Test
    public void findKeyValuePairsInRawItemDataTest() {
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItem).size();
        assertEquals(expected, actual);
    }

    @Test
    public void findKeyValuePairsInRawItemDataTestIrregular() {
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItemIrregularSeperatorSample).size();
        assertEquals(expected, actual);
    }

//    @Test
//    public void countExceptionTest() {
//        Integer expected = 3;
//        Integer actual = itemParser.getExceptionsThrown(getRawMultipleBrokenItems);
//        assertEquals(expected, actual);
//    }

}
