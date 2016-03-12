package be.kdg.kandoe.integration;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
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

        organisation = new Organisation("organisation");
        organisation.setOrganisator(user);
        organisation = userService.addOrganisation(organisation);

        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;

        List<Tag> tags = new ArrayList<>();

        theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        theme = contentService.addTheme(theme);

        Session session1 = new AsynchronousSession(true, 60, 4);
        session1 = sessionService.addSession(session1, theme.getId());

        Session session2 = new AsynchronousSession(true, 60, 4);
        session2 = sessionService.addSession(session2, theme.getId());

        Session session3 = new AsynchronousSession(true, 60, 4);
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
        List<Set<String>> frequentItemsetList = contentService.findMostFrequentCardCombinations(theme.getThemeId());

        assertEquals("The 1st frequent itemsetlist must be correct", "[card1]", frequentItemsetList.get(0).toString());
        assertEquals("The 2nd frequent itemsetlist must be correct", "[card2]", frequentItemsetList.get(1).toString());
        assertEquals("The 3rd frequent itemsetlist must be correct", "[card3]", frequentItemsetList.get(2).toString());
        assertEquals("The 4th frequent itemsetlist must be correct", "[card1, card2]", frequentItemsetList.get(3).toString());
        assertEquals("The 5th frequent itemsetlist must be correct", "[card1, card3]", frequentItemsetList.get(4).toString());
        assertEquals("The 6th frequent itemsetlist must be correct", "[card2, card3]", frequentItemsetList.get(5).toString());
        assertEquals("The 7th frequent itemsetlist must be correct", "[card1, card2, card3]", frequentItemsetList.get(6).toString());
    }
}
