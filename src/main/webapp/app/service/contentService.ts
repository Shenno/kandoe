import {Injectable} from 'angular2/core';
import {Http, Response, Headers} from "angular2/http";
import {Theme} from '../entity/theme';
import {Tag} from '../entity/tag';
import {UrlService} from "../service/urlService";
import {Logger} from "../util/logger";
import {Observable} from "rxjs/Observable";
import {Router} from "angular2/router";
import {Card} from "../entity/card";
import {AuthService} from "./authService";

@Injectable()
export class ContentService {
    private http:Http = null;
    private logger:Logger;
    private baseUrl: string;
    private router:Router;
    private authService:AuthService;

    public constructor(http:Http, urlService: UrlService, logger:Logger, router:Router, authService: AuthService) {
        this.http = http;
        this.router = router;
        this.logger = logger;
        this.baseUrl = urlService.getUrl();
        this.authService = authService;
    }

    /*Theme*/

    public getTheme(id:string): Observable<Theme> {
        var url = this.baseUrl + "/api/themes/" + id;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');

        return this.http.get(url, {headers: headers}).map((res:Response) => res.json())/*.subscribe(
            (data) => this.logger.log('Thema succesvol teruggekregen')
            ((err:Error) => this.logger.log('Fout tijdens opvragen van thema' + id + ': ' + err.message))
        )*/;
    }

    public addTheme(theme:Theme): void {
        var url = this.baseUrl + "/api/themes";
        var themeString = JSON.stringify(theme);
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');
        this.http.post(url, themeString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.onSuccesfulAddTheme(data.id, theme),
            ((err:Error) => this.logger.log('Fout tijdens aanmaken van thema: ' + err.message))
        );
    }

    private onSuccesfulAddTheme(id:number, theme:Theme): void {
        this.logger.log('Thema "' + theme.themeName + '" is aangemaakt"');
        this.router.navigate(['/DetailTheme', {themeId: id}]);
    }

    public updateTheme(theme:Theme): void {
        var url = this.baseUrl + "/api/themes/" + theme.id;
        var themeString = JSON.stringify(theme);
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');
        this.http.put(url, themeString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.onSuccesfulUpdateTheme(data.id, theme),
            ((err:Error) => this.logger.log('Fout tijdens bewerken van thema: ' + err.message))
        );
    }

    private onSuccesfulUpdateTheme(id:number, theme:Theme): void {
        this.logger.log('Thema "' + theme.themeName + '" is bijgewerkt"');
        this.router.navigate(['/DetailTheme', {themeId: id}]);
    }

    /*Tag*/

    public addTag(tag:Tag): void {
        var url = this.baseUrl + "/api/themes/{mainThemeId}/tags";
        var themeString = JSON.stringify(tag);
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');
        this.http.post(url, themeString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.logger.log('Tag "' + tag.name + '" is aangemaakt'),
            ((err:Error) => this.logger.log('Fout tijdens aanmaken van tag: ' + err.message))
        );
    }

    /*Card*/
    public addCard(card:Card): void {
        var url = this.baseUrl + "/api/themes/cards";
        var cardString = JSON.stringify(card);
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');
        this.http.post(url, cardString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.onSuccesfulAddCard(data.id, card),
            ((err:Error) => this.logger.log('Fout tijdens aanmaken van card: ' + err.message))
        );
    }

    private onSuccesfulAddCard(id:number, card:Card): void {
        this.logger.log('Card "' + card.text + '" is aangemaakt"');
        this.router.navigate(['/DetailTheme', {themeId: id}]);
    }
}