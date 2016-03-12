package be.kdg.kandoe.unit;

import be.kdg.kandoe.backend.analysis.AprioriFrequentItemsetGenerator;
import be.kdg.kandoe.backend.analysis.FrequentItemsetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestAprioriAlgorithm {

    @Test
    public void testGetMostFrequentCards() {
        AprioriFrequentItemsetGenerator<String> generator = new AprioriFrequentItemsetGenerator<>();

        List<Set<String>> itemsetList = new ArrayList<>();

        itemsetList.add(new HashSet<>(Arrays.asList("card1", "card2")));
        itemsetList.add(new HashSet<>(Arrays.asList("card2", "card3", "card4")));
        itemsetList.add(new HashSet<>(Arrays.asList("card1", "card3", "card4", "card5")));
        itemsetList.add(new HashSet<>(Arrays.asList("card1", "card4", "card5")));
        itemsetList.add(new HashSet<>(Arrays.asList("card1", "card2", "card3")));

        itemsetList.add(new HashSet<>(Arrays.asList("card1", "card2", "card3", "card4")));
        itemsetList.add(new HashSet<>(Arrays.asList("card1")));
        itemsetList.add(new HashSet<>(Arrays.asList("card1", "card2", "card3")));
        itemsetList.add(new HashSet<>(Arrays.asList("card1", "card2", "card4")));
        itemsetList.add(new HashSet<>(Arrays.asList("card2", "card4", "card5")));

        FrequentItemsetData<String> data = generator.generate(itemsetList, 0.2);

        List<Set<String>> frequentItemsetList = data.getFrequentItemsetList();
        assertEquals("The 1st frequent itemsetlist must be correct", "[card1]", frequentItemsetList.get(0).toString());
        assertEquals("The 2nd frequent itemsetlist must be correct", "[card2]", frequentItemsetList.get(1).toString());
        assertEquals("The 3rd frequent itemsetlist must be correct", "[card3]", frequentItemsetList.get(2).toString());
        assertEquals("The 4th frequent itemsetlist must be correct", "[card4]", frequentItemsetList.get(3).toString());
        assertEquals("The 5th frequent itemsetlist must be correct", "[card5]", frequentItemsetList.get(4).toString());
        assertEquals("The 6th frequent itemsetlist must be correct", "[card1, card2]", frequentItemsetList.get(5).toString());
        assertEquals("The 7th frequent itemsetlist must be correct", "[card1, card3]", frequentItemsetList.get(6).toString());
        assertEquals("The 8th frequent itemsetlist must be correct", "[card4, card1]", frequentItemsetList.get(7).toString());
        assertEquals("The 9th frequent itemsetlist must be correct", "[card1, card5]", frequentItemsetList.get(8).toString());
        assertEquals("The 10th frequent itemsetlist must be correct", "[card2, card3]", frequentItemsetList.get(9).toString());
        assertEquals("The 11th frequent itemsetlist must be correct", "[card4, card2]", frequentItemsetList.get(10).toString());
        assertEquals("The 12th frequent itemsetlist must be correct", "[card4, card3]", frequentItemsetList.get(11).toString());
        assertEquals("The 13th frequent itemsetlist must be correct", "[card4, card5]", frequentItemsetList.get(12).toString());
        assertEquals("The 14th frequent itemsetlist must be correct", "[card1, card2, card3]", frequentItemsetList.get(13).toString());
        assertEquals("The 15th frequent itemsetlist must be correct", "[card4, card1, card2]", frequentItemsetList.get(14).toString());
        assertEquals("The 16th frequent itemsetlist must be correct", "[card4, card1, card3]", frequentItemsetList.get(15).toString());
        assertEquals("The 17th frequent itemsetlist must be correct", "[card4, card1, card5]", frequentItemsetList.get(16).toString());
        assertEquals("The 18th frequent itemsetlist must be correct", "[card4, card2, card3]", frequentItemsetList.get(17).toString());
    }
}