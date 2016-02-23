package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
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
public class TestTag {
    Theme theme;

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        User user = new User("firstname.lastname@kandoe.be", "password");
        user = userService.addUser(user);
        int userId = user.getUserId();

        Organisation organisation= new Organisation("organisation");
        //organisation = userService.(organisation,userId);

        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();

        theme =  new Theme(name, description, isCommentaryAllowed, isAddingAdmited, user, organisation, tags);
//        contentService.addTheme(theme);

    }

    @Test
    public void testAddTag() {
        String name = "tag name 2";
        Tag tag = new Tag(name);
        //tag = contentService.addTag(theme.getId(),tag);
        tag = contentService.addTag(1,tag);

        assertNotNull(tag);
        assertEquals("Tag name must be correct", name, tag.getTagName());
        //controleren dat tag in lijst van tags van thema zit.
        // assertEquals("Theme must be correct", tag.getTheme().getId().intValue(), 1);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddTagEmptyName() {
        String name = "";
        Tag tag = new Tag(name);
        //tag = contentService.addTag(theme.getId(),tag);
        tag = contentService.addTag(1,tag);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddTagNull() {
        String name = "";
        Tag tag = null;
        //tag = contentService.addTag(theme.getId(),tag);
        tag = contentService.addTag(1,tag);
    }
    @Test(expected = ContentServiceException.class)
    public void testAddTagEmptyTheme() { //TODO verdere uitwerking
        String name = "tag name";
        Tag tag = new Tag(name);
        contentService.addTag(0,tag);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddExistingTag() {
        String name = "tag name";
        Tag tag = new Tag(name);
        //tag = contentService.addTag(theme.getId(),tag);
        tag = contentService.addTag(1,tag);
        assertNotNull(tag);
        //tag = contentService.addTag(theme.getId(),tag);
        tag = contentService.addTag(1,tag);    }
}
