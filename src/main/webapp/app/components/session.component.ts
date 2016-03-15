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

        <h1 *ngIf="myTurn && !currentSession?.gameOver">Jij bent aan de beurt, {{currentUser?.firstName}}!</h1>
        <h1 *ngIf="!myTurn && !currentSession?.gameOver">Wacht even je beurt af!</h1>
        <h1 class="alert-danger" *ngIf="currentSession?.gameOver">Het spel is afgelopen!</h1>
        <template [ngIf]="currentSession">
        <div class="container-fluid">
            <div class="row custom-rw">
                <div class="col-md-4 fixed-column">
                    <div class="center-content">
                        <kandoe-circle (swapPlayer)="swapPlayer(movedCard)" (gameOver)="currentSession.gameOver"
                        [eligibleToMoveCard]="myTurn" [sessionId]="currentSessionId"
                        [amountOfCircles]="currentSession.amountOfCircles" [circleCards]="currentSession.cardSessionResources"
                        [isOrganisator]="currentSession.organisator == currentUser.id">
                        </kandoe-circle>
                        <!--<h3 *ngIf="myTurn && !currentSession?.gameOver">Jij bent aan de beurt, {{currentUser?.firstName}}!</h3>
                        <h3 *ngIf="!myTurn && !currentSession?.gameOver">Wacht even je beurt af!</h3>
                        <h3 class="alert-danger" *ngIf="currentSession?.gameOver">Het spel is afgelopen!</h3>-->
                    </div>
                </div>
                <div class="col-md-5">
                    <div *ngFor="#card of currentSession.cardSessionResources; #i = index">
                            <div class="col-md-2 col-xs-3 card">
                            <span class="badge alert-danger center-content">{{i+1}}</span>
                            <div class="image-sizing img-thumbnail"><img src="{{card?.image}}" class="card-image"></div>

                                {{card?.card}}
                            </div>
                    </div>
                </div>
                <div class="col-md-2 col-xs-4">
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

        //Testing purposes
        /*var i;
         for (i = 0; i < 12 ; i++) {
         //var tmpcard:SessionCard = new SessionCard(i, 8, "test", "google.com", 290 + (290*Math.cos(pies[i])), 290 + (290*Math.sin(pies[i])));
         var tmpcard:SessionCard = new SessionCard(i, 5, "test", "google.com", 1, 1);
         this.testerino.push(tmpcard);
         }*/

        this.currentSessionId = routeParam.params["sessionId"];

        //Get session once
        this.sessionService.getSession(this.currentSessionId).subscribe((sessionActive:SessionActive) => {
            this.updateView(sessionActive);
            //Start polling for updates
            this.subscription = this.sessionService.pollSession(routeParam.params["sessionId"], 5000).subscribe((sessionActive:SessionActive) => {
                this.updateView(sessionActive);
                this.currentSession.cardSessionResources = sessionActive.cardSessionResources;
                this.currentSession.remarks = sessionActive.remarks;

                //Testing purposes
                /*
                 this.testerino[0].id = this.testerino[0].id + 1;
                 // var blabla = this.testerino.slice();
                 this.testerino = this.testerino.slice();
                 */
            });
        });
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