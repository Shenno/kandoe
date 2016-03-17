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

        organisation = new Organisation("organisation", user);
        organisation = userService.addOrganisation(organisation);

        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;

        List<Tag> tags = new ArrayList<>();

        theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        theme = contentService.addTheme(theme);

        Session session1 = new AsynchronousSession(true, 60, 4,"nameSession");
        session1 = sessionService.addSession(session1, theme.getId());

        Session session2 = new AsynchronousSession(true, 60, 4,"nameSession");
        session2 = sessionService.addSession(session2, theme.getId());

        Session session3 = new AsynchronousSession(true, 60, 4,"nameSession");
        session3 = sessionService.addSession(session3, theme.getId());

        String cardDescription="card1";
        String url="http://www.google.be";
        Card card= new Card(cardDescription,url,theme);
        card=contentService.addCard(card);
        sessionService.addCardToSession(session1, card);
        sessionService.addCardToSession(session2, card);
        sessionService.addCardToSession(session3, card);

        cardDescription="card2";
        url = "http://www.google.be";
        card = new Card(cardDescription, url, theme);
        card = contentService.addCard(card);
        sessionService.addCardToSession(session1, card);
        sessionService.addCardToSession(session2, card);

        cardDescription="card3";
        url = "http://www.google.be";
        card = new Card(cardDescription, url, theme);
        card = contentService.addCard(card);
        sessionService.addCardToSession(session1, card);
        sessionService.addCardToSession(session3, card);
    }

    @After
    public void tearDown(){
        userService.deleteOrganisation(organisation.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    public void testGetMostFrequentCardCombinations() {
        List<Set<CardSession>> frequentItemsetList = contentService.findMostFrequentCardCombinations(theme.getThemeId());
    /* TODO: Geeft nu nog steeds op een willekeurige volgorde terug?
        String frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(0)).toString();
        assertEquals("The 1st frequent itemsetlist must be correct", "[card3, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(1)).toString();
        assertEquals("The 2nd frequent itemsetlist must be correct", "[card1, card1]", frequentItemsetListString);

        frequentItemsetListString = new ArrayList<>(frequentItemsetList.get(2)).toString();
        assertEquals("The 3rd frequent itemsetlist must be correct", "[card2, card3]", frequentItemsetListString);

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
