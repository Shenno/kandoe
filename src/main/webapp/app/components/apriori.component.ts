import {Component} from "angular2/core";
import {ViewEncapsulation} from "angular2/core";
import {FORM_DIRECTIVES} from "angular2/common";
import {CORE_DIRECTIVES} from "angular2/common";
import {ContentService} from "../service/contentService";
import {RouteParams} from "angular2/router";
import {CardCombination} from "../entity/cardCombination";

@Component({
    selector: 'apriori',
    templateUrl: 'app/partials_html/apriori.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})

export class AprioriComponent {
    private contentService: ContentService;
    private cardCombinations: CardCombination[] = []; //a card combination is an array of cards

    public constructor(contentService: ContentService, routeParam: RouteParams) {

        contentService.getMostFrequentCardCombinations(routeParam.params["themeId"]).subscribe((cardCombinations: CardCombination[]) => {
            this.cardCombinations = cardCombinations;
        });
    }
}