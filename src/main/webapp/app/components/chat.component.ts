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
        <h1>Chat</h1>
        <div *ngFor="#remark of remarks">
            [{{remark.timeStamp[2]}}/{{remark.timeStamp[1]}}/{{remark.timeStamp[0]}}
            {{remark.timeStamp[3]}}:{{remark.timeStamp[4]}}:{{remark.timeStamp[5]}}]
            <span class="text-success">{{remark.username}}:</span>
            {{remark.text}}
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
        var mes = this.currentMessage;
        this.sessionService.addRemark(new Remark(null, null, mes), this.currentSessionId).subscribe((remarks:Remark[]) => {
            this.remarks = remarks;
        });
        console.log(this.remarks[0].timeStamp[0]);
        this.currentMessage = "";
        alert(this.currentMessage);
    }

    public constructor(sessionService: SessionService) {
        this.sessionService = sessionService;
    }
}