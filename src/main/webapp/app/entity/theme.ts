import {Organisation} from "./organisation";
export class Theme {
    constructor(public id: number,
                public themeName: string,
                public description: string,
                public commentaryAllowed: boolean,
                public addingAdmitted: boolean,
                public organisation: Organisation) {

    }

    public static createEmptyTheme(): Theme {
        return new Theme(0, "", "", false, false, Organisation.createEmptyOrganisation());
    }
}