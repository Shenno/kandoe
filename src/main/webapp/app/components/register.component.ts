import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Theme} from '../entity/theme';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";
import {RegisterUser} from "../entity/registerUser";

@Component({
    selector: 'login',
    template: `
    <form (submit)="register($event, username.value, password.value, firstName.value, lastName.value)">

        <label for="username">Username</label>
        <!-- Using #username, we can identify this input to get the value on the form's submit event -->
        <input type="text" #username class="form-control" id="username" placeholder="Email">

        <label for="password">Password</label>
        <!-- Using #password we can identify this input to get its value -->
        <input type="password" #password class="form-control" id="password" placeholder="Password">

        <label for="firstName">First name</label>
        <input type="text" #firstName class="form-control" id="firstName" placeholder="First name">

        <label for="lastName">Last name</label>
        <!-- Using #password we can identify this input to get its value -->
        <input type="text" #lastName class="form-control" id="lastName" placeholder="Last name">

        <button type="submit">Submit</button>
    </form>
    `,
    encapsulation: ViewEncapsulation.None
})

export class RegisterComponent {

    public userService: UserService;

    public constructor(userService: UserService) {
        this.userService = userService;
    }

    register(event, username, password, firstName, lastName) {
        event.preventDefault();

        var registerUser  = new RegisterUser(username, password, firstName, lastName);

        this.userService.register(registerUser);
        /*this.userService.register()
        fetch('http://localhost:3001/sessions/create', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username, password
            })
        })
            .then(status)
            .then(json)
            .then((response) => {
                // Once we get the JWT in the response, we save it into localStorage
                localStorage.setItem('jwt', response.id_token);
                // and then we redirect the user to the home
                this.router.parent.navigate('/home');
            })
            .catch((error) => {
                alert(error.message);
                console.log(error.message);
            });*/
    }
}