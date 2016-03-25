import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Theme} from '../entity/theme';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";
import {RegisterUser} from "../entity/registerUser";

/**
 * Component for register
 */
@Component({
    selector: 'login',
    template: `
    <div class="content-center">
    <h1>Registeren</h1>
    <form (submit)="register($event, username.value, password.value, firstName.value, lastName.value)">

        <label for="username">Gebruikersnaam</label>
        <!-- Using #username, we can identify this input to get the value on the form's submit event -->
        <input type="text" #username class="form-control" id="username" placeholder="Email">
        <br>
        <label for="password">Paswoord</label>
        <!-- Using #password we can identify this input to get its value -->
        <input type="password" #password class="form-control" id="password" placeholder="Password">
        <br>
        <label for="firstName">Voornaam</label>
        <input type="text" #firstName class="form-control" id="firstName" placeholder="First name">
        <br>
        <label for="lastName">Naam</label>
        <!-- Using #password we can identify this input to get its value -->
        <input type="text" #lastName class="form-control" id="lastName" placeholder="Last name">
        <br>
        <button class="btn btn-primary" type="submit">Registreren</button>
    </form>
    </div>
    `,
    encapsulation: ViewEncapsulation.None
})

export class RegisterComponent {

    public userService: UserService;
    public router: Router;

    public constructor(userService: UserService, router: Router) {
        this.userService = userService;
        this.router = router;
    }

    register(event, username, password, firstName, lastName) {
        event.preventDefault();

        var registerUser  = new RegisterUser(username, password, firstName, lastName);

        this.userService.register(registerUser);
        this.router.navigate(['/Home'])
    }
}