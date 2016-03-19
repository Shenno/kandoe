package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.CardSession;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import be.kdg.kandoe.frontend.controllers.resources.content.CardCombinationResource;
import be.kdg.kandoe.frontend.controllers.resources.content.CardResource;
import be.kdg.kandoe.frontend.controllers.resources.content.TagResource;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.CardSessionResource;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/themes")
public class ContentRestController {

    private ContentService contentService;
    private MapperFacade mapperFacade;
    private Logger logger = Logger.getLogger(ContentRestController.class);

    @Autowired
    public ContentRestController(ContentService contentService, MapperFacade mapperFacade) {
        this.contentService = contentService;
        this.mapperFacade = mapperFacade;
    }

    @RequestMapping(value = "/{themeId}", method = RequestMethod.GET)
    public ResponseEntity<ThemeResource> findMainThemeById(@PathVariable int themeId) {
        Theme foundTheme = contentService.getTheme(themeId);
        ThemeResource themeResource = mapperFacade.map(foundTheme, ThemeResource.class);
        logger.info("Theme " + themeId + " has been found.");
        return new ResponseEntity<>(themeResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/organisator/{organisatorId}", method = RequestMethod.GET)
    public ResponseEntity<List<ThemeResource>> findThemesByOrganisatorId(@PathVariable int organisatorId) {
        List<Theme> foundThemes = contentService.findThemesByOrganisatorId(organisatorId);
        List<ThemeResource> themeResources = foundThemes.stream().map(t -> mapperFacade.map(t, ThemeResource.class)).collect(Collectors.toList());
        return new ResponseEntity<List<ThemeResource>>(themeResources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ThemeResource> createMainTheme(@RequestBody ThemeResource themeResource) {
        //Theme themeToAdd = new Theme(themeResource.getName(), themeResource.getDescription(), themeResource.isCommentaryAllowed(), themeResource.isAddingAdmitted(), null, null, null);
        try {
            Theme addedTheme = contentService.addTheme(mapperFacade.map(themeResource, Theme.class));

            for (String tagName : themeResource.getTags()) {
                Tag tag = contentService.addTag(new Tag(tagName, addedTheme));
                addedTheme.addTag(tag);
            }

            return new ResponseEntity<>(mapperFacade.map(addedTheme, ThemeResource.class), HttpStatus.CREATED);
        } catch (ContentServiceException ex) {
            String errorMessage = ex.getMessage();
            themeResource.setErrorMessage(errorMessage);
            return new ResponseEntity<>(themeResource, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{themeId}", method = RequestMethod.PUT)
    public ResponseEntity<ThemeResource> updateMainTheme(@PathVariable("themeId") Integer themeId, @RequestBody ThemeResource themeResource, @AuthenticationPrincipal User user) {
        try {
            if (themeResource.getOrganisatorId().intValue() == user.getId()) {
                Theme updatedTheme = contentService.updateTheme(mapperFacade.map(themeResource, Theme.class));

                for (String tagName : themeResource.getTags()) {
                    try {
                        contentService.findTagByTagNameByTheme(tagName, updatedTheme);
                    } catch (ContentServiceException ex) {
                        Tag tag = contentService.addTag(new Tag(tagName, updatedTheme));
                        updatedTheme.addTag(tag);
                    }
                }

                return new ResponseEntity<>(mapperFacade.map(updatedTheme, ThemeResource.class), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ContentServiceException ex) {
            String errorMessage = ex.getMessage();
            themeResource.setErrorMessage(errorMessage);
            return new ResponseEntity<>(themeResource, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{mainThemeId}/tags", method = RequestMethod.POST)
    public ResponseEntity<TagResource> addTagToMainTheme(@PathVariable int mainThemeId, @RequestBody TagResource tagResource) {
        Tag t = tagResource.toDOM();
        t.setTheme(contentService.getTheme(mainThemeId));
        Tag tag = contentService.addTag(t);
        return new ResponseEntity<>(new TagResource(tag), HttpStatus.OK);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    public ResponseEntity<CardResource> addCard(@RequestBody CardResource cardResource) {
        Card addedCard = contentService.addCard(mapperFacade.map(cardResource, Card.class));
        return new ResponseEntity<>(mapperFacade.map(addedCard, CardResource.class), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{themeId}/cards", method = RequestMethod.GET)
    public ResponseEntity<List<CardResource>> findCardsByThemeId(@PathVariable int themeId) {

        List<Card> cards = contentService.findCardsByThemeId(themeId);
        List<CardResource> cardResources = cards.stream().map(c -> mapperFacade.map(c, CardResource.class)).collect(Collectors.toList());
        return new ResponseEntity<List<CardResource>>(cardResources, HttpStatus.OK);
    }

    @RequestMapping(value = "{themeId}/cardCombinations", method = RequestMethod.GET)
    public ResponseEntity<List<CardCombinationResource>> findMostFrequentCardCombinations(@PathVariable int themeId) {

        List<Set<CardSession>> cardCombinationSets = contentService.findMostFrequentCardCombinations(themeId);
        List<CardCombinationResource> cardCombinations = new ArrayList<>();

        if (cardCombinationSets != null) {

            for (Set<CardSession> cardCombinationSet : cardCombinationSets) {
                List<CardSessionResource> cardResources = new ArrayList<>();

                for (CardSession cardSession : cardCombinationSet) {
                    CardSessionResource cardResource = mapperFacade.map(cardSession, CardSessionResource.class);
                    cardResources.add(cardResource);
                }

                CardCombinationResource cardCombinationResource = new CardCombinationResource();
                cardCombinationResource.setCards(cardResources);
                cardCombinations.add(cardCombinationResource);
            }
        }
        return new ResponseEntity<>(cardCombinations, HttpStatus.OK);
    }

    @RequestMapping(value = "/cards/{cardId}", method = RequestMethod.GET)
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
