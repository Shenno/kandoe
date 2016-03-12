import {Component} from "angular2/core";
import {ViewEncapsulation} from "angular2/core";
import {FORM_DIRECTIVES} from "angular2/common";
import {CORE_DIRECTIVES} from "angular2/common";
import {ContentService} from "../service/contentService";
import {RouteParams} from "angular2/router";

@Component({
    selector: 'apriori',
    templateUrl: 'app/partials_html/apriori.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})

export class AprioriComponent {
    private contentService: ContentService;
    private cardCombinations: string[] = [];

    public constructor(contentService: ContentService, routeParam: RouteParams) {
        document.title = 'Dashboard';

        contentService.getMostFrequentCardCombinations(routeParam.params["themeId"]).subscribe((cardCombinations: string[]) => {
            this.cardCombinations = cardCombinations;
        });
    }
}