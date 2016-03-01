package be.kdg.kandoe.integration;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        user = userService.addUser(user);
    }

    @After
    public void tearDown() {
        userService.deleteUser(user.getUserId());
    }

    @Test
    public void testAddOrganisationNoUser() {
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

    @Test(expected = UserServiceException.class)
    public void testDeleteOrganisationNoOrganisator(){
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
        organisation.setOrganisator(user);
        organisation = userService.addOrganisation(organisation);
        user = userService.findUserById(user.getId());
        assertNotNull(organisation);
        assertEquals(organisation.getOrganisator(), user);
        assertEquals(user.getOrganisations().size(), 1);
        assertEquals(user.getOrganisations().get(0).getName(), organisation.getName());
        assertEquals(user.getOrganisations().get(0).getId(), organisation.getId());
    }

    @Test
    public void testRemoveOrganisationCheckOrganisator() {
        String name = "RemoveOrganisation";
        Organisation organisation = new Organisation(name);
        organisation.setOrganisator(user);
        //Bidirectional linking
        organisation = userService.addOrganisation(organisation);
        user = userService.findUserById(user.getId());
        int size = user.getOrganisations().size();

        //Bidirectional removing
        userService.deleteOrganisation(organisation.getId());

        organisation = userService.getOrganisationById(organisation.getId());
        user = userService.findUserById(user.getId());
        assertNull(organisation); //Make sure organisation is removed
        assertNotNull(user); // Make sure user is still alive
        assertEquals(user.getOrganisations().size(), size-1); // Make sure organisation is removed TODO better check
    }
}
