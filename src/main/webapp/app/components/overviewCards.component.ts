import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {ContentService} from "../service/contentService";
import {Card} from "../entity/card";

@Component({
    selector: 'overview-card',
    templateUrl: 'app/partials_html/overviewCards.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})

export class OverviewCardComponent {
    private router:Router;
    private contentService:ContentService;
    private cards:Card[] = [];

    public constructor(contentService:ContentService, routeParam:RouteParams, router:Router) {
        this.router = router;
        this.contentService = contentService;
        contentService.getCardsByThemeId(routeParam.get("themeId")).subscribe((cards:Card[]) => {
            this.cards = cards;
            document.title = 'Kaartjes';
        })
    }
    public addCard(): void {
        this.router.navigate(['/CreateCard']);
    }
}