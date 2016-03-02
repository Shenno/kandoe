import {Injectable} from 'angular2/core';
import {Http, Response, Headers} from "angular2/http";
import {Theme} from '../entity/theme';
import {Tag} from '../entity/tag';
import {UrlService} from "../service/urlService";
import {Logger} from "../util/logger";
import {Observable} from "rxjs/Observable";
import {Router} from "angular2/router";

@Injectable()
export class AuthService {
    private http:Http = null;
    private logger:Logger;
    private baseUrl: string;
    private router:Router;
    private res:Response;

    public constructor(http:Http, urlService: UrlService, logger:Logger, router:Router) {
        this.http = http;
        this.router = router;
        this.logger = logger;
        this.baseUrl = urlService.getUrl();
    }

    public get(url: string) : Observable<Response> {
        var headers = new Headers();
        headers.append("Authorization", "Bearer " + localStorage.getItem("jwt"));
        return this.http.get(url,{headers:headers});
    }

    public post(url: string, body: string) {

        var headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("Authorization", "Bearer " + localStorage.getItem("jwt"));
        return this.http.post(url, body, {headers: headers});
    }
}