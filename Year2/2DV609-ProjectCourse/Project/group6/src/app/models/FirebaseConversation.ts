

export class FirebaseConversation{
public conversationID: string;
public userIDList: string[];


constructor(){
this.conversationID = '';
}

public setConversationID(conversationID: string){this.conversationID = conversationID; }
public setUserIDList(userList: string[]){this.userIDList = userList; }

public getConversationID(){return this.conversationID; }
public getUserIDList(){return this.userIDList; }


}
