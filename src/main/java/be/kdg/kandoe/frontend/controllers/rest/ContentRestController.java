package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.frontend.controllers.resources.content.CardResource;
import be.kdg.kandoe.frontend.controllers.resources.content.TagResource;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/themes")
public class ContentRestController {

    private ContentService contentService;
    MapperFacade mapperFacade;

    @Autowired
    public ContentRestController(ContentService contentService, MapperFacade mapperFacade)
    {
        this.contentService = contentService;
        this.mapperFacade = mapperFacade;
    }

   /* @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ThemeResource> findUserById(@PathVariable int userId)
    {
        Theme theme = contentService.getMainThemesByOrganisationId;
        return new ResponseEntity<>(new UserResource(user), HttpStatus.OK);
    } */

    @RequestMapping(value = "/{themeId}", method = RequestMethod.GET)
    public ResponseEntity<ThemeResource> findMainThemeById(@PathVariable int themeId)
    {     //
        Theme foundTheme = contentService.getTheme(themeId);
        ThemeResource themeResource = mapperFacade.map(foundTheme, ThemeResource.class);
        return new ResponseEntity<>(themeResource, HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<ThemeResource> createMainTheme(@RequestBody ThemeResource themeResource)
    {
        //Theme themeToAdd = new Theme(themeResource.getName(), themeResource.getDescription(), themeResource.isCommentaryAllowed(), themeResource.isAddingAdmitted(), null, null, null);
        Theme addedTheme = contentService.addTheme(mapperFacade.map(themeResource, Theme.class));
        return new ResponseEntity<>(mapperFacade.map(addedTheme, ThemeResource.class), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{themeId}",method = RequestMethod.PUT)
    public ResponseEntity<ThemeResource> updateMainTheme(@PathVariable("themeId") Integer themeId, @RequestBody ThemeResource themeResource)
    {
        Theme addedTheme = contentService.updateTheme(mapperFacade.map(themeResource, Theme.class));
        return new ResponseEntity<>(mapperFacade.map(addedTheme, ThemeResource.class), HttpStatus.OK);
    }

    @RequestMapping(value="/{mainThemeId}/tags", method = RequestMethod.POST)
    public ResponseEntity<TagResource> addTagToMainTheme(@PathVariable int mainThemeId, @RequestBody TagResource tagResource)
    {
        Tag t = tagResource.toDOM();
        t.setTheme(contentService.getTheme(mainThemeId));
        Tag tag = contentService.addTag(t);
        return new ResponseEntity<>(new TagResource(tag), HttpStatus.OK);
    }

    @RequestMapping(value="/cards", method = RequestMethod.POST)
    public ResponseEntity<CardResource> addCard(@RequestBody CardResource cardResource) {
        contentService.addCard(mapperFacade.map(cardResource, Card.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/cards/{cardId}", method = RequestMethod.GET)
    public ResponseEntity<CardResource> findCardById(@PathVariable int cardId) {
        Card card = contentService.getCard(cardId);
        System.out.println(card.getTheme().getId() + " /// " + card.getTheme().getDescription());
        return new ResponseEntity<CardResource>(mapperFacade.map(card, CardResource.class), HttpStatus.OK);
    }

   /* @RequestMapping(value = "/{mainThemeId}/tags", method = RequestMethod.GET)
    public ResponseEntity<TagResource> findAllTagsByMainThemeId(@PathVariable int mainThemeId)
    {
        Theme foundTheme = contentService.getTag(themeId);
        return new ResponseEntity<>(new ThemeResource(foundTheme), HttpStatus.OK);
    }*/




    /*@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> createUser(@Valid @RequestBody User user)
    {
        User u = userService.addUser(user);
        return new ResponseEntity<>(userResourceAssembler.toResource(user), HttpStatus.OK);
    }*/
}
