import {Component, ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
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
import {SessionActive} from "../entity/sessionActive";
import {SessionCard} from "../entity/sessionCard";
import {Subscription} from "../../node_modules/rxjs/Subscription";

@Component({
    selector: 'view-session',
    template: `<h1>Hallo, {{currentUser?.username}}!</h1>
        <h1 *ngIf="myTurn && !currentSession?.gameOver">Het is jouw beurt, {{currentUser?.username}}!</h1>
        <h1 *ngIf="!myTurn && !currentSession?.gameOver">Wacht even je beurt af!</h1>
        <h1 class="alert-danger" *ngIf="currentSession?.gameOver">Het spel is afgelopen!</h1>
        <template [ngIf]="currentSession">
            <div *ngFor="#card of currentSession.cardSessionResources; #i = index">
                <img src="{{card?.image}}" height="225px" width="200px">
                {{card?.card}}, afstand tot centrum van de cirkel: {{card?.distanceToCenter}}
                <button [class.disabled]="!myTurn || currentSession?.gameOver" class="btn btn-success" (click)="makeMove(card)">Push!</button>
            </div>
        </template>
    `,
    encapsulation: ViewEncapsulation.None
})
/*<div *ngFor="#card of cards">{{card?.text}}
 <1img src="{{card.imageURL}}">
 </div>*/
/*<h1 *ngIf="myTurn">Het is jouw beurt, {{currentUser?.username}}!</h1>
 <h1 *ngIf="!myTurn">Wacht even je beurt af!</h1>
 <div *ngIf="currentSession" *ngFor="#card of currentSession.cardSessionResources">
 {{card.card}}
 <img src="{{card.image}}">
 </div>*/
/*<select [(ng-model)]="objValue1">
 <option *ng-for="#o of objArray" [value]="o">{{o.name}}</option>
 </select>*/

export class SessionComponent {

    private userService: UserService;
    private contentService: ContentService;
    private sessionService: SessionService;
    private router: Router;
    private currentUser: User = null;
    private currentSession: SessionActive = null;
    private myTurn: boolean = false;
    private currentSessionId;
    private subscription;

    public constructor(routeParam:RouteParams, userService: UserService, contentService: ContentService, sessionService: SessionService, router: Router) {
        this.router = router;
        this.userService = userService;
        this.contentService = contentService;
        this.sessionService = sessionService;
        document.title = 'Kandoe sessie';

        this.userService.getMyDetails().subscribe((user:User) => {
            this.currentUser = user;
        });

        this.currentSessionId = routeParam.params["sessionId"];

        //Get session once
        this.sessionService.getSession(this.currentSessionId).subscribe((sessionActive:SessionActive) => {
            this.updateView(sessionActive);
            //Start polling for updates
            this.subscription  = this.sessionService.pollSession(routeParam.params["sessionId"], 7500).subscribe((sessionActive:SessionActive) => {
                this.updateView(sessionActive);
            });
        });
    }

    public updateView(sessionActive:SessionActive) {
        console.log("Updating view...");
        this.currentSession = sessionActive;
        if(sessionActive.gameOver) {
            this.subscription.unsubscribe();
        }
        if(sessionActive.currentUser == this.currentUser.id) {
            this.myTurn = true;
        } else {
            this.myTurn = false;
        }
    }

    public makeMove(card: SessionCard) {
        this.sessionService.makeMove(card, this.currentSessionId).subscribe((session:SessionActive) => {
            this.updateView(session);
        });
    }
}