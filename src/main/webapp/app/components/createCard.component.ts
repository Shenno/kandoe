import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {ContentService} from "../service/contentService";
import {Card} from "../entity/card";
import {RouteParams} from "angular2/router";
/**
 * Component for creating card
 */
@Component({
    selector: 'create-card',
    templateUrl: 'app/partials_html/createCard.component.html',
    encapsulation: ViewEncapsulation.None
})
export class CreateCardComponent {
    private router:Router;
    private contentService: ContentService;

    private card: Card = Card.createEmptyCard();

    public constructor(contentService: ContentService, router: Router, routeParam: RouteParams) {
        this.router = router;
        this.contentService = contentService;
        document.title = 'Maak kaartje aan';
        this.card.themeId = +routeParam.params["themeId"];
    }

    public onSubmit(): void {
        this.contentService.addCard(this.card);
    }
    public onCancel(event): void {
        event.preventDefault();
        this.router.navigate(['/Theme', {themeId: this.card.themeId}]);
    }
}