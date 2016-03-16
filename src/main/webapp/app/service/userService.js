System.register(['angular2/core', "angular2/http", "../service/urlService", "../util/logger", "angular2/router"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, urlService_1, logger_1, router_1;
    var UserService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (urlService_1_1) {
                urlService_1 = urlService_1_1;
            },
            function (logger_1_1) {
                logger_1 = logger_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            }],
        execute: function() {
            UserService = (function () {
                function UserService(http, urlService, logger, router) {
                    this.http = null;
                    this.http = http;
                    this.router = router;
                    this.logger = logger;
                    this.baseUrl = urlService.getUrl();
                    this.urlService = urlService;
                    this.authenticationEvent$ = new core_1.EventEmitter();
                }
                /*Organisation*/
                UserService.prototype.addOrganisation = function (organisation) {
                    var _this = this;
                    var url = this.baseUrl + "/api/organisations";
                    var organisationString = JSON.stringify(organisation);
                    var headers = this.urlService.getHeaders(true);
                    this.http.post(url, organisationString, { headers: headers }).map(function (res) { return res.json(); }).subscribe(function (data) { return _this.onSuccesfulAddOrganisation(data.id, organisation, data.errorMessage); }, (function (err) { return _this.logger.log('Fout tijdens HTTP call voor aanmaken van organisation: ' + err.message); }));
                };
                UserService.prototype.onSuccesfulAddOrganisation = function (id, organisation, errorMessage) {
                    if (errorMessage == null) {
                        this.logger.log('Organisatie "' + organisation.name + '" is aangemaakt"');
                        this.router.navigate(['/Organisation', { organisationId: id }]);
                    }
                    else {
                        organisation.errorMessage = errorMessage;
                    }
                };
                UserService.prototype.getOrganisation = function (id) {
                    var url = this.baseUrl + "/api/organisations/" + id;
                    var headers = this.urlService.getHeaders(false);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
                };
                UserService.prototype.getOrganisations = function () {
                    var url = this.baseUrl + "/api/organisations";
                    var headers = this.urlService.getHeaders(true);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
                };
                UserService.prototype.triggerLoginEvent = function () {
                    this.authenticationEvent$.emit("log in");
                };
                /*Login/Logout*/
                UserService.prototype.login = function (loginUser) {
                    var headers = this.urlService.getHeaders(false);
                    return this.http.post(this.baseUrl + "/api/login", JSON.stringify(loginUser), { headers: headers });
                };
                UserService.prototype.register = function (registerUser) {
                    var _this = this;
                    var content = JSON.stringify(registerUser);
                    var headers = this.urlService.getHeaders(false);
                    this.http.post(this.baseUrl + "/api/users", content, { headers: headers }).map(function (res) { return res.json(); }).subscribe(function (data) {
                        localStorage.setItem("jwt", data);
                        _this.authenticationEvent$.emit("register");
                    }, (function (err) { return _this.logger.log('Fout tijdens het registreren ' + err.message); }));
                    /*this.userService.login(this.user)
                     .subscribe((res: Response) => {
                     localStorage.setItem("jwt", res.text());
                     this.userService.triggerLoginEvent();
                     //this.userService.getMyDetails().subscribe((user:User) => alert(user.firstName + "BANAAN"));
                     this.router.navigate(['/Home']);
                     },
                     error => {
                     console.log(error);
                     });
                     }*/
                };
                UserService.prototype.logout = function () {
                    if (localStorage.getItem("jwt")) {
                        localStorage.removeItem("jwt");
                        this.authenticationEvent$.emit("logout");
                    }
                };
                /*Users*/
                UserService.prototype.getAllUsernames = function () {
                    var headers = this.urlService.getHeaders(true);
                    return this.http.get(this.baseUrl + "/api/usernames", { headers: headers }).map(function (res) { return res.json(); });
                };
                UserService.prototype.getMyDetails = function () {
                    var headers = this.urlService.getHeaders(true);
                    return this.http.get(this.baseUrl + "/api/users/me", { headers: headers }).map(function (res) { return res.json(); });
                };
                UserService.prototype.getUserById = function (id) {
                    var url = this.baseUrl + "/api/users/" + id;
                    var headers = this.urlService.getHeaders(false);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
                };
                UserService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, urlService_1.UrlService, logger_1.Logger, router_1.Router])
                ], UserService);
                return UserService;
            })();
            exports_1("UserService", UserService);
        }
    }
});
//# sourceMappingURL=userService.js.map