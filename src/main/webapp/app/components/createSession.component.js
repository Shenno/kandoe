System.register(['angular2/core', 'angular2/router', "../service/userService", "../entity/theme", "../service/contentService", "../entity/createSession", "../service/sessionService"], function(exports_1) {
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
    var core_1, router_1, userService_1, theme_1, contentService_1, createSession_1, sessionService_1;
    var CreateSessionComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            },
            function (theme_1_1) {
                theme_1 = theme_1_1;
            },
            function (contentService_1_1) {
                contentService_1 = contentService_1_1;
            },
            function (createSession_1_1) {
                createSession_1 = createSession_1_1;
            },
            function (sessionService_1_1) {
                sessionService_1 = sessionService_1_1;
            }],
        execute: function() {
            CreateSessionComponent = (function () {
                function CreateSessionComponent(userService, contentService, sessionService, router) {
                    var _this = this;
                    this.themes = [];
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.cards = null;
                    this.currentUser = null;
                    this.userService = userService;
                    this.contentService = contentService;
                    this.sessionService = sessionService;
                    this.router = router;
                    document.title = 'Start een nieuwe Kandoe sessie!';
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.contentService.getThemesByOrganisatorId(user.id.toString()).subscribe(function (theme) { _this.themes = theme; });
                        _this.currentUser = user;
                    });
                }
                CreateSessionComponent.prototype.onSelectTheme = function (selectedThemeId) {
                    var _this = this;
                    alert(selectedThemeId);
                    if (selectedThemeId != 0) {
                        this.contentService.getCardsByThemeId(selectedThemeId.toString()).subscribe(function (cards) { _this.cards = cards; });
                        this.contentService.getTheme(selectedThemeId.toString()).subscribe(function (theme) { _this.theme = theme; });
                    }
                    else {
                        this.cards = null;
                        this.theme = null;
                    }
                    // Observable.do .map(function(v) { return [1,2,3];}) .subscribe(console.log.bind(console))
                };
                CreateSessionComponent.prototype.createSession = function () {
                    var _this = this;
                    //Checken of alles ingevuld is
                    if (this.cards != null) {
                        var emails = [];
                        var cardids = [];
                        /* Organisator als deelnemer toevoegen? */
                        emails.push(this.currentUser.username);
                        alert(this.currentUser.username);
                        /* Andere users toevoegen. TODO: dynamisch */
                        emails.push("clarence.ho@gmail.com");
                        /* CardIds ophalen */
                        this.cards.forEach(function (card) {
                            cardids.push(card.id);
                            alert(card.id);
                        });
                        var session = new createSession_1.createSession(emails, cardids, this.theme.themeId);
                        //this.sessionService.post....
                        this.sessionService.addSession(session).subscribe(function (persistedSessionId) {
                            _this.router.navigate(['/Session', { sessionId: persistedSessionId }]);
                            //this.router.navigate(['/DetailTheme',{themeId:id}]);
                        });
                        alert("Ok");
                    }
                    else {
                        alert("Not ok");
                    }
                };
                CreateSessionComponent = __decorate([
                    core_1.Component({
                        selector: 'create-session',
                        template: "\n\n    <select #t (change)=\"onSelectTheme(t.value)\">\n        <option [value]=\"0\">Geen thema</option>\n        <option *ngFor=\"#th of themes\" [value]=\"th.themeId\">{{th.themeName}}</option>\n    </select>\n    <div *ngIf=\"cards\">Dit zijn de kaartjes die we gevonden hebben van het thema {{theme.themeName}}:\n        <div *ngFor=\"#card of cards\">{{card?.text}}\n            <img src=\"{{card.imageURL}}\">\n        </div>\n    </div>\n    <button *ngIf=\"cards\" class=\"btn btn-success\" (click)=\"createSession()\">Sessie aanmaken rond het thema {{theme.themeName}} </button>\n\n    ",
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [userService_1.UserService, contentService_1.ContentService, sessionService_1.SessionService, router_1.Router])
                ], CreateSessionComponent);
                return CreateSessionComponent;
            })();
            exports_1("CreateSessionComponent", CreateSessionComponent);
        }
    }
});
//# sourceMappingURL=createSession.component.js.map