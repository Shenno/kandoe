System.register(['angular2/core', "angular2/http"], function(exports_1) {
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