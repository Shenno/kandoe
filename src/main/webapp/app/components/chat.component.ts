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
        <div *ngFor="#remark of remarks">
            {{remark.text}} {{remark.timeStamp}}
        </div>
        <input type="textarea" [(ngModel)]="currentMessage">
        <button (click)="processRemark()">Chat!</button>
    `,
    encapsulation: ViewEncapsulation.None
})

export class ChatComponent {

    private sessionService: SessionService;

    @Input()
    private remarks: Remark[];

    private currentMessage:string = "";

    @Input()
    private currentSessionId:string;

    public processRemark() {
        alert("Remarks were made: " + this.currentMessage);
        var mes = this.currentMessage;
        this.sessionService.addRemark(new Remark(null, null, mes), this.currentSessionId).subscribe((remarks:Remark[]) => {
            this.remarks = remarks;
        });
        this.currentMessage = "";
        alert(this.currentMessage);
    }

    public constructor(sessionService: SessionService) {
        this.sessionService = sessionService;
    }
}