import {Component, ViewEncapsulation, Output, Input, OnChanges, OnInit, SimpleChange, EventEmitter} from 'angular2/core';
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
import {Ring} from "../entity/ring";
import {NgClass} from "angular2/common";
import {Axis} from "../entity/axis";

/**
 * Component for the kandoe circle
 */
@Component({
    selector: 'kandoe-circle',
    template: `
        <div id="circle">
            <div id="board">
                <template [ngIf]="axis">
                    <div id="x-axis" [style.left]="axis.left + '%'" [style.width]="axis.width + '%'"></div>
                    <div id="y-axis" [style.top]="axis.top + '%'" [style.height]="axis.height + '%'"></div>
                </template>
                <div [id]="'card' + i" class="post-it circle-card" [style.left]="card.x + 'px'" [style.top]="card.y + 'px'" (click)="moveCard(i, card)" *ngFor="#card of circleCards; #i = index">
                    <span [id]="'card' + i + '_numberOnCircle'" class="post-it-number">  {{i+1}}</span>
                    <span [id]="'card' + i + '_text'" class="post-it-hover-text">{{card.card}}</span>
                </div>
                <template [ngIf]="rings">
                    <div *ngFor="#ring of rings; #i = index" class="subcircle"
                    [style.width]="ring.width + '%'" [style.height]="ring.height + '%'"
                    [style.z-index]="ring.z" >
                    </div>
                </template>
            </div>
            <div class="text-center padding-top">
                <button *ngIf="!gameOver && isOrganisator" class="btn btn-danger" (click)="terminateSession()">Stop deze sessie</button>
            </div>
        </div>
    `,
    encapsulation: ViewEncapsulation.None,
    directives: [NgClass]
})

export class KandoeCircleComponent implements OnChanges, OnInit {

    private sessionService:SessionService;

    @Output()
    private swapPlayer: EventEmitter<SessionCard> = new EventEmitter();

    @Input()
    private circleCards:SessionCard[];

    @Input()
    private amountOfCircles:number;

    @Input()
    private eligibleToMoveCard:boolean;

    @Input()
    private sessionId;

    @Input()
    private gameOver;

    @Input()
    private isOrganisator;

    private rings: Ring[] = [];
    private axis: Axis = null;

    private cardThickness = 10;
    private circleThickness;
    private circleRadius = 225;

    public constructor(sessionService:SessionService) {
        this.sessionService = sessionService;
    }

    ngOnInit() {
        var step = 100/(this.amountOfCircles + 2);
        var z = 10;
        for(var i = 0; i < this.amountOfCircles + 2; i++) {
            if(i == this.amountOfCircles + 1) {
                this.rings.push(new Ring(step + (i*step), step + (i*step), z, true));
            } else {
                this.rings.push(new Ring(step + (i*step), step + (i*step), z, false));
            }
            z = z - 1;
        }
        var topleft = 100/((this.amountOfCircles+2)*2);
        var heightwidth = 100 - ((100/this.amountOfCircles)*2);
        this.axis = new Axis(topleft, topleft, heightwidth, heightwidth);
    }

    ngOnChanges(changes:{[propName: string]: SimpleChange}) {
        this.changeCardCoordinates();

    }

    public terminateSession() {
        this.sessionService.terminateSession(this.sessionId).subscribe();
        this.gameOver = true;
        this.eligibleToMoveCard = false;
    }

    public changeCardCoordinates() {
        this.circleThickness = this.circleRadius / (this.amountOfCircles + 2);
        var index = 0;
        for (index; index < this.circleCards.length; index++) {
            var angleDeg = ((360 / this.circleCards.length) * index) + 270;

            if (angleDeg > 360) angleDeg = angleDeg - 360;

            var angleRad = angleDeg * (Math.PI / 180);
            var ringRadius = (this.circleRadius - ((this.circleRadius) - ((this.circleCards[index].distanceToCenter + 1) * this.circleThickness))) - this.cardThickness;
            var circleOrigin = this.circleRadius - this.cardThickness;
            this.circleCards[index].x = circleOrigin + (ringRadius * Math.cos(angleRad));
            this.circleCards[index].y = circleOrigin + (ringRadius * Math.sin(angleRad));
        }
    }

    moveCard(index: number, movedCard:SessionCard) {

        if(!this.eligibleToMoveCard) {
            return;
        }

        this.eligibleToMoveCard = false;


        var topleftX = movedCard.x;
        var topleftY = movedCard.y;
        var midpointX;
        var midpointY;
        var polarR;
        var polarT;

        //van linkerbovenhoek naar middelpunt cirkel (das dus (x',y') = (x+R, y+R) waar R de straal van de cirkel is)
        midpointX = topleftX - (this.circleRadius);
        midpointY = topleftY - (this.circleRadius);

        //omzetten naar poolcoordinaten d.w.z. (x,y) -> (r,t) waarbij r de afstand tot het middelpunt van de cirkel is
        //t = Theta = de gemaakte hoek
        //midpoint2polar (x,y) = (sqrt (x*x + y*y), atan2 y x)
        polarR = Math.sqrt(Math.pow(midpointX, 2) + Math.pow(midpointY, 2));
        polarT = Math.atan2(midpointY, midpointX);

        //polarR verminderen met dikte van 1 schil
        polarR = polarR - this.circleThickness;

        //poolcoordinaten terug omzetten naar midpoint (x,y) = (r * cos theta, r * sin theta)
        midpointX = polarR * Math.cos(polarT);
        midpointY = polarR * Math.sin(polarT);

        //omzetten naar linkerbovenhoek
        movedCard.x = midpointX + (this.circleRadius);
        movedCard.y = midpointY + (this.circleRadius);

        this.sessionService.makeMove(movedCard, this.sessionId).subscribe(
           // () => this.swapPlayer.emit(movedCard)
        );

        if(movedCard.distanceToCenter == 1) {
            this.eligibleToMoveCard = false;
        }




    }
}