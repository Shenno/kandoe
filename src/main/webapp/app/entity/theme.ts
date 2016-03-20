/**
 * An object for showing and creating and editing a Theme
 */
export class Theme {
    constructor(public themeId: number,
                public themeName: string,
                public description: string,
                public commentaryAllowed: boolean,
                public addingAdmitted: boolean,
                public organisationId: number,
                public organisatorId: number,
                public organisatorNames: string[],
                public tags: string[],
                public errorMessage: string) {

    }

    public static createEmptyTheme(): Theme {
        return new Theme(0, "", "", false, false, 0, 0, [], [], "");
    }
}