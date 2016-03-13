import {SessionCard} from "./sessionCard";
import {Remark} from "./remark";

export class SessionActive {
//    private remarks: Remark[] = [];

    constructor(public nameSession: String, public amountOfUsers: number, public gameOver: boolean, public remarks: Remark[], public currentUser:number, public cardSessionResources: SessionCard[], public problem:boolean, public amountOfCircles: number) {
    }

}