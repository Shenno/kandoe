package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.OrganisationService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import be.kdg.kandoe.backend.services.exceptions.OrganisationServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by   Shenno Willaert
 * Date         22/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.acceptance
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestOrganisation {

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
 /*       User user = new User("firstname.lastname@kandoe.be", "password");
        user = userService.addUser(user);
        int userId = user.getUserId();

        Organisation organisation = new Organisation("organisation");
        //organisation = userService.addOrganisation(organisation,userId);

        String name = "theme name";
        String description = "description of theme";
        boolean isCommentaryAllowed = true;
        boolean isAddingAdmited = true;
        List<Tag> tags = new ArrayList<>();

        Theme theme = new Theme(name, description, isCommentaryAllowed, isAddingAdmited, user, organisation, tags);
        contentService.addTheme(userId, theme);*/

    }

    @Test
    public void testAddOrganisation() {
        String name = "Organisation 1";
        Organisation organisation = new Organisation(name);
        organisationService.addOrganisation(organisation);
        assertNotNull(organisation);
        assertEquals(organisation.getId(), organisationService.getOrganisationById(organisation.getId()).getId());
    }

    @Test(expected = OrganisationServiceException.class)
    public void testEmptyOrganisation() {
        String name = "";
        Organisation organisation = new Organisation(name);
        organisationService.addOrganisation(organisation);
    }

    @Test(expected = OrganisationServiceException.class)
    public void testDuplicateNameOrganisation() {
        String name = "Duplicate name";
        Organisation organisation = new Organisation(name);
        Organisation organisationDupl = new Organisation(name);
        organisationService.addOrganisation(organisation);
        organisationService.addOrganisation(organisationDupl);
    }
}
