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
    selector: 'create-session',
    template: `

    <select #t (change)="onSelectTheme(t.value)">
        <option [value]="0">Geen thema</option>
        <option *ngFor="#th of themes" [value]="th.themeId">{{th.themeName}}</option>
    </select>
    <div *ngIf="cards">Dit zijn de kaartjes die we gevonden hebben van het thema {{theme.themeName}}:
        <div *ngFor="#card of cards">
            <input (change)="changeCardSelectedStatus(card)" type="checkbox" [checked]="card.selected">
            <img src="{{card.imageURL}}" height="225px" width="200px">{{card?.text}}
        </div>
    </div>
    <button *ngIf="cards" class="btn btn-success" (click)="createSession()">Sessie aanmaken rond het thema {{theme.themeName}} </button>

    `,
    encapsulation: ViewEncapsulation.None
})
/*<select [(ng-model)]="objValue1">
 <option *ng-for="#o of objArray" [value]="o">{{o.name}}</option>
 </select>*/

export class CreateSessionComponent {

    private router: Router;

    private userService: UserService;
    private contentService: ContentService;
    private sessionService: SessionService;
    private themes : Theme[] = [];
    private theme: Theme = Theme.createEmptyTheme();
    private cards: Card[] = null;
    private currentUser = null;
    //var cardids: number[] = [];
    private cardIds: number[] = [];

    public constructor(userService: UserService, contentService: ContentService, sessionService: SessionService, router: Router) {
        this.userService = userService;
        this.contentService = contentService;
        this.sessionService = sessionService;
        this.router = router;
        document.title = 'Start een nieuwe Kandoe sessie!';
        this.userService.getMyDetails().subscribe((user:User) => {
            this.contentService.getThemesByOrganisatorId(user.id.toString()).subscribe((theme:Theme[]) =>{ this.themes = theme});
            this.currentUser = user;
        });
    }

    public onSelectTheme(selectedThemeId:number) {
        alert(selectedThemeId);
        if(selectedThemeId != 0) {
            this.contentService.getCardsByThemeId(selectedThemeId.toString()).subscribe((cards:Card[]) => {
                this.cards = cards;
                this.cards.forEach( function (card) {
                    card.selected = true;
                });
            });
            this.contentService.getTheme(selectedThemeId.toString()).subscribe((theme:Theme) => {this.theme = theme});
        }
        else {
            this.cards = null;
            this.theme = null;
        }
       // Observable.do .map(function(v) { return [1,2,3];}) .subscribe(console.log.bind(console))
    }

    public changeCardSelectedStatus(card:Card) {
        if(card.selected) {
            card.selected = false;
            return
        }
        card.selected = true;
    }

    public createSession() {
        //Checken of alles ingevuld is
        if(this.cards != null) {
            var emails: string[] = [];
            var cardids: number[] = [];

            /* Organisator als deelnemer toevoegen? */
            emails.push(this.currentUser.username);
            alert(this.currentUser.username);

            /* Andere users toevoegen. TODO: dynamisch */
            emails.push("clarence.ho@gmail.com");

            /* CardIds ophalen */
            this.cards.forEach( function (card) {
                if(card.selected) {
                    cardids.push(card.id);
                }
            });

            var session: createSession = new createSession(emails, cardids, this.theme.themeId);
            //this.sessionService.post....
            this.sessionService.addSession(session).subscribe((persistedSessionId:number) => {
                this.router.navigate(['/Session', {sessionId:persistedSessionId}]);
                //this.router.navigate(['/DetailTheme',{themeId:id}]);
            });

            alert("Ok");
        }
        else {
            alert("Not ok");
        }
    }

}