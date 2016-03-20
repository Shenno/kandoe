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
/**
 * Component for chatbox
 */
@Component({
    selector: 'chatbox',
    template: `
        <h1 style="border-bottom: 2px solid #4985B9">Chat</h1>

        <div style="overflow: auto; height: 275px">
            <div *ngFor="#remark of remarks" class="chat-component">
                <div style="font-style: italic">
                    [{{remark.timeStamp[2]}}/{{remark.timeStamp[1]}}/{{remark.timeStamp[0]}}
                    {{remark.timeStamp[3]}}:{{remark.timeStamp[4]}}:{{remark.timeStamp[5]}}]
                </div>
                <span class="text-success" style="font-weight: bold">{{remark.username}}:</span>
                <p>{{remark.text}}</p>
            </div>
        </div>
        <div class="input-group">
            <input type="textarea" [(ngModel)]="currentMessage" class="form-control">
            <span class="input-group-btn">
                <button (click)="processRemark()" class="btn btn-default"><span class="glyphicon glyphicon-comment"></span> Chat!</button>
            </span>
        </div>
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
        this.currentMessage = "";
    }

    public constructor(sessionService: SessionService) {
        this.sessionService = sessionService;
    }
}