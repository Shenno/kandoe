package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.session.Participation;
import be.kdg.kandoe.backend.dom.user.User;
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
        boolean isRegistered = false;
        boolean isOnTurn = false;

        Participation participation = new Participation(isRegistered, isOnTurn);
        participation = sessionService.addParticipation(participation);

        assertNotNull(participation);
        assertEquals("isRegistered must match", isRegistered, participation.isRegistered());
        assertEquals("isOnTurn must match", isOnTurn, participation.isOnTurn());
    }


}
