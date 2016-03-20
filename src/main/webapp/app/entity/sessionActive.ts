import {SessionCard} from "./sessionCard";
import {Remark} from "./remark";
/**
 * An Object for an Active Session
 */
export class SessionActive {

    constructor(public id: number,public nameSession: String, public amountOfUsers: number, public gameOver: boolean, public remarks: Remark[], public currentUser:number, public currentUserName: string,public themeName: string,
                public cardSessionResources: SessionCard[], public problem:boolean, public amountOfCircles: number, public organisator:number, public errorMessage: String) {
    }

}