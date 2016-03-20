/**
 * An object for creating a Session
 */
export class createSession {
    constructor(public participantsEmails: string[],
                public cardIds: number[],
                public themeId: number,
                public nameSession: string,
                public amountOfCircles: number) {
    }

}