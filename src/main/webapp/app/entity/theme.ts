export class Theme {
    constructor(public themeName: string,
                public description: string,
                public commentaryAllowed: boolean,
                public addingAdmitted: boolean) {

    }

    public static createEmptyTheme(): Theme {
        return new Theme("", "", false, false);
    }
}