package be.kdg.kandoe.integration;

import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestUsers {

    @Autowired
    private UserService userService;

    @Test
    public void testRegisterUser() {
        String userName="user@kandoe.be";
        String password="kandoe";
        String firstName="user";
        String lastName="kandoe";

        User user = new User(userName,password);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        user = userService.addUser(user);

        assertNotNull(user);
        assertEquals("Username must be correct",user.getUsername(),userName);
        assertEquals("Firstname must be correct",user.getFirstName(),firstName);
        assertEquals("Lastname must be correct",user.getLastName(),lastName);
    }
}
