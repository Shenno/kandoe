/**
 * An object for Cards for a Specific Session
 */
export class SessionCard {
    constructor(public id: number,
                public distanceToCenter: number,
                public card: string,
                public image: string,
                public x:number,
                public y:number) {

    }

}