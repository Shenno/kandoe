package be.kdg.kandoe.integration;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
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
public class TestTag {

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

        organisation = new Organisation("organisation",user);
        organisation = userService.addOrganisation(organisation);

        String name = "theme name";
        String description = "description of theme";
        List<Tag> tags = new ArrayList<>();
        theme = new Theme(name, description, user, organisation, tags);
        theme = contentService.addTheme(theme);

    }

    @After
    public void tearDown() {
        userService.deleteOrganisation(organisation.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    public void testAddTag() {
        String name = "tag name";

        Tag tag = new Tag(name,theme);
        tag = contentService.addTag(tag);

        assertNotNull(tag);
        assertEquals("Tag name must be correct", name, tag.getTagName());
        assertEquals("Theme must be correct",theme,tag.getTheme());

        Theme theme = contentService.getTheme(this.theme.getId());

        assertEquals("Theme must have 1 tag", 1, theme.getTags().size());
        assertEquals("Theme must have correct tag", tag.getTagName(), theme.getTags().get(0).getTagName());
    }

    @Test
    public void testAddTagUppercase() {
        String name = "TaG nAme";

        Tag tag = new Tag(name,theme);
        tag = contentService.addTag(tag);

        assertNotNull(tag);
        assertEquals("Tag name must be lowercase", name.toLowerCase(), tag.getTagName());
    }

    @Test(expected = ContentServiceException.class)
    public void testAddEmptyTag(){
        Tag tag = null;
        contentService.addTag(tag);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddTagEmptyName() {
        String name = "";
        Tag tag = new Tag(name,theme);
        contentService.addTag(tag);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddExistingTagSameTheme() {
        String name = "tag name";

        Tag tag = new Tag(name,theme);
        Tag duplicateTag = new Tag(name,theme);
        tag = contentService.addTag(tag);
        assertNotNull(tag);
        contentService.addTag(duplicateTag);
    }
    @Test
    public void testAddExistingTagDifferentTheme() {
        String themename = "theme name 2";
        String description = "description of theme 2";
        List<Tag> tags = new ArrayList<>();
        Theme theme2 = new Theme(themename, description, user, organisation, tags);
        theme2 = contentService.addTheme(theme2);

        String name = "tag name";

        Tag tag = new Tag(name,theme);
        Tag duplicateTag = new Tag(name,theme2);
        tag = contentService.addTag(tag);
        assertNotNull(tag);
        contentService.addTag(duplicateTag);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddTagEmptyTheme() {
        String name = "tag name";
        Tag tag = new Tag(name,null);
        contentService.addTag(tag);
    }

    @Test
    public void testFindAllTag(){
        String name = "tag name";
        Tag tag = new Tag(name,theme);
        tag = contentService.addTag(tag);

        String name2 = "tag name2";
        Tag tag2 = new Tag(name2,theme);
        tag2 = contentService.addTag(tag2);

        List<Tag> tags = contentService.findTags();
        assertEquals(tags.size(),2);
        assertEquals(tags.get(0).getTagName(),tag.getTagName());
        assertEquals(tags.get(1).getTagName(),tag2.getTagName());

    }

    @Test
    public void findTagById(){
        String name = "tag name";
        Tag tag = new Tag(name,theme);
        tag = contentService.addTag(tag);
        assertNotNull(tag);

        Tag t = contentService.getTag(tag.getId());
        assertEquals(t.getTagName(),tag.getTagName());
    }

    @Test
    public void findTagbyTheme(){
        String name = "tag name";
        Tag tag = new Tag(name,theme);
        tag = contentService.addTag(tag);
        assertNotNull(tag);

        List<Tag> tags = contentService.findTagByTheme(theme);
        assertEquals(tags.size(),1);
        assertEquals(tags.get(0).getTagName(),tag.getTagName());

    }

    @Test
    public void testUpdateTag(){
        String name = "tag name";
        Tag tag = new Tag(name,theme);
        tag = contentService.addTag(tag);
        assertNotNull(tag);

        tag = contentService.getTag(tag.getId());
        assertNotNull(tag);

        String newName = "new tag name";
        tag.setTagName(newName);

        Tag persistedTag = contentService.updateTag(tag);
        assertNotNull(persistedTag);
        assertEquals("Tag must be updated",newName, persistedTag.getTagName());

        Theme theme = contentService.getTheme(this.theme.getId());

        assertEquals("Theme must have 1 tag", 1, theme.getTags().size());
        assertEquals("Theme must have correct tag", persistedTag.getTagName(), theme.getTags().get(0).getTagName());
    }

    //TODO: Subtheme

}
