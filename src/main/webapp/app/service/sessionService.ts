import {Injectable} from 'angular2/core';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {Logger} from "../util/logger";
import {Observable} from "rxjs/Observable";
import {Router} from "angular2/router";
import 'rxjs/add/observable/interval';
import 'rxjs/add/operator/switchMap';
import {createSession} from "../entity/createSession";
import {SessionActive} from "../entity/sessionActive";
import {SessionCard} from "../entity/sessionCard";

@Injectable()
export class SessionService {
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

   /* public getSession(id:string): Observable<Session> {
        var url = this.baseUrl + "/api/sessions/" + id;
        var headers =  this.urlService.getHeaders(true);
        return this.http.get(url, {headers: headers}).map((res:Response) => res.json());
    }*/

    public addSession(sessionToBeCreated:createSession): Observable<number> {
        var url = this.baseUrl + "/api/sessions";
        var headers = this.urlService.getHeaders(true);
        return this.http.post(url, JSON.stringify(sessionToBeCreated), {headers: headers}).map((res:Response) => res.json());
    }

    public getSession(id:string): Observable<SessionActive> {
        var url = this.baseUrl + "/api/sessions/" + id;
        var headers = this.urlService.getHeaders(true);
        return this.http.get(url, {headers:headers}).map((res:Response) => res.json());
    }

    public pollSession(id:string, interval:number): Observable<SessionActive> {
        var url = this.baseUrl + "/api/sessions/" + id;
        var headers = this.urlService.getHeaders(true);
        return Observable.interval(interval)
            .switchMap(() => this.http.get(url, {headers:headers})).map((res:Response) => res.json());
    }

    public makeMove(sessionCard:SessionCard, id:string) : Observable<SessionActive> {
        var url = this.baseUrl + "/api/sessions/" + id;
        var headers = this.urlService.getHeaders(true);
        return this.http.post(url, JSON.stringify(sessionCard), {headers: headers}).map((res:Response) => res.json());
    }
}