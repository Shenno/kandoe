package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.persistence.impl.OrganisationRepositoryImpl;
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

public class TestCard {
    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    private Theme theme;
    private User user;
    private Organisation organisation;

    @Before
    public void setup(){
        user = new User("firstname.lastname@kandoe.be", "password");
        user = userService.addUser(user);

        organisation= new Organisation("organisation");
        organisation = userService.addOrganisation(organisation);

        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();

       theme =  new Theme(name, description, isCommentaryAllowed, isAddingAdmited, user, organisation, tags);
       contentService.addTheme(theme);
    }

    @After
    public void tearDown(){
        userService.deleteUser(user.getId());
        userService.deleteOrganisation(organisation.getId());
    }


    @Test
    public void testAddCard(){
        String description="This is a card";
        String url="http://www.google.be";
        Card card= new Card(description,url,theme);
        card=contentService.addCard(card);
        assertNotNull(card);
        assertEquals("Description must be correct",description,card.getText());
        assertEquals("URL must be correct",url,card.getImageURL());

    }

    @Test(expected = ContentServiceException.class)
    public void testAddCardEmptyName() {
        String description="";
        String url="http://www.google.be";
        Card card= new Card(description,url,theme);
        contentService.addCard(card);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddEmptyCard() {
        Card card = null;
        contentService.addCard(card);
    }

    @Test(expected = ContentServiceException.class)
    public void testNoRealUrl() {
        String description="This is a Card";
        String url="bafbzbzizro";
        Card card= new Card(description,url,theme);
        card=contentService.addCard(card);
    }

    @Test(expected = ContentServiceException.class)
    public void testAddExistingCard() {
        String description = "This is a card";
        String url = "http://www.google.be";
        Card card= new Card(description,url,theme);
        Card duplicateCard = new Card(description,url,theme);
        contentService.addCard(card);
        contentService.addCard(duplicateCard);
    }

    @Test(expected = ContentServiceException.class)
    public void testEmptyTheme() {
        String description = "This is some card";
        String url = "http://www.google.com";

        Theme emptyTheme= null;

        Card card = new Card(description, url,emptyTheme);
        card=contentService.addCard(card);
    }
}
