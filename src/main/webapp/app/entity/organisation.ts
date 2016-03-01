export class Organisation {
    constructor(public id: number,
                public organisationName: string
                ) {

    }

    public static createEmptyOrganisation(): Organisation {
        return new Organisation(0, "");
    }
}