package be.kdg.kandoe.integration;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.apache.xpath.operations.Or;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestOrganisation {

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setup() {
        user = new User("firstname.lastname@kandoe.be", "password");
        user = userService.addUser(user);
    }

    @After
    public void tearDown() {
        user = userService.findUserById(user.getId());
        List<Organisation> organisations = user.getOrganisations();
        for (Organisation o: organisations){
            userService.deleteOrganisation(o.getId());
        }
        userService.deleteUser(user.getUserId());
    }

    @Test
    public void testAddOrganisation() {
        String name = "Organisation";
        Organisation organisation = new Organisation(name, user);
        organisation = userService.addOrganisation(organisation);
        user = userService.findUserById(user.getId());
        assertNotNull(organisation);
        assertEquals("Number of Organisations must be correct", user.getOrganisations().size(), 1);
        assertEquals("Name must be correct",user.getOrganisations().get(0).getName(), organisation.getName());
        assertEquals("Organisator must be correct",user.getOrganisations().get(0).getId(), organisation.getId());
    }

    @Test (expected = UserServiceException.class)
    public void testAddOrganisationNull(){
        Organisation organisation = null;
        userService.addOrganisation(organisation);
    }

    @Test(expected = UserServiceException.class)
    public void testAddOrganisationEmptyName() {
        String name = "";
        Organisation organisation = new Organisation(name, user);
        userService.addOrganisation(organisation);
    }

    @Test(expected = UserServiceException.class)
    public void testAddOrganisationEmptyUser() {
        String name = "Organisation";
        Organisation organisation = new Organisation(name, null);
        userService.addOrganisation(organisation);
    }

    @Test(expected = UserServiceException.class)
    public void testAddOrganisationDuplicateName() {
        String name = "Organisation";
        Organisation organisation = new Organisation(name, user);
        Organisation organisationDuplicate = new Organisation(name, user);
        userService.addOrganisation(organisation);
        userService.addOrganisation(organisationDuplicate);

        userService.deleteOrganisation(organisation.getId());
    }

    @Test
    public void testDeleteOrganisation(){
        String name = "organisation name";
        Organisation organisation = new Organisation(name, user);
        organisation = userService.addOrganisation(organisation);
        assertNotNull("Organisation must be correct",organisation);

        user = userService.findUserById(user.getId());
        int size = user.getOrganisations().size();

        userService.deleteOrganisation(organisation.getId());
        Organisation organisationDeleted = userService.getOrganisationById(organisation.getId());
        user = userService.findUserById(user.getId());
        assertNull("Organsation must be deleted",organisationDeleted);
        assertNotNull("User must be correct",user);
        assertEquals("Size organisations must be correct",user.getOrganisations().size(),size-1);
        assertFalse("Organisation may not be in list", user.getOrganisations().contains(organisation));
    }

}
