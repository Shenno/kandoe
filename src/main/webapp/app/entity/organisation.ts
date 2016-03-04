import {User} from "./user";
import {Theme} from"./theme";

export class Organisation {
    constructor(public id: number,
                public name: string,
                public organisator: User,
                public themes : Theme[]
                ) {

    }

    public static createEmptyOrganisation(): Organisation {
        alert(test);
        return new Organisation(0, "", User.createEmptyUser(),[]);
    }
}