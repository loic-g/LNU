import { Injectable } from '@angular/core';

//Firebase imports
import { AngularFirestore } from '@angular/fire/firestore';

//Service
import { FirebaseImporterService } from './firebase-importer.service';

//Models
import { FirebaseUser } from 'src/app/models/FirebaseUser';
import { FirebaseConversation } from 'src/app/models/FirebaseConversation';
import { FirebaseMessage } from 'src/app/models/FirebaseMessage';
import { User } from 'src/app/models/User';
import { Message } from 'src/app/models/Message';


@Injectable({
  providedIn: 'root'
})
export class FirebaseExporterService {

  constructor(private database: AngularFirestore,
    private fbImport: FirebaseImporterService) { }

  addUser(user: FirebaseUser): Promise<FirebaseUser> {
    console.log(user);
    return new Promise<FirebaseUser>((resolve, reject) => {
      if (user != null) {
        //user.setUserID(this.database.createId());
        this.database.doc(`users/${user.userID}`).set(Object.assign({}, user));
        resolve(user);
      } else {
        reject();
      }
    })
  }

  updateUser(userID: string, user: User): Promise<FirebaseUser> {
    return new Promise((resolve, reject) => {
      if (user != null) {
        let firebaseUser = new FirebaseUser();
        firebaseUser.setEmail(user.email);
        firebaseUser.setName(user.name);
        firebaseUser.setUserID(userID);
        const friendIDs = [];
        user.getFriends().forEach(friend => { //converting friends to strings
          friendIDs.push(friend.getUserID());
        });
        firebaseUser.setFriends(friendIDs);

        const conversationIDs = [];
        user.getConversations().forEach(conversation => { //converting conversations to strings
          conversationIDs.push(conversation.getConversationID);
        });
        firebaseUser.setConversations(conversationIDs);

        this.database.doc(`users/${firebaseUser.userID}`).update(Object.assign({}, firebaseUser));
        resolve(firebaseUser);
      } else {
        reject();
      }
    })
  }

  deleteUser(userID: string) {
    this.database.doc(`users/${userID}`).delete();
  }
  //NEED TO LOOK INTO IT 
  async addConversationIDToUser(userID: string, conversationid: string) {
    //Need to first get the array of the conversation IDs 
    //and add the new conversation ID to able to update the firebase. 
    let convIDs = await this.fbImport.getConversationIDsUser(userID);
    console.log(convIDs)

    let conversationIDs = convIDs;
    conversationIDs.push(conversationid);
    if (convIDs != null) {
      this.database.collection('users').doc(userID).set(
        { conversations: conversationIDs },
        { merge: true }
      )
    } else {
      console.log("NOT TODAY");
    }
  }

  AddChat(conversation: FirebaseConversation): Promise<FirebaseConversation> {
    return new Promise((resolve, reject) => {
      if (conversation != null) {

        //Part to create the document of the Conversation 
        conversation.setConversationID(this.database.createId());
        let convDoc = this.database.doc(`chats/${conversation.conversationID}`);
        convDoc.set(Object.assign({}, conversation));

        //Part to update the Users with the conversationID
        for (let i = 0; i < conversation.userIDList.length; i++) {
          this.addConversationIDToUser(conversation.userIDList[i], conversation.conversationID);
        }
        resolve(conversation);
      } else {
        reject();
      }
    })
  }

  AddMessageToChat(conversationID: string, message: Message) {

    let fireMessage = new FirebaseMessage();
    let refConversation = this.database.doc(`chats/${conversationID}`).collection('Messages');
    const messageID = this.database.createId();
    fireMessage.setMessageID(messageID);
    fireMessage.setText(message.text);
    fireMessage.setTime(message.time);
    fireMessage.setUserID(message.user.userID);

    let userID: string[] = [];
    message.usersHasReadMessage.forEach(user => {
      if (user) {
        userID.push(user.userID);
      }
    })
    fireMessage.setUserHasReadMessage(userID);

    refConversation.doc(messageID).set(Object.assign({}, fireMessage));


  }

  AddFriendRequestToUsers(user: User, friend: User) {
    const userFriendRequestIDs: string[] = [];
    friend.getFriendRequests().forEach(userFriendRequest => {
      userFriendRequestIDs.push(userFriendRequest.getUserID());
    });

    userFriendRequestIDs.push(user.getUserID());

    this.database.collection('users').doc(friend.userID).update({
      friendRequests: `${userFriendRequestIDs}`
      // updating friendrequests with the new friendID, might need to add check that it is not duplicated
    });


  }

  AcceptFriendRequest(user: User, newFriend: User) {
    // userpart
    const userFriendRequestIDs: string[] = [];
    const userFriendsIDs: string[] = [];

    user.getFriendRequests().forEach(userFriendRequest => {
      if (!user.getUserID().localeCompare(newFriend.getUserID())) {
        userFriendRequestIDs.push(userFriendRequest.getUserID());
      }
    });

    user.getFriends().forEach(userFriend => {
      userFriendsIDs.push(userFriend.getUserID());
    });
    userFriendsIDs.push(newFriend.getUserID()); // push new friendID

    this.database.collection('users').doc(user.getUserID()).update({
      friendrequests: `${userFriendRequestIDs}`,
      friends: `${userFriendsIDs}`
      // remove friendrequest and add as friend instead
    });


    // friendpart
    let friendFriendRequestIDs: string[] = [];
    let friendFriendsIDs: string[] = [];
    this.fbImport.importFriendRequestIDs(newFriend.getUserID()).then(friendRequestIDs => {
      friendFriendRequestIDs = friendRequestIDs;
    });
    friendFriendRequestIDs.splice(friendFriendRequestIDs.indexOf(user.getUserID()), 1); // remove friend request

    this.fbImport.importFriendIDs(newFriend.getUserID()).then(friendIDs => {
      friendFriendsIDs = friendIDs;
    });
    friendFriendsIDs.push(user.getUserID());  // push user as friend

    this.database.collection('users').doc(newFriend.getUserID()).update({
      friendrequests: `${friendFriendRequestIDs}`,
      friends: `${friendFriendsIDs}`
      // remove friendrequest and add as friend instead
    });
  }

}


