import {SessionCard} from "./sessionCard";

export class SessionActive {
    constructor(public currentUser:number, public cardSessionResources: SessionCard[], public problem:boolean, public amountOfCircles: number) {

    }

}