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
import {ChatComponent} from "./chat.component";
import {KandoeCircleComponent} from "./kandoeCircle.component";

@Component({
    selector: 'view-session',
    directives: [ChatComponent, KandoeCircleComponent],
    template: `
        <h1>{{currentSession?.nameSession}}</h1>
        <h2 *ngIf="myTurn && !currentSession?.gameOver">Jij bent aan de beurt, {{currentUser?.firstName}}!</h2>
        <h2 *ngIf="!myTurn && !currentSession?.gameOver">Wacht even je beurt af!</h2>
        <h2 class="alert-danger" *ngIf="currentSession?.gameOver">Het spel is afgelopen!</h2>
        <template [ngIf]="currentSession">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <div class="center-content">
                        <kandoe-circle (swapPlayer)="swapPlayer(movedCard)" (gameOver)="currentSession.gameOver"
                        [eligibleToMoveCard]="myTurn" [sessionId]="currentSessionId"
                        [amountOfCircles]="currentSession.amountOfCircles" [circleCards]="currentSession.cardSessionResources"
                        [isOrganisator]="currentSession.organisator == currentUser.id">
                        </kandoe-circle>
                    </div>
                </div>
                <div class="col-md-5" style="overflow-y: auto">
                    <div *ngFor="#card of currentSession.cardSessionResources; #i = index">
                        <div class="col-md-2 col-xs-3 card">
                            <span [id]="'card' + i + '_number'" class="badge alert-danger">{{i+1}}</span>
                            <div class="card-image">
                                <img [id]="'card' + i + '_image'" src="{{card?.image}}">
                            </div>
                            <span [id]="'card' + i + '_name'">{{card?.card}}</span>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="chatbox">
                        <chatbox [currentSessionId]="currentSessionId" [remarks]="currentSession.remarks"></chatbox>
                    </div>
                </div>
            </div>
            <!--
            <div class="row">
                <div class="col-lg-3 hidden-md">
                    <chatbox [currentSessionId]="currentSessionId" [remarks]="currentSession.remarks"></chatbox>
                </div>
            </div>-->
            </div>
        </template>
    `,
    encapsulation: ViewEncapsulation.None
})

export class SessionComponent {

    private userService:UserService;
    private contentService:ContentService;
    private sessionService:SessionService;
    private router:Router;
    private currentUser:User = null;
    private currentSession:SessionActive = null;
    private myTurn:boolean = false;
    private currentSessionId;
    private subscription;

    private testerino:SessionCard[] = [];

    public constructor(routeParam:RouteParams, userService:UserService, contentService:ContentService, sessionService:SessionService, router:Router) {
        this.router = router;
        this.userService = userService;
        this.contentService = contentService;
        this.sessionService = sessionService;
        document.title = 'Kandoe sessie';

        this.userService.getMyDetails().subscribe((user:User) => {
            this.currentUser = user;
        });

        this.currentSessionId = routeParam.params["sessionId"];

        /*this.sessionService.addSession(session).subscribe(
         (session:SessionActive) => {
         if (session.errorMessage == '') {
         this.router.navigate(['/Session', {sessionId: session.id}])
         } else {
         window.scrollTo(0, 0);
         this.errorMessage = session.errorMessage;
         }
         },
         (err) => this.errorMessage = "Oeps, er trad een onverwachte fout op!");*/

        //Get session once
        this.sessionService.getSession(this.currentSessionId).subscribe(
            (sessionActive:SessionActive) => {
                this.updateView(sessionActive);
                //Start polling for updates
                this.subscription = this.sessionService.pollSession(routeParam.params["sessionId"], 5000).subscribe((sessionActive:SessionActive) => {
                    this.updateView(sessionActive);
                    this.currentSession.cardSessionResources = sessionActive.cardSessionResources;
                    this.currentSession.remarks = sessionActive.remarks;
                });
        },  (err) => this.router.navigate(['/Error']));
    }

    public isOrganisator(): boolean {
        if(this.currentUser.id == this.currentSession.organisator) {
            return true;
        }
        return false;
    }

    public updateView(sessionActive:SessionActive) {
        this.currentSession = sessionActive;
        if (sessionActive.gameOver) {
            this.myTurn = false;
            this.subscription.unsubscribe();
        }
        if (sessionActive.currentUser == this.currentUser.id) {
            this.myTurn = true;
        } else {
            this.myTurn = false;
        }
    }

    public swapPlayer(movedCard:SessionCard) {
        this.myTurn = false;
        for (var i = 0; i < this.currentSession.cardSessionResources.length; i++) {
            if (this.currentSession.cardSessionResources[i].id = movedCard.id) {
                this.currentSession.cardSessionResources[i] = movedCard;
            }
        }
        this.currentSession.cardSessionResources = this.currentSession.cardSessionResources.slice();
    }

    /* public makeMove(card: SessionCard) {
     this.sessionService.makeMove(card, this.currentSessionId).subscribe((session:SessionActive) => {
     this.updateView(session);
     });
     }*/
}