System.register(['angular2/core', '../entity/theme', "angular2/http"], function(exports_1) {
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
    var core_1, theme_1, http_1;
    var CreateThemeComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (theme_1_1) {
                theme_1 = theme_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            CreateThemeComponent = (function () {
                //new Promise<Theme[]>(resolve => setTimeout(() =>resolve(Theme), 2000));
                function CreateThemeComponent(http) {
                    this.http = null;
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.http = http;
                }
                CreateThemeComponent.prototype.onSubmit = function () {
                    var url = "http://localhost:9966/kandoe/api/themes";
                    var theme = JSON.stringify(this.theme);
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('Accept', 'application/json');
                    //this.http.get("http://localhost:9966/kandoe/api/users").map((res:Response) => res.json()).subscribe((result:string) => alert(result));
                    this.http.post(url, theme, { headers: headers }).map(function (res) { return res.json(); }).subscribe(function (data) { return alert('Data'); }, (function (err) { return alert('Error' + err.message); }), function () { return alert('OK'); });
                };
                CreateThemeComponent = __decorate([
                    core_1.Component({
                        selector: 'create-theme',
                        templateUrl: 'app/partials_html/createTheme.component.html',
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], CreateThemeComponent);
                return CreateThemeComponent;
            })();
            exports_1("CreateThemeComponent", CreateThemeComponent);
        }
    }
});
//# sourceMappingURL=createTheme.component.js.map