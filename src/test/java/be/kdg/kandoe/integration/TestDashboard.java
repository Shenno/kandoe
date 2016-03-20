package be.kdg.kandoe.integration;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.CardSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.SessionService;
import be.kdg.kandoe.backend.services.api.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestDashboard {

    private User user;
    private User user2;
    private User user3;
    private Organisation organisation;
    private Theme theme;

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Before
    public void setup() {
        user = new User("firstname.lastname@kandoe.be", "password");
        user = userService.addUser(user);

        user2 = new User("firstname2.lastname2@kandoe.be", "password");
        user2 = userService.addUser(user2);

        user3 = new User("firstname3.lastname3@kandoe.be", "password");
        user3 = userService.addUser(user3);

        organisation = new Organisation("organisation", user);
        organisation = userService.addOrganisation(organisation);

        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;

        List<Tag> tags = new ArrayList<>();

        theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        theme = contentService.addTheme(theme);

        List<Card> cardsSession1 = new ArrayList<>();
        List<Card> cardsSession2 = new ArrayList<>();
        List<Card> cardsSession3 = new ArrayList<>();

        String cardDescription="card1";
        String url="http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg";
        Card card= new Card(cardDescription,url,theme);
        card=contentService.addCard(card);
        cardsSession1.add(card);
        cardsSession2.add(card);
        cardsSession3.add(card);

        cardDescription="card2";
        url = "http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg";
        card = new Card(cardDescription, url, theme);
        card = contentService.addCard(card);
        cardsSession1.add(card);
        cardsSession2.add(card);

        cardDescription="card3";
        url = "http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg";
        card = new Card(cardDescription, url, theme);
        card = contentService.addCard(card);
        cardsSession1.add(card);
        cardsSession3.add(card);

        List<String> usernames = new ArrayList<>();
        usernames.add(user.getUsername());
        usernames.add(user2.getUsername());
        usernames.add(user3.getUsername());

        Session session1 = new AsynchronousSession(user.getId(), false, 60, 4, "sessionName1");
        session1 = sessionService.addSession(session1, theme.getId(), cardsSession1, usernames);

        Session session2 = new AsynchronousSession(user.getId(), false, 60, 4, "sessionName2");
        session2 = sessionService.addSession(session2, theme.getId(), cardsSession2, usernames);

        Session session3 = new AsynchronousSession(user.getId(), false, 60, 4, "sessionName3");
        session3 = sessionService.addSession(session3, theme.getId(), cardsSession3, usernames);
    }

    @After
    public void tearDown(){
        userService.deleteOrganisation(organisation.getId());
        userService.deleteUser(user.getId());
        userService.deleteUser(user2.getId());
        userService.deleteUser(user3.getId());
    }

    @Test
    public void testGetMostFrequentCardCombinations() {
        List<Set<CardSession>> frequentItemsetList = contentService.findMostFrequentCardCombinations(theme.getThemeId());

        assertEquals("The amount of combinations must be correct", 6, frequentItemsetList.size());

        for(int i = 0; i < frequentItemsetList.size()-1; i++) {
            assertEquals("The amount of cards in the combination must be correct", 2, frequentItemsetList.get(i).size());
        }

        assertEquals("The amount of cards in the combination must be correct", 3, frequentItemsetList.get(5).size());

        //TODO: Geeft nu nog steeds op een willekeurige volgorde terug?
       /*
        String frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(0)).toString();
        assertEquals("The 1st frequent itemsetlist must be correct", "[card3, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(1)).toString();
        assertEquals("The 2nd frequent itemsetlist must be correct", "[card3, card2]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(2)).toString();
        assertEquals("The 3rd frequent itemsetlist must be correct", "[card1, card3]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(3)).toString();
        assertEquals("The 4th frequent itemsetlist must be correct", "[card1, card2]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(4)).toString();
        assertEquals("The 5th frequent itemsetlist must be correct", "[card1, card2]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(5)).toString();
        assertEquals("The 6th frequent itemsetlist must be correct", "[card2, card2]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(6)).toString();
        assertEquals("The 7th frequent itemsetlist must be correct", "[card2, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(7)).toString();
        assertEquals("The 8th frequent itemsetlist must be correct", "[card1, card2]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(8)).toString();
        assertEquals("The 9th frequent itemsetlist must be correct", "[card1, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(9)).toString();
        assertEquals("The 10th frequent itemsetlist must be correct", "[card3, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(10)).toString();
        assertEquals("The 11th frequent itemsetlist must be correct", "[card1, card3]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(11)).toString();
        assertEquals("The 12th frequent itemsetlist must be correct", "[card1, card3]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(12)).toString();
        assertEquals("The 13th frequent itemsetlist must be correct", "[card2, card3]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(13)).toString();
        assertEquals("The 14th frequent itemsetlist must be correct", "[card3, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(14)).toString();
        assertEquals("The 15th frequent itemsetlist must be correct", "[card1, card2]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(15)).toString();
        assertEquals("The 16th frequent itemsetlist must be correct", "[card1, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(16)).toString();
        assertEquals("The 17th frequent itemsetlist must be correct", "[card1, card2]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(17)).toString();
        assertEquals("The 18th frequent itemsetlist must be correct", "[card1, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(18)).toString();
        assertEquals("The 19th frequent itemsetlist must be correct", "[card1, card2]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(19)).toString();
        assertEquals("The 20th frequent itemsetlist must be correct", "[card1, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(20)).toString();
        assertEquals("The 21st frequent itemsetlist must be correct", "[card2, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(21)).toString();
        assertEquals("The 22nd frequent itemsetlist must be correct", "[card1, card3, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(22)).toString();
        assertEquals("The 23rd frequent itemsetlist must be correct", "[card1, card2, card3]", frequentItemsetListString);*/
    }
}
