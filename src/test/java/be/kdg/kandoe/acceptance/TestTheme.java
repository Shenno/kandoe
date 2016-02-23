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

        organisation = new Organisation("organisation");
        organisation = userService.addOrganisation(organisation);
    }

    @After
    public void tearDown(){
        userService.deleteUser(user.getId());
        userService.deleteOrganisation(organisation.getId());
    }

    @Test
    public void testAddTheme() {
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;

        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        theme = contentService.addTheme(theme);

        assertNotNull(theme);
        assertTrue("User must be correct", theme.getOrganisators().contains(user));
        assertEquals("Description must be correct", theme.getDescription(), description);
        assertEquals("Commentary must be allowed", theme.isCommentaryAllowed(), isCommentaryAllowed);
        assertEquals("Adding must be admitted", theme.isAddingAdmited(), isAddingAdmited);
        assertEquals("Organisation must be correct", theme.getOrganisation(), organisation);
        assertEquals("There must be one tag", theme.getTags().size(), tags.size());
        assertEquals("Tag must be correct", theme.getTags().size(),tags.size());
    }

    @Test(expected = ContentServiceException.class)
    public void testAddEmptyTheme(){
       Theme theme = null;
        contentService.addTheme(theme);
    }
    @Test(expected = ContentServiceException.class)
    public void testAddThemeEmptyName() {
        String name = "";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;

        List<Tag> tags= new ArrayList<>();

        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        contentService.addTheme(theme);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddExistingTheme() {
        String name = "theme name";
        String description = "description of theme";

        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,user,organisation,tags);
        Theme duplicattheme = new Theme(name,description,user,organisation,tags);
        theme = contentService.addTheme(theme);
        assertNotNull(theme);
        contentService.addTheme(duplicattheme);
    }
   @Test(expected = ContentServiceException.class)
    public void testAddThemeEmptyOrganisation() {
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,null,tags);
        contentService.addTheme(theme);

    }

    @Test(expected = ContentServiceException.class)
    public void testAddThemeEmptyUser() {
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,null,organisation,tags);
        contentService.addTheme(theme);
    }

    @Test
    public void testDeleteTheme(){
        String name = "theme name";
        String description = "description of theme";

        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,user,organisation,tags);
        theme = contentService.addTheme(theme);
        assertNotNull(theme);
        contentService.deleteTheme(theme.getId());
        theme = contentService.getTheme(theme.getId());
        assertNull(theme);
    }
}
