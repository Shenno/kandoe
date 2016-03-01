import {Injectable} from 'angular2/core';
import {Http, Response, Headers} from "angular2/http";
import {Organisation} from '../entity/organisation'
import {UrlService} from "../service/urlService";
import {Logger} from "../util/logger";
import {Observable} from "rxjs/Observable";
import {Router} from "angular2/router";

@Injectable()
export class UserService {
    private http:Http = null;
    private logger:Logger;
    private baseUrl:string;
    private router:Router;

    public constructor(http:Http, urlService:UrlService, logger:Logger, router:Router) {
        this.http = http;
        this.router = router;
        this.logger = logger;
        this.baseUrl = urlService.getUrl();
    }

    /*Organisation*/
    public addOrganisation(organisation:Organisation):void {
        var url = this.baseUrl + "/api/organisations";
        var organisationString = JSON.stringify(organisation);
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');
        this.http.post(url, organisationString, {headers: headers}).map((res:Response) => res.json()).subscribe(
            (data) => this.onSuccesfulAddOrganisation(data.id, organisation),
            ((err:Error) => this.logger.log('Fout tijdens aanmaken van organisation: ' + err.message))
        );
    }

    private onSuccesfulAddOrganisation(id:number, organisation:Organisation): void {
        alert("1");
        this.logger.log('Organisatie "' + organisation.name + '" is aangemaakt"');
        alert("2" + id);
        this.router.navigate(['/detailOrganisation',{organisationId:id}]);
        alert("3");
    }

    public getOrganisation(id:string):Observable<Organisation>{
        var url = this.baseUrl + "/api/organisations/" + id;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');
        return this.http.get(url, {headers: headers}).map((res:Response) => res.json())
    }


}