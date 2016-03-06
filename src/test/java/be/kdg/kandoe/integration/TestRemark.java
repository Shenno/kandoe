package be.kdg.kandoe.integration;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Remark;
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

import static org.junit.Assert.*;

/**
 * @author: Evelien
 * @versionon 1.0 29-2-201609:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestRemark {
    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    private Theme theme;
    private User user;
    private Organisation organisation;
    private Card card;

    @Before
    public void setup(){
        user = new User("firstname.lastname@kandoe.be", "password");
        user = userService.addUser(user);

        organisation= new Organisation("organisation");
        organisation.setOrganisator(user);
        organisation = userService.addOrganisation(organisation);

        String themeName = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();

        theme =  new Theme(themeName, description, isCommentaryAllowed, isAddingAdmited, user, organisation, tags);
        contentService.addTheme(theme);

        String cardName = "card name";
        card = new Card(cardName,theme);
        contentService.addCard(card);
    }

    @After
    public void tearDown(){
        userService.deleteOrganisation(organisation.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    public void addRemarkOnCard(){
        String text = "remark";
        Remark remark = new Remark(text,user,card);
        remark = contentService.addRemark(remark);

        assertNotNull(remark);
        assertEquals("Text must be correct",remark.getText(),text);
        assertEquals("User must be correct",remark.getUser(),user);
        assertEquals("Card mus be correct",remark.getCard(),card);
    }
    @Test(expected = ContentServiceException.class)
    public void testAddEmptyRemark(){
        Remark remark = null;
        contentService.addRemark(remark);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddRemarkEmptyText(){
        String text = "";
        Remark remark = new Remark(text,user,card);
        remark = contentService.addRemark(remark);
    }

    @Test(expected = ContentServiceException.class)
    public void addExistingRemarkSameCard(){
        String text = "remark";
        Remark remark = new Remark(text,user,card);
        Remark duplicateRemark = new Remark(text,user,card);
        remark = contentService.addRemark(remark);
        assertNotNull(remark);
        contentService.addRemark(duplicateRemark);
    }

    @Test
    public void addExistingRemarkDifferentCard(){
        Card card2 = new Card("card2",theme);
        card2 = contentService.addCard(card2);
        assertNotNull(card2);

        String text = "remark";
        Remark remark = new Remark(text,user,card);
        remark = contentService.addRemark(remark);
        assertNotNull(remark);

        Remark remark2 = new Remark(text,user,card2);
        contentService.addRemark(remark2);
        assertNotNull(remark2);

        //contentService.deleteCard(card2);
    }

    @Test(expected = ContentServiceException.class)
    public void addRemarkEmptyCard(){
        Card card = null;
        String text = "remark";
        Remark remark = new Remark(text,user,card);
        contentService.addRemark(remark);

    }
    @Test(expected = ContentServiceException.class)
    public void addRemarkEmptyUser(){
        User user = null;
        String text = "remark";
        Remark remark = new Remark(text,user,card);
        contentService.addRemark(remark);

    }
}
