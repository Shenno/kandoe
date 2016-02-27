import {Injectable} from 'angular2/core';
import {Http, Response, Headers} from "angular2/http";
import {Theme} from '../entity/theme';
import {UrlService} from "../service/urlService";
import {Logger} from "../util/logger";

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
}