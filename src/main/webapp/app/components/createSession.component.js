System.register(['angular2/core', 'angular2/router', "../service/userService", "../entity/theme", "../service/contentService", "../entity/createSession", "../service/sessionService"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
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
            /**
             * Component for creating Session
             */
            CreateSessionComponent = (function () {
                function CreateSessionComponent(userService, contentService, sessionService, router) {
                    var _this = this;
                    this.themes = [];
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.cards = null;
                    this.nameSession = "";
                    this.currentUser = null;
                    this.amountOfCircles = 8;
                    this.newParticipant = '';
                    this.participantEmails = [];
                    //var cardids: number[] = [];
                    this.cardIds = [];
                    this.users = [];
                    this.errorMessage = "";
                    this.userService = userService;
                    this.contentService = contentService;
                    this.sessionService = sessionService;
                    this.router = router;
                    document.title = 'Start een nieuwe Kandoe sessie!';
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.participantEmails.push(user.username);
                        _this.contentService.getThemesByOrganisatorId(user.id.toString()).subscribe(function (theme) {
                            _this.themes = theme;
                        });
                        _this.currentUser = user;
                        _this.userService.getAllUsernames().subscribe(function (users) {
                            var index = users.indexOf(_this.currentUser.username);
                            users.splice(index, 1); //you can't add yourself as organisator
                            _this.users = users;
                        });
                    });
                }
                CreateSessionComponent.prototype.onSelectTheme = function (selectedThemeId) {
                    var _this = this;
                    if (selectedThemeId != 0) {
                        this.contentService.getCardsByThemeId(selectedThemeId.toString()).subscribe(function (cards) {
                            _this.cards = cards;
                            _this.cards.forEach(function (card) {
                                card.selected = true;
                            });
                        });
                        this.contentService.getTheme(selectedThemeId.toString()).subscribe(function (theme) {
                            _this.theme = theme;
                        });
                    }
                    else {
                        this.cards = null;
                        this.theme = null;
                    }
                };
                CreateSessionComponent.prototype.changeValue = function (bloe) {
                    this.newParticipant = bloe;
                };
                CreateSessionComponent.prototype.onAddParticipant = function () {
                    if (this.newParticipant != '') {
                        this.participantEmails.push(this.newParticipant);
                        var index = this.users.indexOf(this.newParticipant);
                        this.users.splice(index, 1);
                        this.newParticipant = "";
                    }
                    else {
                    }
                };
                CreateSessionComponent.prototype.onRemoveParticipant = function (i) {
                    this.users.push(this.participantEmails[i]);
                    this.participantEmails.splice(i, 1);
                };
                CreateSessionComponent.prototype.changeCardSelectedStatus = function (card) {
                    if (card.selected) {
                        card.selected = false;
                        return;
                    }
                    card.selected = true;
                };
                CreateSessionComponent.prototype.createSession = function () {
                    var _this = this;
                    var cardids = [];
                    /* CardIds ophalen */
                    this.cards.forEach(function (card) {
                        if (card.selected) {
                            cardids.push(card.id);
                        }
                    });
                    var session = new createSession_1.createSession(this.participantEmails, cardids, this.theme.themeId, this.nameSession, this.amountOfCircles);
                    this.sessionService.addSession(session).subscribe(function (session) {
                        if (session.errorMessage == '') {
                            _this.router.navigate(['/Session', { sessionId: session.id }]);
                        }
                        else {
                            window.scrollTo(0, 0);
                            _this.errorMessage = session.errorMessage;
                        }
                    }, function (err) { return _this.errorMessage = "Oeps, er trad een onverwachte fout op!"; });
                };
                CreateSessionComponent = __decorate([
                    core_1.Component({
                        selector: 'create-session',
                        templateUrl: 'app/partials_html/createSession.component.html',
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [userService_1.UserService, contentService_1.ContentService, sessionService_1.SessionService, router_1.Router])
                ], CreateSessionComponent);
                return CreateSessionComponent;
            }());
            exports_1("CreateSessionComponent", CreateSessionComponent);
        }
    }
});
//# sourceMappingURL=createSession.component.js.map