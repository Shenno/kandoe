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
import be.kdg.kandoe.backend.services.exceptions.SessionServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @After
    public void tearDown() {
        userService.deleteOrganisation(organisation.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    public void testCreateAsyncSession() {
        // Create AsyncSession and add three users to it
        Session session = new AsynchronousSession(true, 60, 4);
        User user2 = new User("gebruikertje2", "pass");
        user2 = userService.addUser(user2);
        User user3 = new User("gebruikertje3", "pass");
        user3 = userService.addUser(user3);

        // Create cards and add to the session
        Card card1 = new Card("CardOne", theme);
        Card card2 = new Card("CardTwo", theme);
        Card card3 = new Card("CardThree", theme);
        card1 = contentService.addCard(card1);
        card2 = contentService.addCard(card2);
        card3 = contentService.addCard(card3);

        // Persist session
        session = sessionService.addSession(session, theme.getId());

        // Add users to session
        session = sessionService.addUserToSession(session, user.getUsername());
        session = sessionService.addUserToSession(session, user2.getUsername());
        session = sessionService.addUserToSession(session, user3.getUsername());

        // Add card to session
        session = sessionService.addCardToSession(session, card1);
        session = sessionService.addCardToSession(session, card2);
        session = sessionService.addCardToSession(session, card3);

        // Remove card to check the session will not break
        contentService.deleteCard(card1.getId());

        // Debugging feature
/*
        try {
            Thread.sleep(50000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        // Asserts
        session = sessionService.findSession(session.getId());
        theme = contentService.getTheme(theme.getId());
        user = userService.findUserById(user.getId());
        assertNotNull(session);
        assertNotNull(theme);
        assertNotNull(user);
        assertEquals("Card not removed", 2, contentService.findCardsByThemeId(theme.getId()).size());
        assertEquals("No 3 cardsessions found in session", 3, session.getCardSessions().size());
        assertEquals("Theme has more than 1 session", 1, theme.getSessions().size());
        assertEquals("Theme sessionId is not correct",theme.getSessions().get(0).getId(), session.getId());
        assertEquals("Playerlist must contain 3 user(s)", session.getUsers().size(),3);
        assertEquals("Current user must be user", user.getUserId(), session.getCurrentUser());
        //TODO split in multiple tests
        // Make move correct user
        CardSession cardSessionOne = sessionService.findCardSession(session.getCardSessions().get(0).getId());
        int distanceToCenter = cardSessionOne.getDistanceToCenter();
        sessionService.makeMove(cardSessionOne, user.getId());
        session = sessionService.findSession(session.getId());
        assertEquals("Distance to center of card should be one less", distanceToCenter - 1, session.getCardSessions().get(0).getDistanceToCenter());
        assertEquals("Current user must be user2", user2.getUserId(), session.getCurrentUser());

        // Make move incorrect user
        exception.expect(SessionServiceException.class);
        sessionService.makeMove(cardSessionOne, user.getId());

        // Make moves 3 more moves on same card to check gameOver
        sessionService.makeMove(cardSessionOne, user2.getId());
        sessionService.makeMove(cardSessionOne, user3.getId());
        sessionService.makeMove(cardSessionOne, user.getId());

        session = sessionService.findSession(session.getId());
        assertTrue(session.isGameOver());



    }

    @Test
    public void testCreateTwoAsyncSessionsSameTheme() {
        Session sessionOne = new AsynchronousSession(true, 60, 4);
        Session sessionTwo = new AsynchronousSession(false, 50, 4);
        sessionOne = sessionService.addSession(sessionOne, theme.getId());
        sessionTwo = sessionService.addSession(sessionTwo, theme.getId());
        assertNotNull(sessionOne);
        assertNotNull(sessionTwo);
        theme = contentService.getTheme(theme.getId());
        assertNotNull(theme);
        assertEquals(theme.getSessions().size(), 2);
    }

    @Test(expected = SessionServiceException.class)
    public void testAddSessionWithUnknownUser() {
        // Create AsyncSession and add three users to it
        Session session = new AsynchronousSession(true, 60, 4);
        session = sessionService.addUserToSession(session, "NonExistingUsername");
    }

    @Test
    public void testMakeMoveCorrectUser() {
        //TODO split tests

    }
}

