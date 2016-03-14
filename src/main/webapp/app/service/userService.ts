import {Injectable} from 'angular2/core';
import {Http, Response, Headers} from "angular2/http";
import {Organisation} from '../entity/organisation'
import {UrlService} from "../service/urlService";
import {Logger} from "../util/logger";
import {Observable} from "rxjs/Observable";
import {Router} from "angular2/router";
import {LoginUser} from "../entity/loginUser";
import {User} from "../entity/user";
import {RegisterUser} from "../entity/registerUser";

@Injectable()
export class UserService {
    private http:Http = null;
    private logger:Logger;
    private baseUrl:string;
    private router:Router;
    private urlService:UrlService;

    public constructor(http:Http, urlService:UrlService, logger:Logger, router:Router) {
        this.http = http;
        this.router = router;
        this.logger = logger;
        this.baseUrl = urlService.getUrl();
        this.urlService = urlService;
    }

    /*Organisation*/
    public addOrganisation(organisation:Organisation):void {
        var url = this.baseUrl + "/api/organisations";
        var organisationString = JSON.stringify(organisation);
        var headers =  this.urlService.getHeaders(true);
        this.http.post(url, organisationString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.onSuccesfulAddOrganisation(data.id, organisation),
            ((err:Error) => this.logger.log('Fout tijdens aanmaken van organisation: ' + err.message))
        );
    }

    private onSuccesfulAddOrganisation(id:number, organisation:Organisation): void {
        this.logger.log('Organisatie "' + organisation.name + '" is aangemaakt"');
        this.router.navigate(['/Organisation',{organisationId:id}]);
    }

    public getOrganisation(id:string):Observable<Organisation>{
        var url = this.baseUrl + "/api/organisations/" + id;
        var headers =  this.urlService.getHeaders(false);
        return this.http.get(url, {headers: headers}).map((res:Response) => res.json());
    }

    public getOrganisations():Observable<Organisation[]>{
        var url = this.baseUrl + "/api/organisations";
        var headers =  this.urlService.getHeaders(true);
        return this.http.get(url, {headers: headers}).map((res:Response) => res.json());
    }

    /*Login/Logout*/
    public login(loginUser: LoginUser): Observable<Response> {
        var headers =  this.urlService.getHeaders(false);
        return this.http.post(this.baseUrl + "/api/login", JSON.stringify(loginUser), {headers:headers});
    }


    public register(registerUser: RegisterUser): void {
        var content = JSON.stringify(registerUser);
        var headers =  this.urlService.getHeaders(false);
        this.http.post(this.baseUrl + "/api/users", content, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => alert(data),
            ((err:Error) => this.logger.log('Fout tijdens het registreren ' + err.message))
        );
    }

    public logout() {
        if(localStorage.getItem("jwt")) {
            localStorage.removeItem("jwt");
        }
    }

    /*Users*/

    public getAllUsernames(): Observable<string[]> {
        var headers = this.urlService.getHeaders(true);
        return this.http.get(this.baseUrl + "/api/usernames", {headers: headers}).map((res:Response) => res.json());
    }

    public getMyDetails() : Observable<User> {
        var headers =  this.urlService.getHeaders(true);
        return this.http.get(this.baseUrl + "/api/users/me", {headers: headers}).map((res:Response) => res.json());
    }

    public getUserById(id: number) : Observable<User>{
        var url = this.baseUrl + "/api/users/" + id;
        var headers =  this.urlService.getHeaders(false);
        return this.http.get(url, {headers: headers}).map((res:Response) => res.json());
    }

}