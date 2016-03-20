System.register(['angular2/core', "angular2/http"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1;
    var UrlService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            /**
             * Service class to handle everything with Url
             */
            UrlService = (function () {
                function UrlService() {
                    this.host = "localhost";
                    this.port = "9966";
                    this.url = "http://" + this.host + ":" + this.port + "/kandoe";
                }
                UrlService.prototype.getUrl = function () {
                    return this.url;
                };
                UrlService.prototype.getHeaders = function (authNeeded) {
                    var headers = new http_1.Headers();
                    if (authNeeded && localStorage.getItem("jwt")) {
                        headers.append("Authorization", "Bearer " + localStorage.getItem("jwt"));
                    }
                    headers.append("Content-Type", "application/json");
                    return headers;
                };
                UrlService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [])
                ], UrlService);
                return UrlService;
            })();
            exports_1("UrlService", UrlService);
        }
    }
});
//# sourceMappingURL=urlService.js.map