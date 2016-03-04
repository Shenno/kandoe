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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestSession {
    Theme theme;
    User user;
    Organisation organisation;

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
        List<Tag> tags = new ArrayList<>();
        theme = new Theme(name, description, user, organisation, tags);
        theme = contentService.addTheme(theme);

    }

    @After
    public void tearDown() {
        userService.deleteOrganisation(organisation.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    public void testCreateAsyncSession() {
        // Create AsyncSession and add three users to it
        Session session = new AsynchronousSession(true, 60);
        session.addUser(user);
        User user2 = new User("gebruikertje2", "pass");
        user2 = userService.addUser(user2);
        session.addUser(user2);
        User user3 = new User("gebruikertje3", "pass");
        user3 = userService.addUser(user3);
        session.addUser(user3);

        // Persist session
        session = sessionService.addSession(session, theme.getId());

        // Create cards and add to the session
        Card card1 = new Card("CardOne", theme);
        Card card2 = new Card("CardTwo", theme);
        Card card3 = new Card("CardThree", theme);
        card1 = contentService.addCard(card1);
        card2 = contentService.addCard(card2);
        card3 = contentService.addCard(card3);
        //TODO Card adden -> delete op 1 of andere manier users

       /* sessionService.addCardToSession(card1);
        sessionService.addCardToSession(card2);
        sessionService.addCardToSession(card3);*/


        // Assert
        assertNotNull(session);
        theme = contentService.getTheme(theme.getId());
        user = userService.findUserById(user.getId());
        assertNotNull(theme);
        assertEquals("Theme has 1 session", 1, theme.getSessions().size());
        assertEquals("Theme sessionId is correct",theme.getSessions().get(0).getId(), session.getId());
        assertEquals("Playerlist must contain 3 user(s)", session.getUsers().size(),3);


    }

    @Test
    public void testCreateTwoAsyncSessionsSameTheme() {
        Session sessionOne = new AsynchronousSession(true, 60);
        Session sessionTwo = new AsynchronousSession(false, 50);
        sessionOne = sessionService.addSession(sessionOne, theme.getId());
        sessionTwo = sessionService.addSession(sessionTwo, theme.getId());
        assertNotNull(sessionOne);
        assertNotNull(sessionTwo);
        theme = contentService.getTheme(theme.getId());
        assertNotNull(theme);
        assertEquals(theme.getSessions().size(), 2);
    }

    @Test
    public void test() {

    }
}

