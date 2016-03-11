import {Component, ViewEncapsulation, Output, Input, OnChanges, SimpleChange, EventEmitter} from 'angular2/core';
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
    selector: 'kandoe-circle',
    template: `
        <div id="circle">
            <div id="board">
                <div class="subcircle" style="width:10%; height:10%; z-index:10;"></div>
                <div class="subcircle" style="width:20%; height:20%;z-index:9;"></div>
                <div class="subcircle" style="width:30%; height:30%;z-index:8;"></div>
                <div class="subcircle" *ngIf="amountOfCircles >= 3" [style.width]="40 + '%'" [style.height]="40 + '%'" [style.z-index]="7"></div>
                <div class="subcircle" *ngIf="amountOfCircles >= 4" style="width:50%; height:50%;z-index:6;"></div>
                <div class="subcircle" *ngIf="amountOfCircles >= 5" style="width:60%; height:60%;z-index:5;"></div>
                <div class="subcircle" *ngIf="amountOfCircles >= 6" style="width:70%; height:70%;z-index:4;"></div>
                <div class="subcircle" *ngIf="amountOfCircles >= 7" style="width:80%; height:80%;z-index:3;"></div>
                <div class="subcircle" *ngIf="amountOfCircles >= 8" style="width:90%; height:90%;z-index:2;"></div>
                <div class="subcircle"  style="width:100%; height:100%;z-index:1;"></div>
                <div id="x-axis"></div>
                <div id="y-axis"></div>
                <div class="post-it circle-card" [style.left]="card.x + 'px'" [style.top]="card.y + 'px'" (click)="moveCard(i, card)" *ngFor="#card of circleCards; #i = index">
                    <span class="post-it-number">  {{i+1}}</span>
                    <span class="post-it-hover-text">{{card.card}}</span>
                </div>
            </div>
        </div>
    `,
    encapsulation: ViewEncapsulation.None
})

export class KandoeCircleComponent implements OnChanges {

    private sessionService:SessionService;

    @Output()
    private swapPlayer: EventEmitter<SessionCard> = new EventEmitter();

    @Input()
    private circleCards:SessionCard[];

    @Input()
    private amountOfCircles:number;

    @Input()
    private currentSessionId:string;

    @Input()
    private eligibleToMoveCard:boolean;

    @Input()
    private sessionId;

    //Testing purposes
    //private radians = [Math.PI/2 ,(5*Math.PI)/12, Math.PI/3, Math.PI/4, Math.PI/6, Math.PI/12, 2*Math.PI, (23*Math.PI)/12, (11*Math.PI)/6, (7*Math.PI)/4, (5*Math.PI)/3, (19*Math.PI)/12, (3*Math.PI)/2, (17*Math.PI)/12, (4*Math.PI)/3, (5*Math.PI)/4, (7*Math.PI)/6, (13*Math.PI)/12, Math.PI, (11*Math.PI)/12, (5*Math.PI)/6, (3*Math.PI)/4, (2*Math.PI)/3, (7*Math.PI)/12];

    private cardThickness = 10;
    private circleThickness = 30;
    private circleRadius = 300;

    public constructor(sessionService:SessionService) {
        this.sessionService = sessionService;
    }

    ngOnChanges(changes:{[propName: string]: SimpleChange}) {
        this.changeCardCoordinates();
    }

    public changeCardCoordinates() {
        var index = 0;
        for (index; index < this.circleCards.length; index++) {
            var angleDeg = ((360 / this.circleCards.length) * index) + 270;

            if (angleDeg > 360) angleDeg = angleDeg - 360;

            var angleRad = angleDeg * (Math.PI / 180);
            console.log(this.circleRadius);
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




    }
}