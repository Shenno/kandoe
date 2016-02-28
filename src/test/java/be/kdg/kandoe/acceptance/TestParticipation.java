package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.session.Participation;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestParticipation {
    private User user;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Before
    public void setup() {
        user = new User("firstname.lastname@kandoe.be", "123");
        user = userService.addUser(user);
    }

    @After
    public void tearDown() {
        userService.deleteUser(user.getId());
    }

    @Test
    public void testHibernateMapping() {
        User testuser = new User("een@test.be", "123");
        Participation p = new Participation();
        testuser.addParticipation(p);
        p.setUser(testuser);
        testuser = userService.addUser(testuser);
        p = sessionService.addParticipation(p);
        System.out.println(testuser.getParticipations().size());
    }

    @Test
    public void testAddParticipation() {
        Participation participation = new Participation();
        Boolean isRegistered = false;
        Boolean isOnTurn = false;
        participation.setIsRegistered(isRegistered);
        participation.setIsOnTurn(isOnTurn);

        assertNotNull(participation);
        assertEquals("isRegistered must be correct", isRegistered, participation.isRegistered());
        assertEquals("isOnTurn must be correct", isOnTurn, participation.isOnTurn());
    }

    @Test(expected = NullPointerException.class)
    public void testParticipationNullFields() {
        Boolean isRegistered = null;
        Boolean isOnTurn = null;

        Participation participation = new Participation();
        participation.setIsRegistered(isRegistered);
        participation.setIsOnTurn(isOnTurn);

        user.addParticipation(participation);
        userService.addUser(user);
    }

    @Test(expected = NullPointerException.class)
    public void testNullParticipation() {
        Participation participation = null;
        user.addParticipation(participation);
        userService.addUser(user);
    }
}
