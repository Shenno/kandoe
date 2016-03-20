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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestSession {
    Theme theme;
    User user1;
    User user2;
    User user3;
    Card card1;
    Organisation organisation;
    List<String> usernames;
    List<Card> cards;
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

        cards = new ArrayList<>();
        Card card1 = new Card("CardOne","", theme);
        Card card2 = new Card("CardTwo","", theme);
        card1 = contentService.addCard(card1);
        card2 = contentService.addCard(card2);
        cards.add(card1);
        cards.add(card2);
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

        List<CardSession> cardsOfSession = session.getCardSessions();
        assertEquals("Two cards should be added to session", 2, cardsOfSession.size());
        assertEquals("Incorrect card text", "CardOne", cardsOfSession.get(0).getCard());
        assertEquals("Incorrect card text", "CardTwo", cardsOfSession.get(1).getCard());

        assertEquals("Game should not be over", false, session.isGameOver());
        assertEquals("Current player should be first player added", user1.getUserId(), session.getCurrentUser());
    }

    //Successcenario
    @Test
    public void testSimulateRealisticPlaySession() {
        Session session = new AsynchronousSession(organisatorId, true, 60, 6, "a name");

        List<Card> moreCards = new ArrayList<Card>(cards);
        for(int i = 0; i < 7; i++) {
            Card newCard = new Card("dummy" + i, "", theme);
            contentService.addCard(newCard);
            moreCards.add(newCard);
        }

        session = sessionService.addSession(session, theme.getId(), moreCards, usernames);
        List<CardSession> cardsOfSession = session.getCardSessions();
        int amountOfCards = cardsOfSession.size();
        Random rng = new Random();

        CardSession movedCard = null;
        while(!session.isGameOver()) {
            movedCard = cardsOfSession.get(rng.nextInt(amountOfCards));
            int currentDistanceToCenter = movedCard.getDistanceToCenter();
            session = sessionService.makeMove(movedCard, session.getCurrentUser());
            assertEquals("Kaart moet 1 schil naar het midden verplaatst zijn", currentDistanceToCenter-1, movedCard.getDistanceToCenter());
        }
        assertEquals("Eén kaart moet in het centrum liggen van de cirkel",0, movedCard.getDistanceToCenter());
    }

    //Successcenario
    @Test
    public void testMoveCard() {
        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "a name");

        session = sessionService.addSession(session, theme.getId(), cards, usernames);

        List<CardSession> cardsOfSession = session.getCardSessions();
        CardSession cardToMove = cardsOfSession.get(0);
        int distanceToCenterOfCardBeforeMove = cardToMove.getDistanceToCenter();

        Integer idOfNextPlayer = session.getUsers().get(1).getUserId();

        sessionService.makeMove(cardToMove, session.getCurrentUser());
        session = sessionService.findSession(session.getId());
        int currentDistanceToCenterOfMovedCard = session.getCardSessions().get(0).getDistanceToCenter();

        assertEquals("Kaart zou 1 schil dichter bij het centrum moeten liggen", distanceToCenterOfCardBeforeMove - 1, currentDistanceToCenterOfMovedCard);
        assertEquals("Speler aan beurt zou naar de volgende speler moeten verschoven zijn", user2.getId(), idOfNextPlayer);
        assertEquals("Sessie mag nog niet over zijn", false, session.isGameOver());
    }

    //Successcenario
    @Test
    public void testMoveCardEndGameCondition() {

        int amountOfCircles = 4;

        Session session = new AsynchronousSession(organisatorId, true, 60, amountOfCircles, "a name");

        session = sessionService.addSession(session, theme.getId(), cards, usernames);

        List<CardSession> cardsOfSession = session.getCardSessions();
        CardSession cardToMove = cardsOfSession.get(0);


        for(int i = 0; i < amountOfCircles; i++) {
            session = sessionService.makeMove(cardToMove, session.getCurrentUser());
            cardToMove = session.getCardSessions().get(0);
            if(cardToMove.getDistanceToCenter() != 0) {
                assertEquals("Session should not be over yet", false, session.isGameOver());
            } else {
                assertEquals("A card has reached the center of the circle. Session should be over", true, session.isGameOver());
            }
        }
    }

    @Test
    public void testMoveCardWhenGameOver() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Zet kan niet uitgevoerd worden omdat de sessie gedaan is.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "a name");
        session.setGameOver(true);
        session = sessionService.addSession(session, theme.getId(), cards, usernames);

        List<CardSession> cardsOfSession = session.getCardSessions();
        CardSession cardToMove = cardsOfSession.get(0);

        session = sessionService.makeMove(cardToMove, session.getCurrentUser());
    }

    @Test
    public void testMoveCardTooFar() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Deze zet kan niet uitgevoerd worden. Kaart bevindt zich al in het centrum van de cirkel.");

        int amountOfCircles = 4;

        Session session = new AsynchronousSession(organisatorId, true, 60, amountOfCircles, "a name");

        session = sessionService.addSession(session, theme.getId(), cards, usernames);

        List<CardSession> cardsOfSession = session.getCardSessions();
        CardSession cardToMove = cardsOfSession.get(0);


        for(int i = 0; i < amountOfCircles + 3; i++) {
            session = sessionService.makeMove(cardToMove, session.getCurrentUser());
            session.setGameOver(false);
            sessionService.updateSession(session);
        }
    }

    @Test
    public void testMoveCardWrongUser() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Deze zet kan niet uitgevoerd worden omdat de gebruiker niet aan beurt is.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "a name");

        session = sessionService.addSession(session, theme.getId(), cards, usernames);

        List<CardSession> cardsOfSession = session.getCardSessions();
        CardSession cardToMove = cardsOfSession.get(0);

        session = sessionService.makeMove(cardToMove, session.getCurrentUser()+1);
    }

    //Successcenario
    @Test
    public void testAddRemarkToSession() {
        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "a name");

        session = sessionService.addSession(session, theme.getId(), cards, usernames);

        assertEquals("Lijst van opmerkingen moet leeg zijn bij het starten van een nieuwe sessie", 0, session.getRemarks().size());

        String remarkToAddText = "Opmerking";

        sessionService.addRemarkToSession(session, user1.getUsername(), remarkToAddText);

        assertEquals("Opmerking moet staan tussen lijst van opmerkingen", 1, session.getRemarks().size());
        assertEquals("Tekst van de opmerking moet juist zijn", remarkToAddText, session.getRemarks().get(0).getText());
    }

    @Test
    public void testAddRemarkToSessionByNonParticipant() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Gebruiker die niet deelneemt aan de sessie of geen organisator is van de sessie mag geen opmerkingen plaatsen.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "a name");

        List<String> notAllUsenames = new ArrayList<>();
        notAllUsenames.add(user1.getUsername());
        notAllUsenames.add(user2.getUsername());

        session = sessionService.addSession(session, theme.getId(), cards, notAllUsenames);

        String remarkToAddText = "Opmerking";

        sessionService.addRemarkToSession(session, user3.getUsername(), remarkToAddText);
    }

    @Test
    public void testAddRemarkToSessionByNonParticipatingOrganisator() {

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "a name");

        List<String> notAllUsenames = new ArrayList<>();
        notAllUsenames.add(user3.getUsername());
        notAllUsenames.add(user2.getUsername());

        session = sessionService.addSession(session, theme.getId(), cards, notAllUsenames);

        String remarkToAddText = "Opmerking";

        session = sessionService.addRemarkToSession(session, user1.getUsername(), remarkToAddText);
        assertEquals("Organisator van een sessie moet altijd een opmerking kunnen plaatsen.", 1, session.getRemarks().size());
    }

    @Test
    public void testMoveCardWithUnexistingUser() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Deze zet kan niet uitgevoerd worden omdat de gebruiker niet bestaat.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "a name");

        session = sessionService.addSession(session, theme.getId(), cards, usernames);

        List<CardSession> cardsOfSession = session.getCardSessions();
        CardSession cardToMove = cardsOfSession.get(0);

        session = sessionService.makeMove(cardToMove, 0);
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

        List<Card> tooFewCards = new ArrayList<>();
        tooFewCards.add(card1);

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, theme.getId(), tooFewCards, usernames);
    }

    @Test
    public void testCreateSessionNoParticipants() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een sessie moet deelnemers bevatten.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, theme.getId(), cards, null);
    }

    @Test
    public void testCreateSessionOfThemeWithDifferentOrganisator() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Je kan geen sessie starten van een thema waar je geen organisator van bent");

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
        sessionService.addSession(session, theme.getId(), cards, usernames);
    }

    @Test
    public void testCreateSessionEmptyName() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("De sessie moet een naam hebben.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "");
        sessionService.addSession(session, theme.getId(), cards, usernames);
    }

    @Test
    public void testCreateSessionTooFewCircles() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een Kandoecirkel moet minimum 3 en maximum 8 schillen hebben.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 2, "Session");
        sessionService.addSession(session, theme.getId(), cards, usernames);
    }

    @Test
    public void testCreateSessionTooManyCircles() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een Kandoecirkel moet minimum 3 en maximum 8 schillen hebben.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 9, "Session");
        sessionService.addSession(session, theme.getId(), cards, usernames);
    }

    @Test
    public void testCreateSessionUnexistingTheme() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Ongeldig thema.");

        Session session = new AsynchronousSession(organisatorId, true, 60, 8, "Session");
        sessionService.addSession(session, 999999, cards, usernames);
    }

    @Test
    public void testCreateSessionUnexistingParticipant() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Deelnemer kon niet toegevoegd worden aan de sessie");

        List<String> unexistingParticipant = new ArrayList<String>(usernames);
        unexistingParticipant.add("ikbestaniet@kandoe.be");

        Session session = new AsynchronousSession(organisatorId, true, 60, 8, "Session");
        sessionService.addSession(session, theme.getId(), cards, unexistingParticipant);
    }

    @Test
    public void testCreateSessionTooManyCards() {
        expectedException.expect(SessionServiceException.class);
        expectedException.expectMessage("Een sessie moet minimum 2 en maximum 24 kaarten bevatten.");

        List<Card> tooManyCards = new ArrayList<>();

        for(int i = 0; i < 26; i++) {
            Card card = new Card("Card " + i,"", theme);
            card = contentService.addCard(card);
            tooManyCards.add(card);
        }

        Session session = new AsynchronousSession(organisatorId, true, 60, 4, "SessionName");
        sessionService.addSession(session, theme.getId(), tooManyCards, usernames);
    }


    @Test
    public void testCreateTwoAsyncSessionsSameTheme() {
        String nameSession = "SessionName";
        Session sessionOne = new AsynchronousSession(organisatorId, true, 60, 4,nameSession);
        Session sessionTwo = new AsynchronousSession(organisatorId, true, 50, 4,nameSession);
        TODO: sessionOne = sessionService.addSession(sessionOne, theme.getId(), cards, usernames);
        TODO: sessionTwo = sessionService.addSession(sessionTwo, theme.getId(), cards, usernames);
        assertNotNull(sessionOne);
        assertNotNull(sessionTwo);
        theme = contentService.getTheme(theme.getId());
        assertNotNull(theme);
        assertEquals(theme.getSessions().size(), 2);
    }

    @Test
    public void testGetSessionByUserId(){
        String nameSession = "SessionName";
        Session session = new AsynchronousSession(organisatorId, true, 60, 4,nameSession);

        session = sessionService.addSession(session, theme.getId(), cards, usernames);

        List<Session> sessions = sessionService.findSessionByUserId(user1.getId());
        assertEquals("Sessie moet in lijst bij user zitten ",sessions.get(0).getId(),session.getId());
    }
}

