import { Message } from './Message';
import { User } from './User';

export class Conversation{
public conversationID: string;
public messages: Message[];
public users: User[];
public groupConversation: boolean;

constructor(conversationID: string){
this.conversationID = conversationID;
this.messages = [];
this.users = [];
}

public addMessage(message: Message){
    this.messages.push(message);
}

public setConversationID(conversationID: string){this.conversationID = conversationID; }
public setMessages(messages: Message[]){this.messages = messages; }
public setUsers(users: User[]){this.users = users; }
public setIsGroupConversation(groupConversation: boolean){this.groupConversation = groupConversation; }

public getConversationID(): string{return this.conversationID; }
public getMessages(): Message[]{return this.messages; }
public getUsers(): User[]{return this.users; }
public isGroupConversation(): boolean{return this.groupConversation; }
}
