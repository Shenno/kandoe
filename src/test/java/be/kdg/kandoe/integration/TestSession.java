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
import org.springframework.beans.factory.annotation.Value;
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
    User user1;
    User user2;
    User user3;
    Organisation organisation;
    List<String> usernames;
    Integer organisatorId;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Before
    public void setup() {
        user1 = new User("user1@kandoe.be", "password");
        user1 = userService.addUser(user1);
        organisatorId = user1.getId();

        user2 = new User("user2@kandoe.be", "password");
        user2 = userService.addUser(user2);

        user3 = new User("user3@kandoe.be", "password");
        user3 = userService.addUser(user3);

        usernames = new ArrayList<>();
        usernames.add(user1.getUsername());
        usernames.add(user2.getUsername());
        usernames.add(user3.getUsername());

        organisation = new Organisation("Organisation 1", user1);
        organisation = userService.addOrganisation(organisation);

        String name = "theme name";
        String description = "description of theme";
        List<Tag> tags = new ArrayList<>();

        theme = new Theme(name, description, user1, organisation, tags);
        theme = contentService.addTheme(theme);
    }

    @After
    public void tearDown() {
        userService.deleteOrganisation(organisation.getId());
        userService.deleteUser(user1.getId());
        userService.deleteUser(user2.getId());
        userService.deleteUser(user3.getId());

    }

    //Successcenario
    @Test
    public void testCreateSession() {
        String nameSession = "SessionName";

        // Create AsyncSession and add three users to it
        Session session = new AsynchronousSession(organisatorId, true, 60, 4, nameSession);

        // Create cards and add to the session
        Card card1 = new Card("CardOne","", theme);
        Card card2 = new Card("CardTwo","", theme);
        Card card3 = new Card("CardThree","", theme);
        card1 = contentService.addCard(card1);
        card2 = contentService.addCard(card2);
        card3 = contentService.addCard(card3);
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);


        // Persist session
        session = sessionService.addSession(session, theme.getId(), cards, usernames);

        session = sessionService.findSession(session.getId());
        assertNotNull(session);
        assertEquals("Session name must be correct",session.getNameSession(),nameSession);

        Theme retrievedTheme = contentService.getTheme(theme.getId());
        assertEquals("Incorrect theme added to session", retrievedTheme.getId(), theme.getId());

        List<User> usersOfSession = session.getUsers();
        assertEquals("Three users should be added to session", 3, usersOfSession.size());
        assertEquals("Incorrect user added to session", usernames.get(0), usersOfSession.get(0).getUsername());
        assertEquals("Incorrect user added to session", usernames.get(1), usersOfSession.get(1).getUsername());
        assertEquals("Incorrect user added to session", usernames.get(2), usersOfSession.get(2).getUsername());


        //List<CardSession> cardsOfSession = session.getCardSessions();

        /*
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

        // Debugging feature*/
