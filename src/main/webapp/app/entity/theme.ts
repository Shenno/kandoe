export class Theme {
    constructor(public themeName: string,
                public description: string,
                public isCommentaryAllowed: boolean,
                public isAddingAdmitted: boolean) {

    }

    public static createEmptyTheme(): Theme {
        return new Theme("", "", false, false);
    }
}