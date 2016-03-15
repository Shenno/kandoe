import {User} from "./user";
import {Theme} from"./theme";

export class Organisation {
    constructor(public id: number,
                public name: string,
                public organisatorId: number,
                public organisatorName: string,
                public themes : string[],
                public errorMessage : string
                ) {

    }

    public static createEmptyOrganisation(): Organisation {
        return new Organisation(0, "", 0,"",[], "");
    }
}