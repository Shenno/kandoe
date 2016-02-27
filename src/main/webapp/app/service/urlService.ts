import {Injectable} from 'angular2/core';

@Injectable()
export class UrlService {
    private host: string = "localhost";
    private port: string = "9966";
    private url: string = "http://" + this.host + ":" + this.port + "/kandoe";

    public getUrl(): string {
        return this.url;
    }
}