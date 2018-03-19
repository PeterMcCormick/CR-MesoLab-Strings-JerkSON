package io.zipcoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

    private Main mainTest = new Main();
    @Before
    public void Setup() {



        String originalRawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016" +
                "##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016" +
                "##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016" +
                "##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016" +
                "##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016" +
                "##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016" +
                "##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016" +
                "##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016" +
                "##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016" +
                "##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016" +
                "##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016" +
                "##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016" +
                "##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016" +
                "##naMe:;price:3.23;type:Food;expiration:1/04/2016" +
                "##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016" +
                "##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016" +
                "##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016" +
                "##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016" +
                "##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016" +
                "##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016" +
                "##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016" +
                "##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016" +
                "##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016" +
                "##naMe:MilK;priCe:;type:Food;expiration:4/25/2016" +
                "##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016" +
                "##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016" +
                "##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016" +
                "##naMe:;price:3.23;type:Food^expiration:1/04/2016##";

        String practice = "naMe:MaRShMAll0w;price:3.23;type:Food;expiration:10/25/2022" +
                "##naME:c0cAC0la;price:1.23;type:Food;expiration:1/02/2016" +
                "##NAMe:PREtzels;price:1.23;type:Food;expiration:2/25/2016" +
                "##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016" +
                "##naMe:TwINKies;price:4.25;type:Food%expiration:1/25/2016" +
                "##naMe:3DozEggs;price:2.25;type:Food*expiration:1/25/2016" +
                "##naMe:PANCAKEMIX;price:1.25;type:Food;expiration:3/22/2016" +
                "##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016" +
                "##NAME:2lbShrmp;price:3.23;type:Food;expiration:1/17/2016" +
                "##naMe:Snpple24;price:10.23;type:Food!expiration:4/25/2016" +
                "##naMe:aplJax;price:0.25;type:Food;expiration:1/23/2016" +
                "##naMe:aplJax;price:0.23;type:Food;expiration:5/02/2016" +
                "##NAMe:;price:1.23;type:Food;expiration:1/25/2016" +
                "##naMe:;price:3.23;type:Food;expiration:1/04/2016" +
                "##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016" +
                "##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016" +
                "##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016" +
                "##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016" +
                "##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016" +
                "##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016" +
                "##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016" +
                "##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016" +
                "##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016" +
                "##naMe:MilK;priCe:;type:Food;expiration:4/25/2016" +
                "##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016" +
                "##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016" +
                "##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016" +
                "##naMe:;price:3.23;type:Food^expiration:1/04/2016##";



    }
    @Test
    public void MainTest()throws Exception {
        String expected = "name:    Milk \t\t seen: 6 times\n" +
                "============= \t \t =============\n" +
                "Price: \t 3.23\t\t seen: 5 times\n" +
                "-------------\t\t -------------\n" +
                "Price:   1.23\t\t seen: 1 time\n" +
                "\n" +
                "name:   Bread\t\t seen: 6 times\n" +
                "=============\t\t =============\n" +
                "Price:   1.23\t\t seen: 6 times\n" +
                "-------------\t\t -------------\n" +
                "\n" +
                "name: Cookies     \t seen: 8 times\n" +
                "=============     \t =============\n" +
                "Price:   2.25        seen: 8 times\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:  Apples     \t seen: 4 times\n" +
                "=============     \t =============\n" +
                "Price:   0.25     \t seen: 2 times\n" +
                "-------------     \t -------------\n" +
                "Price:   0.23  \t \t seen: 2 times\n" +
                "\n" +
                "Errors         \t \t seen: 4 times\n";

        String maybe = "name:    Milk        seen: 6 times\n" +
                "=============        =============\n" +
                "Price:   3.23        seen: 5 times\n" +
                "-------------        -------------\n" +
                "Price:   1.23        seen: 1 time\n" +
                "\n" +
                "name:   Bread        seen: 6 times\n" +
                "=============        =============\n" +
                "Price:   1.23        seen: 6 times\n" +
                "-------------        -------------\n" +
                "\n" +
                "name: Cookies        seen: 8 times\n" +
                "=============        =============\n" +
                "Price:   2.25        seen: 8 times\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:  Apples        seen: 4 times\n" +
                "=============        =============\n" +
                "Price:   0.25        seen: 2 times\n" +
                "-------------        -------------\n" +
                "Price:   0.23        seen: 2 times\n" +
                "\n" +
                "Errors               seen: 4 times";
        String actual = mainTest.readRawDataToString("RawData.txt");


        //This test is simply here to visually compare expected vs actual

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void gettingCrazyTest() throws Exception {

        String expected = "name:Mrshmllo        seen: 1 time\n" +
                "=============        =============\n" +
                "Price:   3.23        seen: 1 time\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:Cocacola        seen: 1 time\n" +
                "=============        =============\n" +
                "Price:   1.23        seen: 1 time\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:Pretzels        seen: 1 time\n" +
                "=============        =============\n" +
                "Price:   1.23        seen: 1 time\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:    Milk        seen: 3 times\n" +
                "=============        =============\n" +
                "Price:   3.23        seen: 3 times\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:Twinkies        seen: 1 time\n" +
                "=============        =============\n" +
                "Price:   4.25        seen: 1 time\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:3dozeggs        seen: 1 time\n" +
                "=============        =============\n" +
                "Price:   2.25        seen: 1 time\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:Panckemx        seen: 1 time\n" +
                "=============        =============\n" +
                "Price:   1.25        seen: 1 time\n" +
                "-------------        -------------\n" +
                "\n" +
                "name: Cookies        seen: 5 times\n" +
                "=============        =============\n" +
                "Price:   2.25        seen: 3 times\n" +
                "-------------        -------------\n" +
                "Price:   2.15        seen: 1 time\n" +
                "-------------        -------------\n" +
                "Price:   2.45        seen: 1 time\n" +
                "\n" +
                "name:2lbshrmp        seen: 1 time\n" +
                "=============        =============\n" +
                "Price:   3.23        seen: 1 time\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:Snpple24        seen: 1 time\n" +
                "=============        =============\n" +
                "Price:  10.23        seen: 1 time\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:  Apljax        seen: 2 times\n" +
                "=============        =============\n" +
                "Price:   0.25        seen: 1 time\n" +
                "-------------        -------------\n" +
                "Price:   0.23        seen: 1 time\n" +
                "\n" +
                "name:   Bread        seen: 3 times\n" +
                "=============        =============\n" +
                "Price:   1.23        seen: 3 times\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:  Apples        seen: 2 times\n" +
                "=============        =============\n" +
                "Price:   0.25        seen: 1 time\n" +
                "-------------        -------------\n" +
                "Price:   0.23        seen: 1 time\n" +
                "\n" +
                "Errors               seen: 5 times\n";
        String actual = mainTest.readRawDataToString("TestData.txt");
        System.out.println(actual);
        Assert.assertEquals(expected, actual);

    }
}
