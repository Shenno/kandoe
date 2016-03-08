import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Card} from '../entity/card';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {ContentService} from "../service/contentService";
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";


@Component({
    selector: 'detail-card',
    templateUrl: 'app/partials_html/detailCard.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export class DetailCardComponent {

    private router: Router;

    private contentService: ContentService;

    private card: Card = Card.createEmptyCard();

    public constructor(contentService: ContentService, routeParam:RouteParams, router:Router) {
        this.router = router;
        this.contentService = contentService;
        contentService.getCard(routeParam.params["cardId"]).subscribe((card:Card) => {
            this.card = card;
            document.title = 'Kaart: ' + this.card.text;
        })
    }

    public backToTheme(id:string): void {
        this.router.navigate(['/Theme',{themeId: id }]);
    }
}