package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        userService.addOrganisation(organisation);
        assertNotNull(organisation);
        assertEquals(organisation.getId(), userService.getOrganisationById(organisation.getId()).getId());
    }

    @Test(expected = UserServiceException.class)
    public void testEmptyOrganisation() {
        String name = "";
        Organisation organisation = new Organisation(name);
        userService.addOrganisation(organisation);
    }

    @Test(expected = UserServiceException.class)
    public void testDuplicateNameOrganisation() {
        String name = "Duplicate name";
        Organisation organisation = new Organisation(name);
        Organisation organisationDupl = new Organisation(name);
        userService.addOrganisation(organisation);
        userService.addOrganisation(organisationDupl);
    }

    @Test
    public void testDeleteOrganisation(){
        String name = "organisation name";
        Organisation organisation = new Organisation(name);
        organisation =userService.addOrganisation(organisation);
        assertNotNull(organisation);
        userService.deleteOrganisation(organisation.getId());
        organisation = userService.getOrganisationById(organisation.getId());
        assertNull(organisation);

    }
}
