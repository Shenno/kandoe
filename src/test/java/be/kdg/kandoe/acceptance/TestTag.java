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

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        User user = new User("firstname.lastname@kandoe.be", "password");
        user = userService.addUser(user);

        Organisation organisation= new Organisation("organisation");

        int userId = user.getUserId();
        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        int organisationId = organisation.getId();

        List<Tag> tags = new ArrayList<>();
        Theme theme =  new Theme(name, description, isCommentaryAllowed, isAddingAdmited, user, organisation, tags);
        contentService.addTheme(userId, theme);

    }

    @Test
    public void testAddTag() {
        String name = "tag name";

        Tag tag = contentService.addTag(name, 1);

        assertNotNull(tag);
        assertEquals("Tag name must be correct", name, tag.getName());
        //controleren dat tag in lijst van tags van thema zit.
        // assertEquals("Theme must be correct", tag.getTheme().getId().intValue(), 1);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddTagEmptyName() {
        String name = "";

        contentService.addTag(name, 1);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddTagEmptyTheme() {
        String name = "tag name";

        contentService.addTag(name, 0);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddExistingTag() {
        String name = "tag name";

        Tag tag = contentService.addTag(name, 1);
        assertNotNull(tag);
        contentService.addTag(name, 1);
    }
}
