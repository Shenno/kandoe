package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestTheme {

    private User user;
    private Organisation organisation;

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        user = new User("firstname.lastname@kandoe.be", "password");
        user = userService.addUser(user);

        organisation = new Organisation("organisation4");
        organisation = userService.addOrganisation(organisation);

    }

   /* @After
    public void tearDown(){
        userService.deleteUser(user.getId());
        userService.deleteOrganisation(organisation.getId());
    }*/

    @Test
    public void testAddTheme() {
        String name = "theme name";
        String description = "description of theme";
        Boolean isCommentaryAllowed = true;
        Boolean isAddingAdmited = true;

        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,user,organisation,tags);
//        theme = contentService.addTheme(theme);

        /*assertNotNull(theme);
        assertTrue("User must be correct", theme.getOrganisators().contains(user));
        assertEquals("Description must be correct", theme.getDescription(), description);
        assertEquals("Commentary must be allowed", theme.isCommentaryAllowed(), isCommentaryAllowed);
        assertEquals("Adding must be admitted", theme.isAddingAdmited(), isAddingAdmited);
        assertEquals("Organisation must be correct", theme.getOrganisation(), organisation);
        assertEquals("There must be one tag", theme.getTags().size(), tags.size());
        assertEquals("Tag must be correct", theme.getTags().size(),tags.size());*/
    }

   /* @Test(expected = ContentServiceException.class)
    public void testAddThemeIncorrectUser() {
        String emailAdress = "incorrectEmail";
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;

        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,user,organisation,tags);
        //contentService.addTheme(emailAdress, name, description, isCommentaryAllowed, isAddingAdmited, organisation, tags);
    }*//*
    @Test(expected = ContentServiceException.class)
    public void testAddThemeEmptyName() {
        String emailAdress = "firstname.lastname@kandoe.be";
        String name = "";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        String organisation = "Organisation";

        List<String> tags = new ArrayList<>();
        String tag1 = "tag1";
        tags.add(tag1);

        //contentService.addTheme(emailAdress, name, description, isCommentaryAllowed, isAddingAdmited, organisation, tags);
    }*/
/*
    @Test(expected = ContentServiceException.class)
    public void testAddThemeEmptyOrganisation() {
        String emailAdress = "firstname.lastname@kandoe.be";
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        String organisation = "";

        List<String> tags = new ArrayList<>();
        String tag1 = "tag1";
        tags.add(tag1);

       // contentService.addTheme(emailAdress, name, description, isCommentaryAllowed, isAddingAdmited, organisation, tags);
    }*/
/*
    @Test(expected = ContentServiceException.class)
    public void testAddExistingTheme() {
        String name = "theme name";
        String description = "description of theme";
        Boolean isCommentaryAllowed = true;
        Boolean isAddingAdmited = true;

        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,user,organisation,tags);
        theme = contentService.addTheme(theme);
        assertNotNull(theme);
        theme = contentService.addTheme(theme);
    }*/
}
