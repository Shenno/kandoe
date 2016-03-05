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
import {Observable, } from "../../node_modules/rxjs/Observable";
import {createSession} from "../entity/createSession";
import {SessionService} from "../service/sessionService";

@Component({
    selector: 'view-session',
    template: `<p>Hallo daar</p>
    `,
    encapsulation: ViewEncapsulation.None
})
/*<select [(ng-model)]="objValue1">
 <option *ng-for="#o of objArray" [value]="o">{{o.name}}</option>
 </select>*/

export class SessionComponent {

    private userService: UserService;
    private contentService: ContentService;
    private sessionService: SessionService;
    private themes : Theme[] = [];
    private theme: Theme = Theme.createEmptyTheme();
    private cards: Card[] = null;
    private currentUser = null;

    public constructor(userService: UserService, contentService: ContentService, sessionService: SessionService) {
        this.userService = userService;
        this.contentService = contentService;
        this.sessionService = sessionService;
        document.title = 'Kandoe sessie';
        this.userService.getMyDetails().subscribe((user:User) => {
            this.currentUser = user;
        });
    }





}