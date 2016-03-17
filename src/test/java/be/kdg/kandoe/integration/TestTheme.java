package be.kdg.kandoe.integration;

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

        organisation = new Organisation("organisation", user);
        organisation = userService.addOrganisation(organisation);
    }

    @After
    public void tearDown(){
        userService.deleteOrganisation(organisation.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    public void testAddTheme() {
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;

        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);

        User user2 = new User("firstname2.lastname2@kandoe.be", "password2");
        user2 = userService.addUser(user2);

        theme.addOrganisator(user2);

        theme = contentService.addTheme(theme);

        assertNotNull(theme);
        assertTrue("User must be correct", theme.getOrganisators().contains(user));
        assertEquals("Description must be correct", theme.getDescription(), description);
        assertEquals("Commentary must be allowed", theme.isCommentaryAllowed(), isCommentaryAllowed);
        assertEquals("Adding must be admitted", theme.isAddingAdmitted(), isAddingAdmited);
        assertEquals("Organisation must be correct", theme.getOrganisation(), organisation);
        assertEquals("There must be one tag", theme.getTags().size(), tags.size());
        assertEquals("There must be two organisators", theme.getOrganisators().size(), 2);
        assertEquals("First organisator must be correct", theme.getOrganisators().get(0), user);
        assertEquals("Second organisator must be correct", theme.getOrganisators().get(1), user2);

        for(int i = 0; i < theme.getOrganisators().size(); i++) {
            List<Theme> themesOfOrganisator = contentService.findThemesByOrganisatorId(theme.getOrganisators().get(i).getId());
            assertEquals("User should only be organisator of 1 theme", 1, themesOfOrganisator.size());
            assertEquals("Theme should be the same", theme.getId(), themesOfOrganisator.get(0).getId());
        }

        organisation = userService.getOrganisationByName("organisation");
        List<Theme> themes = organisation.getThemes();
        assertEquals("There must be one theme in organisation", 1, themes.size());
        assertEquals("Organisation must have the right theme", theme.getThemeName(), themes.get(0).getThemeName());
        assertEquals("Organisation must have one organisator", theme.getOrganisators().size(), themes.get(0).getOrganisators().size());
        assertEquals("Organisation must have the right organisator", theme.getOrganisators().get(0).getUsername(), themes.get(0).getOrganisators().get(0).getUsername());
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
    public void addTestDuplicateOrganisators(){
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name,description,isCommentaryAllowed,isAddingAdmited,user,organisation,tags);
        theme.addOrganisator(user);
        theme = contentService.addTheme(theme);

        assertNotNull(theme);
        int i = 0;
        for (User u: theme.getOrganisators()){
            if (u.getUsername()== user.getUsername()){
                System.out.println(u.getUsername());
                i++;
            }
        }
        assertEquals("Organisators are duplicate",i,1);

    }

   /* @Test
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
    }*/
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
        assertEquals(themes.get(2).getThemeName(),theme.getThemeName());
        assertEquals(themes.get(3).getThemeName(),theme2.getThemeName());

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
