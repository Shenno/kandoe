System.register(['angular2/core', "angular2/http", "../service/urlService", "../util/logger", "rxjs/Observable", "angular2/router", 'rxjs/add/observable/interval', 'rxjs/add/operator/switchMap'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, urlService_1, logger_1, Observable_1, router_1;
    var ContentService;
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
            function (Observable_1_1) {
                Observable_1 = Observable_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (_1) {},
            function (_2) {}],
        execute: function() {
            ContentService = (function () {
                function ContentService(http, urlService, logger, router) {
                    this.http = null;
                    this.http = http;
                    this.router = router;
                    this.logger = logger;
                    this.baseUrl = urlService.getUrl();
                    this.urlService = urlService;
                }
                /*Theme*/
                ContentService.prototype.getTheme = function (id) {
                    var url = this.baseUrl + "/api/themes/" + id;
                    var headers = this.urlService.getHeaders(false);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
                };
                ContentService.prototype.getThemesByOrganisatorId = function (id) {
                    var url = this.baseUrl + "/api/themes/organisator/" + id;
                    var headers = this.urlService.getHeaders(true);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
                };
                ContentService.prototype.getCardsByThemeId = function (id) {
                    var url = this.baseUrl + "/api/themes/" + id + "/cards";
                    var headers = this.urlService.getHeaders(true);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
                };
                ContentService.prototype.getMostFrequentCardCombinations = function (themeId) {
                    var url = this.baseUrl + "/api/themes/" + themeId + "/cardCombinations";
                    var headers = this.urlService.getHeaders(false);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
                };
                ContentService.prototype.pollCardsByThemeId = function (id, interval) {
                    var _this = this;
                    var url = this.baseUrl + "/api/themes/" + id + "/cards";
                    var headers = this.urlService.getHeaders(true);
                    return Observable_1.Observable.interval(1000)
                        .switchMap(function () { return _this.http.get(url, { headers: headers }); }).map(function (res) { return res.json(); });
                };
                ContentService.prototype.addTheme = function (theme) {
                    var _this = this;
                    var url = this.baseUrl + "/api/themes";
                    var themeString = JSON.stringify(theme);
                    var headers = this.urlService.getHeaders(true);
                    this.http.post(url, themeString, { headers: headers }).map(function (res) { return res.json(); }).subscribe(function (data) { return _this.onSuccesfulAddTheme(data.themeId, theme); }, (function (err) { return _this.logger.log('Fout tijdens aanmaken van thema: ' + err.message); }));
                };
                ContentService.prototype.onSuccesfulAddTheme = function (id, theme) {
                    this.logger.log('Thema "' + theme.themeName + '" is aangemaakt"');
                    this.router.navigate(['/Theme', { themeId: id }]);
                };
                ContentService.prototype.updateTheme = function (theme) {
                    var _this = this;
                    var url = this.baseUrl + "/api/themes/" + theme.themeId;
                    var themeString = JSON.stringify(theme);
                    var headers = this.urlService.getHeaders(true);
                    this.http.put(url, themeString, { headers: headers }).map(function (res) { return res.json(); }).subscribe(function (data) { return _this.onSuccesfulUpdateTheme(data.themeId, theme); }, (function (err) { return _this.logger.log('Fout tijdens bewerken van thema: ' + err.message); }));
                };
                ContentService.prototype.onSuccesfulUpdateTheme = function (id, theme) {
                    this.logger.log('Thema "' + theme.themeName + '" is bijgewerkt"');
                    this.router.navigate(['/Theme', { themeId: id }]);
                };
                /*Tag*/
                ContentService.prototype.addTag = function (tag) {
                    var _this = this;
                    var url = this.baseUrl + "/api/themes/{mainThemeId}/tags";
                    var themeString = JSON.stringify(tag);
                    var headers = this.urlService.getHeaders(true);
                    this.http.post(url, themeString, { headers: headers }).map(function (res) { return res.json(); }).subscribe(function (data) { return _this.logger.log('Tag "' + tag.name + '" is aangemaakt'); }, (function (err) { return _this.logger.log('Fout tijdens aanmaken van tag: ' + err.message); }));
                };
                /*Card*/
                ContentService.prototype.addCard = function (card) {
                    var _this = this;
                    var url = this.baseUrl + "/api/themes/cards";
                    var cardString = JSON.stringify(card);
                    var headers = this.urlService.getHeaders(true);
                    this.http.post(url, cardString, { headers: headers }).map(function (res) { return res.json(); }).subscribe(function (data) { return _this.onSuccesfulAddCard(data.id, card); }, (function (err) { return _this.logger.log('Fout tijdens aanmaken van card: ' + err.message); }));
                };
                ContentService.prototype.onSuccesfulAddCard = function (id, card) {
                    this.logger.log('Card "' + card.text + '" is aangemaakt voor thema "' + card.themeId);
                    this.router.navigate(['/DetailCard', { cardId: id }]);
                };
                ContentService.prototype.getCard = function (id) {
                    var url = this.baseUrl + "/api/themes/cards/" + id;
                    var headers = this.urlService.getHeaders(true);
                    return this.http.get(url, { headers: headers }).map(function (res) { return res.json(); });
                };
                ContentService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, urlService_1.UrlService, logger_1.Logger, router_1.Router])
                ], ContentService);
                return ContentService;
            })();
            exports_1("ContentService", ContentService);
        }
    }
});
//# sourceMappingURL=contentService.js.map