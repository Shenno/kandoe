package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
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
        assertEquals("Adding must be admitted", theme.isAddingAdmitted(), isAddingAdmited);
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
    public void testAddExistingThemeSameOrganisation() {
        String name = "theme name";
        String description = "description of theme";

        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,user,organisation,tags);
        Theme duplicatetheme = new Theme(name,description,user,organisation,tags);
        theme = contentService.addTheme(theme);
        assertNotNull(theme);
        contentService.addTheme(duplicatetheme);
    }
    /*@Test
    public void testAddExistingThemeDifferentOrganisation() {
        Organisation organisation2 = new Organisation("organisation2");
        organisation2 = userService.addOrganisation(organisation2);
        assertNotNull(organisation2);

        String name = "theme name";
        String description = "description of theme";
        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,user,organisation,tags);
        theme = contentService.addTheme(theme);
        assertNotNull(theme);

        Theme theme2 = new Theme(name,description,user,organisation2,tags);
        theme2 = contentService.addTheme(theme2);
        assertNotNull(theme2);

        userService.deleteOrganisation(organisation2.getId());

    }*/

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
    @Test
    public void testFindAllThemes(){
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();
        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        theme = contentService.addTheme(theme);

        String name2 = "theme name 2";
        String description2 = "description of theme 2";
        boolean isCommentaryAllowed2 = true;
        boolean isAddingAdmited2 = true;
        List<Tag> tags2 = new ArrayList<>();
        Theme theme2 = new Theme(name2,description2,isCommentaryAllowed2,isAddingAdmited2,user,organisation,tags2);
        theme2 = contentService.addTheme(theme2);

        assertNotNull(theme);
        assertNotNull(theme2);

        List<Theme> themes = contentService.findThemes();
        assertEquals(themes.size(),3);
        assertEquals(themes.get(1).getThemeName(),theme.getThemeName());
        assertEquals(themes.get(2).getThemeName(),theme2.getThemeName());

    }

    @Test
    public void findThemeById(){
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();
        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        theme = contentService.addTheme(theme);
        assertNotNull(theme);

        Theme t = contentService.getTheme(theme.getId());
        assertEquals(t.getThemeName(),theme.getThemeName());
    }

    @Test
    public void findThemeByOrganisation(){
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();
        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        theme = contentService.addTheme(theme);
        assertNotNull(theme);

        List<Theme> themes = contentService.findThemesByOrganisation(organisation);
        assertEquals(themes.size(),1);
        assertEquals(themes.get(0).getThemeName(),theme.getThemeName());

    }

    @Test
    public void testUpdateTheme(){
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;

        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        theme = contentService.addTheme(theme);
        assertNotNull(theme);

        theme = contentService.getTheme(theme.getId());
        assertNotNull(theme
        );
        String newName = "new theme name";
        theme.setThemeName(newName);

        Theme persistedTheme = contentService.updateTheme(theme);
        assertNotNull(persistedTheme);
        assertEquals("Theme is not correct updated",persistedTheme.getThemeName(),newName);


    }
}
