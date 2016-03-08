import {Component, ViewEncapsulation, Input} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {User} from "../entity/user";
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";
import {ContentService} from "../service/contentService";
import {Observable } from "../../node_modules/rxjs/Observable";
import {SessionService} from "../service/sessionService";
import {SessionActive} from "../entity/sessionActive";
import {SessionCard} from "../entity/sessionCard";
import {Subscription} from "../../node_modules/rxjs/Subscription";
import {Remark} from "../entity/remark";

@Component({
    selector: 'chatbox',
    template: `
        <h1>HIER KOMT DE CHAT</h1>
    `,
    encapsulation: ViewEncapsulation.None
})

export class ChatComponent {

    @Input()
    private remarks: Remark[];

    public constructor() {
    }
}