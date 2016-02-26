package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/themes")
public class ContentRestController {

    private ContentService contentService;

    @Autowired
    public ContentRestController(ContentService contentService)
    {
        this.contentService = contentService;
    }

   /* @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ThemeResource> findUserById(@PathVariable int userId)
    {
        Theme theme = contentService.getMainThemesByOrganisationId;
        return new ResponseEntity<>(new UserResource(user), HttpStatus.OK);
    } */

    @RequestMapping(value = "/{themeId}", method = RequestMethod.GET)
    public ResponseEntity<ThemeResource> findMainThemeById(@PathVariable int themeId)
    {
        Theme foundTheme = contentService.getTheme(themeId);
        return new ResponseEntity<>(new ThemeResource(foundTheme), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<ThemeResource> createMainTheme(@RequestBody ThemeResource themeResource)
    {
        Theme themeToAdd = new Theme(themeResource.getName(), themeResource.getDescription(), themeResource.isCommentaryAllowed(), themeResource.isAddingAdmitted(), null, null, null);
        Theme t = contentService.addTheme(themeToAdd);
        return new ResponseEntity<>(new ThemeResource(t), HttpStatus.OK);
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> createUser(@Valid @RequestBody User user)
    {
        User u = userService.addUser(user);
        return new ResponseEntity<>(userResourceAssembler.toResource(user), HttpStatus.OK);
    }*/
}
