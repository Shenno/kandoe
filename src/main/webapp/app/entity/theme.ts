export class Theme {
    constructor(public name: string,
                public description: string,
                public commentaryAllowed: boolean,
                public addingAdmitted: boolean) {

    }

    public static createEmptyTheme(): Theme {
        return new Theme("", "", false, false);
    }
}