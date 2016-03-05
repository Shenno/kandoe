import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Organisation} from '../entity/organisation';
import {User} from "../entity/user";
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";
import {Theme} from "../entity/theme";
import {ContentService} from "../service/contentService";
import {Card} from "../entity/card";

@Component({
    selector: 'create-session',
    templateUrl: 'app/partials_html/createSession.component.html',
    encapsulation: ViewEncapsulation.None
})
/*<select [(ng-model)]="objValue1">
 <option *ng-for="#o of objArray" [value]="o">{{o.name}}</option>
 </select>*/

export class CreateSessionComponent {

    private userService: UserService;
    private contentService: ContentService;
    private themes : Theme[] = [];
    private theme: Theme = Theme.createEmptyTheme();
    private cards: Card[] = [];

    public constructor(userService: UserService, contentService: ContentService) {
        this.userService = userService;
        this.contentService = contentService;
        document.title = 'Start een nieuwe Kandoe sessie!';
        this.userService.getMyDetails().subscribe((user:User) => {
            this.contentService.getThemesByOrganisatorId(user.id.toString()).subscribe((theme:Theme[]) =>{ this.themes = theme})
        });
    }

    public onSelectTheme(selectedThemeId:number) {
        alert(selectedThemeId);
        this.contentService.getCardsByThemeId(selectedThemeId.toString()).subscribe((cards:Card[]) => {this.cards = cards});
    }

}