/*
        try {
            Thread.sleep(50000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        // Asserts
       /* session = sessionService.findSession(session.getId());
        theme = contentService.getTheme(theme.getId());
        user = userService.findUserById(user.getId());
        assertNotNull(session);
        assertEquals("NameSession must be correct",session.getNameSession(),nameSession);
        assertNotNull(theme);
        assertNotNull(user);

        //TODO prioriteit van kaart checken
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

        // Add remark
        String remarkText = "Dit is een opmerking";
        session = sessionService.addRemarkToSession(session, user.getUsername(), remarkText);
        assertEquals("Remark should be added", 1, session.getRemarks().size());
        assertEquals("Remark content should be correct", remarkText, session.getRemarks().get(0).getText());

        // Make move incorrect user
        exception.expect(SessionServiceException.class);
        sessionService.makeMove(cardSessionOne, user.getId());



        // Make moves 3 more moves on same card to check gameOver
        assertTrue(!session.isGameOver());
        sessionService.makeMove(cardSessionOne, user2.getId());
        sessionService.makeMove(cardSessionOne, user3.getId());
        sessionService.makeMove(cardSessionOne, user.getId());
        session = sessionService.findSession(session.getId());
        assertTrue(session.isGameOver());*/
    }


    @Test
    public void testCreateSessionNoCards() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een sessie moet kaarten bevatten.");
        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, theme.getId(), null, usernames);
    }

    @Test
    public void testCreateSessionTooFewCards() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een sessie moet minimum 2 en maximum 24 kaarten bevatten.");

        Card card1 = new Card("CardOne","", theme);
        card1 = contentService.addCard(card1);
        List<Card> cards = new ArrayList<>();
        cards.add(card1);

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, theme.getId(), cards, usernames);
    }

    @Test
    public void testCreateSessionNoParticipants() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een sessie moet deelnemers bevatten.");

        Card card1 = new Card("CardOne","", theme);
        card1 = contentService.addCard(card1);

        Card card2 = new Card("CardTwo","", theme);
        card2 = contentService.addCard(card2);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, theme.getId(), cards, null);
    }

    @Test
    public void testCreateSessionOfThemeWithDifferentOrganisator() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Je kan geen sessie starten van een thema waar je geen organisator van bent");

        Card card1 = new Card("CardOne","", theme);
        card1 = contentService.addCard(card1);

        Card card2 = new Card("CardTwo","", theme);
        card2 = contentService.addCard(card2);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        List<Tag> tags = new ArrayList<>();

        Theme differentTheme = new Theme("anotherTheme", "anotherDescription", user2, organisation, tags);
        differentTheme = contentService.addTheme(differentTheme);

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, differentTheme.getId(), cards, usernames);
    }

    @Test
    public void testCreateSessionCardOfDifferentTheme() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een kaart van een ander thema dan het thema van de sessie kan niet worden toegevoegd.");

        Card card1 = new Card("CardOne","", theme);
        card1 = contentService.addCard(card1);

        Card card2 = new Card("CardTwo","", theme);
        card2 = contentService.addCard(card2);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        List<Tag> tags = new ArrayList<>();

        Theme differentTheme = new Theme("anotherTheme", "anotherDescription", user1, organisation, tags);
        differentTheme = contentService.addTheme(differentTheme);

        Card card3 = new Card("CardDifferentTheme", "", differentTheme);
        cards.add(card3);

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, theme.getId(), cards, usernames);
    }

    @Test
    public void testCreateSessionTooFewParticipants() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een sessie moet minimaal 2 deelnemers hebben.");

        Card card1 = new Card("CardOne","", theme);
        card1 = contentService.addCard(card1);

        Card card2 = new Card("CardTwo","", theme);
        card2 = contentService.addCard(card2);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        List<String> tooFewUsernames = new ArrayList<>();
        tooFewUsernames.add(usernames.get(0));

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, theme.getId(), cards, tooFewUsernames);
    }

    @Test
    public void testCreateSessionNoName() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("De sessie moet een naam hebben.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, null);
        sessionService.addSession(session, theme.getId(), null, usernames);
    }

    @Test
    public void testCreateSessionEmptyName() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("De sessie moet een naam hebben.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "");
        sessionService.addSession(session, theme.getId(), null, usernames);
    }

    @Test
    public void testCreateSessionTooFewCircles() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een Kandoecirkel moet minimum 3 en maximum 8 schillen hebben.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 2, "Session");
        sessionService.addSession(session, theme.getId(), null, usernames);
    }

    @Test
    public void testCreateSessionTooManyCircles() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een Kandoecirkel moet minimum 3 en maximum 8 schillen hebben.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 9, "Session");
        sessionService.addSession(session, theme.getId(), null, usernames);
    }

    @Test
    public void testCreateSessionUnexistingTheme() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Ongeldig thema.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 8, "Session");
        sessionService.addSession(session, 9999, null, usernames);
    }

    @Test
    public void testCreateSessionUnexistingParticipant() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Deelnemer kon niet toegevoegd worden aan de sessie");

        Card card1 = new Card("CardOne","", theme);
        card1 = contentService.addCard(card1);

        Card card2 = new Card("CardTwo","", theme);
        card2 = contentService.addCard(card2);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        List<String> unexistingParticipant = new ArrayList<String>(usernames);
        unexistingParticipant.add("ikbestaniet@kandoe.be");

        Session session = new AsynchronousSession(organisatorId, true, 60, 8, "Session");
        sessionService.addSession(session, theme.getId(), cards, unexistingParticipant);
    }

    @Test
    public void testCreateSessionTooManyCards() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een sessie moet minimum 2 en maximum 24 kaarten bevatten.");

        List<Card> cards = new ArrayList<>();

        for(int i = 0; i < 26; i++) {
            Card card = new Card("Card " + i,"", theme);
            card = contentService.addCard(card);
            cards.add(card);
        }


        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, theme.getId(), cards, usernames);
    }


   /* @Test
    public void testCreateTwoAsyncSessionsSameTheme() {
        String nameSession = "SessionName";
        Session sessionOne = new AsynchronousSession(true, 60, 4,nameSession);
        Session sessionTwo = new AsynchronousSession(false, 50, 4,nameSession);
       //TODO: sessionOne = sessionService.addSession(sessionOne, theme.getId());
        //TODO: sessionTwo = sessionService.addSession(sessionTwo, theme.getId());
        assertNotNull(sessionOne);
        assertNotNull(sessionTwo);
        theme = contentService.getTheme(theme.getId());
        assertNotNull(theme);
        assertEquals(theme.getSessions().size(), 2);
    }

    @Test(expected = SessionServiceException.class)
    public void testAddSessionWithUnknownUser() {
        String nameSession = "SessionName";
        // Create AsyncSession and add three users to it
        Session session = new AsynchronousSession(true, 60, 4, nameSession);
        session = sessionService.addUserToSession(session, "NonExistingUsername");
    }

    @Test
    public void testMakeMoveCorrectUser() {
        //TODO split tests

    }
    @Test(expected = SessionServiceException.class)
    public void testSessionNameNotNull(){
        String nameSession = "";
        Session session = new AsynchronousSession(true, 60, 4,nameSession);
        //TODO: sessionService.addSession(session, theme.getId());

    }
    @Test
    public void testGetSessionByUserId(){
        String nameSession = "SessionName";
        // Create AsyncSession and add three users to it
        Session session = new AsynchronousSession(true, 60, 4,nameSession);

        // Create cards and add to the session
        Card card1 = new Card("CardOne","", theme);
        Card card2 = new Card("CardTwo","", theme);
        Card card3 = new Card("CardThree","", theme);
        card1 = contentService.addCard(card1);
        card2 = contentService.addCard(card2);
        card3 = contentService.addCard(card3);

        // Persist session
        //TODO: session = sessionService.addSession(session, theme.getId());

        // Add users to session
        session = sessionService.addUserToSession(session, user.getUsername());
        session = sessionService.addUserToSession(session, user2.getUsername());
        session = sessionService.addUserToSession(session, user3.getUsername());

        // Add card to session
        session = sessionService.addCardToSession(session, card1);
        session = sessionService.addCardToSession(session, card2);
        session = sessionService.addCardToSession(session, card3);

        assertTrue("User moet in sessie zitten",session.getUsers().contains(user));

        List<Session> sessions = sessionService.findSessionByUserId(user.getId());
        assertEquals("Sessie moet in lijst bij user zitten ",sessions.get(0).getId(),session.getId());
    }*/
}

