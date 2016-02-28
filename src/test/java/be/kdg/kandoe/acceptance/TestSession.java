package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
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
    Session session;
    Theme theme;
    User user;
    Organisation organisation;

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        user = new User("firstname.lastname@kandoe.be", "password");
        user = userService.addUser(user);

        organisation = new Organisation("organisation");
        organisation = userService.addOrganisation(organisation);

        String name = "theme name";
        String description = "description of theme";
        List<Tag> tags = new ArrayList<>();
        theme = new Theme(name, description, user, organisation, tags);
        theme = contentService.addTheme(theme);

    }

    @After
    public void tearDown() {
        userService.deleteUser(user.getId());
        userService.deleteOrganisation(organisation.getId());
    }

    @Test
    public void testCreateAsyncSession() {
        Session session = new AsynchronousSession(true, 60);
        }

    }

