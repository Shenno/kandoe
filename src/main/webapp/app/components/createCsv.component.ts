import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {ContentService} from "../service/contentService";
import {RouteParams} from "angular2/router";

@Component({
    selector: 'createCsv',
    templateUrl: 'app/partials_html/createCsv.component.html',
    encapsulation: ViewEncapsulation.None
})
export class CreateCsvComponent {
   /* browserSupportFileUpload():boolean {
        var isCompatible = false;
        if (window.File && window.FileReader && window.FileList && window.Blob) {
            isCompatible = true;
        }
        return isCompatible;
    }

    changeListener($event):void {
        this.upload($event.target);
    }

    upload(inputValue:any):void {
        if (!browserSupportFileUpload()) {
            alert('The File APIs are not fully supported in this browser!');
        } else {

            var data = null;
            var file:File = inputValue.files[0];
            var reader:FileReader = new FileReader();

            //reader.readAsText(file);

            reader.onloadend = function (e) {
                var csvData = reader.result;
                data = $.csv.toArrays(csvData); //???

                if (data && data.length > 0) {
                    alert('Imported -' + data.length + '- rows successfully!');
                } else {
                    alert('No data to import!');
                }
            };

            reader.onerror = function () {
                alert('Unable to read ' + file);
            };
        }
    }
    */
}