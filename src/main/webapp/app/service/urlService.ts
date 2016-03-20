import {Injectable} from 'angular2/core';
import {Headers} from "angular2/http";
/**
 * Service class to handle everything with Url
 */
@Injectable()
export class UrlService {
    private host: string = "localhost";
    private port: string = "9966";
    private url: string = "http://" + this.host + ":" + this.port + "/kandoe";

    public getUrl(): string {
        return this.url;
    }

    public getHeaders(authNeeded: boolean) : Headers {
        var headers = new Headers();
        if(authNeeded && localStorage.getItem("jwt")) {
            headers.append("Authorization", "Bearer " + localStorage.getItem("jwt"));
        }
        headers.append("Content-Type", "application/json");
        return headers;
    }
}