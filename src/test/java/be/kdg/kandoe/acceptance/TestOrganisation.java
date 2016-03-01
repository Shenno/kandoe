package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

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

    @Autowired
    private ContentService contentService;

    User user;

    @Before
    public void setup() {
        user = new User("TestGebruiker", "pwd");
        userService.addUser(user);
    }

    @After
    public void tearDown() {
        userService.deleteUser(user.getUserId());
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
        organisation = userService.addOrganisation(organisation);
        assertNotNull(organisation);
        userService.deleteOrganisation(organisation.getId());
        organisation = userService.getOrganisationById(organisation.getId());
        assertNull(organisation);
    }

    @Test
    public void testAddOrganisationWithOrganisator() {
        String name = "OrganisationOrg";
        Organisation organisation = new Organisation(name);
        organisation = userService.addOrganisationWithOrganisator(organisation, user.getUserId());
        user = userService.findUserById(user.getId());
        assertNotNull(organisation);
        assertEquals(organisation.getOrganisator(), user);
        assertEquals(user.getOrganisations().size(), 1);
        assertEquals(user.getOrganisations().get(0).getName(), organisation.getName());
        assertEquals(user.getOrganisations().get(0).getId(), organisation.getId());
    }

  /*  @Test
    public void testRemoveOrganisationCheckOrganisator() {
        String name = "RemoveOrganisation";
        Organisation organisation = new Organisation(name);
        organisation = userService.addOrganisationWithOrganisator(organisation, user.getUserId());
        user = userService.findUserById(user.getId());
        userService.deleteOrganisation(organisation.getId());
        organisation = userService.addOrganisation(organisation);

        organisation = userService.getOrganisationById(organisation.getId());
        assertNull(organisation);
    }*/
}
