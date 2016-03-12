export class User {
    constructor(public id: number,
                public username: string,
                public firstName: string
    ) {

    }

    public static createEmptyUser(): User {
        return new User(0, "", "");
    }
}