import {Organisation} from "./organisation";
export class Theme {
    constructor(public themeId: number,
                public themeName: string,
                public description: string,
                public commentaryAllowed: boolean,
                public addingAdmitted: boolean,
                public organisationId: number,
                public organisatorId: number,
                public tags: string[]) {

    }

    public static createEmptyTheme(): Theme {
        return new Theme(0, "", "", false, false, 0, 0, []);
    }
}