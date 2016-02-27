import {Injectable} from 'angular2/core';
import {Http, Response, Headers} from "angular2/http";
import {Theme} from '../entity/theme';
import {Tag} from '../entity/tag';
import {UrlService} from "../service/urlService";
import {Logger} from "../util/logger";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ContentService {
    private http:Http = null;
    private logger:Logger;
    private baseUrl: string;

    public constructor(http:Http, urlService: UrlService, logger:Logger) {
        this.http = http;
        this.logger = logger;
        this.baseUrl = urlService.getUrl();
    }

    public addTheme(theme:Theme): void {
        var url = this.baseUrl + "/api/themes";
        var themeString = JSON.stringify(theme);
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');
        this.http.post(url, themeString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.logger.log('Thema "' + theme.name + '" is aangemaakt'),
            ((err:Error) => this.logger.log('Fout tijdens aanmaken van thema: ' + err.message))
        );
    }
    /*public getFigure(id:string):Observable<Theme>{
        //
    }*/
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
}