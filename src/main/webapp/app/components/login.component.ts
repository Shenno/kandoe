import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Theme} from '../entity/theme';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";
import {LoginUser} from "../entity/loginUser";
import {User} from "../entity/user";

/**
 * Component for the login page
 */
@Component({
    selector: 'login',
    template: `
    <div class="content-center">
    <h1>Inloggen</h1>
    <div>
        <label for="ib_username">E-mailadres</label>
        <input class="form-control" id="ib_username" name="ib_username" [(ngModel)]="user.username"/>
    </div>

    <div>
        <label for="ib_password">Paswoord</label>
        <input class="form-control" type="password" id="ib_password" name="ib_password" [(ngModel)]="user.password"/>
    </div>
    <br>
    <button class="btn btn-primary" name="btn_login" (click)="login()" >Inloggen</button>
    </div>
    `,
    encapsulation: ViewEncapsulation.None
})
export class LoginComponent {

    private userService:UserService;
    private router;

    private user: LoginUser = new LoginUser("", "");

    public constructor(router:Router, userService:UserService) {
        document.title = 'Login';
        this.userService = userService;
        this.router = router;
    }

    public login(): void {
        this.userService.login(this.user)
            .subscribe((res: Response) => {
                    localStorage.setItem("jwt", res.text());
                    this.userService.triggerLoginEvent();
                    this.router.navigate(['/Home']);
                },
                error => {
                    console.log(error);
                });
        }
}