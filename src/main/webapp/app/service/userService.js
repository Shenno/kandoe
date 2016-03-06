System.register(['angular2/core', "angular2/http", "../service/urlService", "../util/logger", "angular2/router"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") return Reflect.decorate(decorators, target, key, desc);
        switch (arguments.length) {
            case 2: return decorators.reduceRight(function(o, d) { return (d && d(o)) || o; }, target);
            case 3: return decorators.reduceRight(function(o, d) { return (d && d(target, key)), void 0; }, void 0);
            case 4: return decorators.reduceRight(function(o, d) { return (d && d(target, key, o)) || o; }, desc);
        }
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
                }
                /*Organisation*/
                UserService.prototype.addOrganisation = function (organisation) {
                    var _this = this;
                    var url = this.baseUrl + "/api/organisations";
                    var organisationString = JSON.stringify(organisation);
                    var headers = this.urlService.getHeaders(true);
                    this.http.post(url, organisationString, { headers: headers }).map(function (res) { return res.json(); }).subscribe(function (data) { return _this.onSuccesfulAddOrganisation(data.id, organisation); }, (function (err) { return _this.logger.log('Fout tijdens aanmaken van organisation: ' + err.message); }));
                };
                UserService.prototype.onSuccesfulAddOrganisation = function (id, organisation) {
                    this.logger.log('Organisatie "' + organisation.name + '" is aangemaakt"');
                    this.router.navigate(['/DetailOrganisation', { organisationId: id }]);
                };
                UserService.prototype.getOrganisation = function (id) {
                    var url = this.baseUrl + "/api/organisations/" + id;
                    var headers = this.urlService.getHeaders(true);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
                };
                UserService.prototype.getOrganisations = function () {
                    var url = this.baseUrl + "/api/organisations";
                    var headers = this.urlService.getHeaders(true);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
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
                    this.http.post(this.baseUrl + "/api/users", content, { headers: headers }).map(function (res) { return res.json(); }).subscribe(function (data) { return alert(data); }, (function (err) { return _this.logger.log('Fout tijdens het registreren ' + err.message); }));
                };
                UserService.prototype.logout = function () {
                    if (localStorage.getItem("jwt")) {
                        localStorage.removeItem("jwt");
                    }
                };
                UserService.prototype.getMyDetails = function () {
                    var headers = this.urlService.getHeaders(true);
                    return this.http.get(this.baseUrl + "/api/users/me", { headers: headers }).map(function (res) { return res.json(); });
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