import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';

@Component({
    selector: 'create-card',
    templateUrl: 'app/partials_html/createCard.component.html',
    encapsulation: ViewEncapsulation.None
})
export class CreateCardComponent {

    private router: Router;

    private contentService: ContentService;

    private card: Card = Card;
    //new Promise<Theme[]>(resolve => setTimeout(() =>resolve(Theme), 2000));

    public constructor(contentService: ContentService, router: Router) {
        this.router = router;
        this.contentService = contentService;
        document.title = 'Maak thema aan';
    }

    public onSubmit(): void {
        //TODO: id teruggeven en gebruiken om te navigeren
        this.contentService.addCard(this.card);
        //  this.router.navigate(['/Theme', {id: 1}]);
    }

}