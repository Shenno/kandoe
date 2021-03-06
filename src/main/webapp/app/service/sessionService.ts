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
import {Remark} from "../entity/remark";
/**
 * Service class to handle everything with Session
 */
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

    public terminateSession(id:any) : Observable<void> {
        var url = this.baseUrl + "/api/sessions/" + id + "/terminate";
        var headers = this.urlService.getHeaders(true);
        return this.http.post(url, "", {headers: headers}).map((res:Response) => res.json());
    }

    public addRemark(remark: Remark, id:string) : Observable<Remark[]> {
        var url = this.baseUrl + "/api/sessions/" + id + "/remarks";
        var headers = this.urlService.getHeaders(true);
        return this.http.post(url, JSON.stringify(remark), {headers: headers}).map((res:Response) => res.json());
    }
    public getSessionsByUserId(id:number):Observable<SessionActive[]>{
        var url = this.baseUrl + "/api/sessions";
        var headers =  this.urlService.getHeaders(true);
        return this.http.get(url, {headers: headers}).map((res:Response) => res.json());
    }
}