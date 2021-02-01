

export class FirebaseMessage {
    public text: string;
    public userID: string;
    public messageID: string;
    public usersHasReadMessage: string[];
    public time: number;

    constructor() {
        this.text = '';
        this.userID = '';
        this.usersHasReadMessage = [];
        this.time = 0;
    }

    getText(){return this.text;}
    getUserID(){return this.userID;}
    
    getTime(){return this.time;}
    getMessageID(){return this.messageID;}


    getUserHasReadMessage(){return this.usersHasReadMessage;}

    setText(text: string) {
        if (text != null) {
            this.text = text;
        }
    }

    setUserID(userid: string) {
        if (userid != null) {
            this.userID = userid;
        }
    }
    setMessageID(messageID: string) {
        if (messageID != null) {
            this.messageID = messageID;
        }
    }

    setTime(time: number) {
        if (time != null) {
            this.time = time;
        }
    }

    setUserHasReadMessage(messagesRead: string[]){
        if(messagesRead != null){
            this.usersHasReadMessage = messagesRead;
        }
    }
}
