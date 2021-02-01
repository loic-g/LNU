
export class FirebaseUser{
public name: string;
public userID: string;
public email: string;
public conversations: string[];
public friends: string[];
public friendRequests: string[];

constructor(){
this.userID = '';
this.name = '';
this.email = '';
this.conversations = [];
this.friends = [];
this.friendRequests = [];
}

public setName(name: string){this.name = name; }
public setUserID(userID: string){this.userID = userID; }
public setEmail(email: string){this.email = email; }
public setConversations(conversations: string[]){this.conversations = conversations; }
public setFriends(friends: string[]){this.friends = friends; }
public setFriendRequests(friendRequests: string[]){this.friendRequests = friendRequests; }

public getName(){return this.name; }
public getUserID(){return this.userID; }
public getEmail(){return this.email; }
public getConversations(){return this.conversations; }
public getFriends(){return this.friends; }
public getFriendRequests(){return this.friendRequests; }
}
