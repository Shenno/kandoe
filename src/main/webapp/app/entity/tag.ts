export class Tag {
    constructor(public name: string)
                 {

    }

    public static createEmptyTag(): Tag {
        return new Tag("");
    }
}