import { Conversation } from './Conversation';

export class User{
public name: string;
public userID: string;
public email: string;
public friends: User[];
public conversations: Conversation[];
public friendRequests: User[];

constructor(){
this.userID = '';
this.name = '';
this.email = '';
this.friends = [];
this.conversations = [];
this.friendRequests = [];
}

public setName(name: string){this.name = name; }
public setUserID(userID: string){this.userID = userID; }
public setEmail(email: string){this.email = email; }
public setFriends(friends: User[]){this.friends = friends; }
public setConversations(conversations: Conversation[]){this.conversations = conversations; }
public setFriendRequests(friendRequests: User[]){this.friendRequests = friendRequests; }

public getName(){return this.name; }
public getUserID(){return this.userID; }
public getEmail(){return this.email; }
public getFriends(){return this.friends; }
public getConversations(){return this.conversations; }
public getFriendRequests(){return this.friendRequests; }
}
