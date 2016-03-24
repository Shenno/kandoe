import {User} from "./user";
import {Theme} from"./theme";
/**
 * An object for showing and creating an Organisation
 */
export class Organisation {
    constructor(public id: number,
                public name: string,
                public organisatorId: number,
                public organisatorName: string,
                public themes : Theme[],
                public errorMessage : string
                ) {

    }

    public static createEmptyOrganisation(): Organisation {
        return new Organisation(0, "", 0,"",[], "");
    }
}