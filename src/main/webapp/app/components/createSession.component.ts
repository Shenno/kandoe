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
import {SessionActive} from "../entity/sessionActive";

@Component({
    selector: 'create-session',
    templateUrl: 'app/partials_html/createSession.component.html',
    encapsulation: ViewEncapsulation.None
})

export class CreateSessionComponent {

    private router:Router;

    private userService:UserService;
    private contentService:ContentService;
    private sessionService:SessionService;

    private themes:Theme[] = [];
    private theme:Theme = Theme.createEmptyTheme();
    private cards:Card[] = null;
    private nameSession:string = "";
    private currentUser = null;
    private amountOfCircles = 8;
    private newParticipant = '';
    private participantEmails = [];
    //var cardids: number[] = [];
    private cardIds:number[] = [];
    private users:string[] = [];
    private errorMessage:String = "";

    public constructor(userService:UserService, contentService:ContentService, sessionService:SessionService, router:Router) {
        this.userService = userService;
        this.contentService = contentService;
        this.sessionService = sessionService;
        this.router = router;
        document.title = 'Start een nieuwe Kandoe sessie!';
        this.userService.getMyDetails().subscribe((user:User) => {
            this.participantEmails.push(user.username);
            this.contentService.getThemesByOrganisatorId(user.id.toString()).subscribe((theme:Theme[]) => {
                this.themes = theme
            });
            this.currentUser = user;
            this.userService.getAllUsernames().subscribe((users:string[]) => {
                var index = users.indexOf(this.currentUser.username);
                users.splice(index, 1); //you can't add yourself as organisator
                this.users = users;
            });
        });
    }

    public onSelectTheme(selectedThemeId:number) {
        alert(selectedThemeId);
        if (selectedThemeId != 0) {
            this.contentService.getCardsByThemeId(selectedThemeId.toString()).subscribe((cards:Card[]) => {
                this.cards = cards;
                this.cards.forEach(function (card) {
                    card.selected = true;
                });
            });
            this.contentService.getTheme(selectedThemeId.toString()).subscribe((theme:Theme) => {
                this.theme = theme
            });
        }
        else {
            this.cards = null;
            this.theme = null;
        }
        // Observable.do .map(function(v) { return [1,2,3];}) .subscribe(console.log.bind(console))
    }

    public changeValue(bloe) {
        this.newParticipant = bloe;
    }

    public onAddParticipant() {
        if (this.newParticipant != '') {
            this.participantEmails.push(this.newParticipant);
            var index = this.users.indexOf(this.newParticipant);
            this.users.splice(index, 1);
            this.newParticipant = "";
        } else {
            alert(this.newParticipant);
        }
    }

    public onRemoveParticipant(i) {
        this.users.push(this.participantEmails[i]);
        this.participantEmails.splice(i, 1);
    }

    public changeCardSelectedStatus(card:Card) {
        if (card.selected) {
            card.selected = false;
            return
        }
        card.selected = true;
    }

    public createSession() {
        // var emails: string[] = [];
        var cardids:number[] = [];

        /* Organisator als deelnemer toevoegen? */
        ///  emails.push(this.currentUser.username);
        //alert(this.currentUser.username);

        /* Andere users toevoegen. TODO: dynamisch */
        //emails.push("clarence.ho@gmail.com");

        /* CardIds ophalen */
        this.cards.forEach(function (card) {
            if (card.selected) {
                cardids.push(card.id);
            }
        });

        var session:createSession = new createSession(this.participantEmails, cardids, this.theme.themeId, this.nameSession, this.amountOfCircles);
        //this.sessionService.post....
        /*this.userService.login(this.user)
         .subscribe((res: Response) => {
         localStorage.setItem("jwt", res.text());
         this.userService.triggerLoginEvent();
         //this.userService.getMyDetails().subscribe((user:User) => alert(user.firstName + "BANAAN"));
         this.router.navigate(['/Home']);
         },
         error => {
         console.log(error);
         });
         this.userService.getMyDetails().subscribe(
         (user: User) => this.currentUserDetails = user,
         (err) => this.currentUserDetails = null);
         }*/
        this.sessionService.addSession(session).subscribe(
            (session:SessionActive) => {
                if (session.errorMessage == '') {
                    this.router.navigate(['/Session', {sessionId: session.id}])
                } else {
                    window.scrollTo(0, 0);
                    this.errorMessage = session.errorMessage;
                }
            },
            (err) => this.errorMessage = "Oeps, er trad een onverwachte fout op!");


    }

}