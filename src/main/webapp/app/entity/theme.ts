export class Theme {
    constructor(public id: number,
                public themeName: string,
                public description: string,
                public commentaryAllowed: boolean,
                public addingAdmitted: boolean) {

    }

    public static createEmptyTheme(): Theme {
        return new Theme(0, "", "", false, false);
    }
}