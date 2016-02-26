import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Theme} from '../entity/theme';
import {Http, Response, Headers} from "angular2/http";

@Component({
    selector: 'create-theme',
    templateUrl: 'app/partials_html/createTheme.component.html',
    encapsulation: ViewEncapsulation.None
})
export class CreateThemeComponent {
    private http:Http = null;

    private theme: Theme = Theme.createEmptyTheme();
        //new Promise<Theme[]>(resolve => setTimeout(() =>resolve(Theme), 2000));

    public constructor(http:Http) {
        this.http = http;
    }

    public onSubmit(): void {
        var url = "http://localhost:9966/kandoe/api/themes";
        var theme = JSON.stringify(this.theme);
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        this.http.get("http://localhost:9966/kandoe/api/users").map((res:Response) => res.json()).subscribe((result:string) => alert(result));
        /* this.http.post(url, theme, {headers: headers}).map((res:Response) => res.json()).subscribe(
            data => alert(data)
        );*/
    }
}