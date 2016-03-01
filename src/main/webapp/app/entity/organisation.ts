import {User} from "./user";
export class Organisation {
    constructor(public id: number,
                public name: string,
                public organisator: User
                ) {

    }

    public static createEmptyOrganisation(): Organisation {
        return new Organisation(0, "", User.createEmptyUser());
    }
}