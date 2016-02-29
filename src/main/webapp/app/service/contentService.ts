import {Injectable} from 'angular2/core';
import {Http, Response, Headers} from "angular2/http";
import {Theme} from '../entity/theme';
import {Tag} from '../entity/tag';
import {UrlService} from "../service/urlService";
import {Logger} from "../util/logger";
import {Observable} from "rxjs/Observable";
import {Router} from "angular2/router";

@Injectable()
export class ContentService {
    private http:Http = null;
    private logger:Logger;
    private baseUrl: string;
    private router:Router;

    public constructor(http:Http, urlService: UrlService, logger:Logger, router:Router) {
        this.http = http;
        this.router = router;
        this.logger = logger;
        this.baseUrl = urlService.getUrl();
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
}