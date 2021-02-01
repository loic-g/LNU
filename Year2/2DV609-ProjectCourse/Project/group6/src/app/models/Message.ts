import { User } from './User';

export class Message{
	public text: string;
    public user: User;
    public messageID: string;
    public usersHasReadMessage: User[];
	public time: number;
	
	constructor(text: string){
		this.text = text;
		this.user = new User();
        this.usersHasReadMessage = [];
		this.time = 0;
		this.messageID = '';
	}

	getText(){return this.text;}
    getUserID(){return this.user;}
    getTime(){return this.time;}
    getMessageID(){return this.messageID;}
    getUserHasReadMessage(){return this.usersHasReadMessage;}

    setText(text: string) {
        if (text != null) {
            this.text = text;
        }
    }

    setUser(user: User) {
        if (user) {
            this.user = user;
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

    setUserHasReadMessage(messagesRead: User[]){
        if(messagesRead != null){
            this.usersHasReadMessage = messagesRead;
        }
    }
}
