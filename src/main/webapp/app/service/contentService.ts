import {Injectable} from 'angular2/core';
import {Http, Response, Headers} from "angular2/http";
import {Theme} from '../entity/theme';
import {Tag} from '../entity/tag';
import {UrlService} from "../service/urlService";
import {Logger} from "../util/logger";
import {Observable} from "rxjs/Observable";
import {Router} from "angular2/router";
import {Card} from "../entity/card";
import 'rxjs/add/observable/interval';
import 'rxjs/add/operator/switchMap';

@Injectable()
export class ContentService {
    private http:Http = null;
    private logger:Logger;
    private baseUrl: string;
    private router:Router;
    private urlService: UrlService;

    public constructor(http:Http, urlService: UrlService, logger:Logger, router:Router) {
        this.http = http;
        this.router = router;
        this.logger = logger;
        this.baseUrl = urlService.getUrl();
        this.urlService = urlService;
    }

    /*Theme*/

    public getTheme(id:string): Observable<Theme> {
        var url = this.baseUrl + "/api/themes/" + id;
        var headers =  this.urlService.getHeaders(false);
        return this.http.get(url, {headers: headers}).map((res:Response) => res.json());
    }

    public getThemesByOrganisatorId(id:string): Observable<Theme[]> {
        var url = this.baseUrl + "/api/themes/organisator/" + id;
        var headers = this.urlService.getHeaders(true);
        return this.http.get(url,{headers: headers}).map((res:Response) => res.json());
    }

    public getCardsByThemeId(id:string): Observable<Theme[]> {
        var url = this.baseUrl + "/api/themes/" + id + "/cards";
        var headers = this.urlService.getHeaders(true);
        return this.http.get(url, {headers:headers}).map((res:Response) => res.json());
    }

    public pollCardsByThemeId(id:string, interval:number): Observable<Theme[]> {
        var url = this.baseUrl + "/api/themes/" + id + "/cards";
        var headers = this.urlService.getHeaders(true);
        return Observable.interval(1000)
            .switchMap(() => this.http.get(url, {headers:headers})).map((res:Response) => res.json());
    }

    public addTheme(theme:Theme): void {
        var url = this.baseUrl + "/api/themes";
        var themeString = JSON.stringify(theme);
        var headers =  this.urlService.getHeaders(true);

        this.http.post(url, themeString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.onSuccesfulAddTheme(data.themeId, theme),
            ((err:Error) => this.logger.log('Fout tijdens aanmaken van thema: ' + err.message))
        );
    }

    private onSuccesfulAddTheme(id:number, theme:Theme): void {
        this.logger.log('Thema "' + theme.themeName + '" is aangemaakt"');
        this.router.navigate(['/DetailTheme', {themeId: id}]);
    }

    public updateTheme(theme:Theme): void {
        var url = this.baseUrl + "/api/themes/" + theme.themeId;
        var themeString = JSON.stringify(theme);
        var headers =  this.urlService.getHeaders(true);
        this.http.put(url, themeString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.onSuccesfulUpdateTheme(data.themeId, theme),
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
        var headers =  this.urlService.getHeaders(true);
        this.http.post(url, themeString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.logger.log('Tag "' + tag.name + '" is aangemaakt'),
            ((err:Error) => this.logger.log('Fout tijdens aanmaken van tag: ' + err.message))
        );
    }

    /*Card*/
    public addCard(card:Card): void {
        var url = this.baseUrl + "/api/themes/cards";
        var cardString = JSON.stringify(card);
        var headers =  this.urlService.getHeaders(true);
        this.http.post(url, cardString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.onSuccesfulAddCard(data.id, card),
            ((err:Error) => this.logger.log('Fout tijdens aanmaken van card: ' + err.message))
        );
    }

    private onSuccesfulAddCard(id:number, card:Card): void {
        this.logger.log('Card "' + card.text + '" is aangemaakt voor thema "'+ card.themeId);
        this.router.navigate(['/DetailCard', {cardId: id}]);
    }

    public getCard(id:string): Observable<Card> {
        var url = this.baseUrl + "/api/themes/cards/" + id;
        var headers =  this.urlService.getHeaders(true);
        return this.http.get(url, {headers: headers}).map((res:Response) => res.json());
    }
}