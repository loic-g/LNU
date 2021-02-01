//Angular imports
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { of, Observable } from 'rxjs';

//Firebase imports
import { AngularFirestore } from '@angular/fire/firestore';

//Models
import { User } from '../../models/User';
import { FirebaseUser } from 'src/app/models/FirebaseUser';
import { FirebaseConversation } from 'src/app/models/FirebaseConversation';
import { Conversation } from 'src/app/models/Conversation';
import { Message } from '../../models/Message';
import { FirebaseMessage } from 'src/app/models/FirebaseMessage';


@Injectable({
  providedIn: 'root'
})
export class FirebaseImporterService {
  private fireStore: AngularFirestore;

  constructor(fireStore: AngularFirestore) {
    this.fireStore = fireStore;
    console.log('fireStore initiated');
  }

  async importUser(userID: string): Promise<User> {
    return new Promise<User>((resolve, reject) => {
      let newUser: User = new User();

      this.fireStore.collection('users').doc(userID).snapshotChanges().pipe(map(dataVar => {
        return dataVar.payload.data() as FirebaseUser;
      })).forEach(firebaseUser => {
        // Import all fields to a new User

        newUser.setUserID(firebaseUser.userID);
        newUser.setName(firebaseUser.name);
        newUser.setEmail(firebaseUser.email);

        //fetch and add conversations
        let conversationArray: Conversation[] = []
        firebaseUser.conversations.forEach(conversationID => {
          this.importConversation(conversationID).then(conversation => {
            conversationArray.push(conversation);

          });
        });
        newUser.setConversations(conversationArray);

        //fetch and add friends
        let friendsArray: User[] = [];
        firebaseUser.friends.forEach(friendID => {
          this.importFriend(friendID).then(friend => {
            friendsArray.push(friend);
          });
        });
        newUser.setFriends(friendsArray);

        let friendRequestArray: User[] = [];
        firebaseUser.friendRequests.forEach(friendRequestID => {
          this.importFriend(friendRequestID).then(friendRequest => {
            friendRequestArray.push(friendRequest);
          });
        });
        newUser.setFriendRequests(friendRequestArray);

      });

      resolve(newUser);
    });

  }

  async importFriend(userID: string): Promise<User> {
    return new Promise<User>((resolve, reject) => {
      const newUser: User = new User();

      this.fireStore.collection('users').doc(userID).snapshotChanges().pipe(map(dataVar => {
        return dataVar.payload.data() as FirebaseUser;
      })).forEach(user => {
        if (user) {
          // Only import information visible to others
          newUser.setUserID(user.userID);
          newUser.setName(user.name);
          newUser.setEmail('');
        }

      });
      resolve(newUser);
    });

  }

  /* Method to get the conversation IDs a user is part of */
  async getConversationIDsUser(userID: string): Promise<string[]> {
    return new Promise<string[]>((resolve, reject) => {
      const newUser = new FirebaseUser();
      this.fireStore.collection('users').doc(userID).snapshotChanges().pipe(map(dataVar => {
        return dataVar.payload.data() as FirebaseUser;
      })).forEach(user => {

        newUser.setConversations(user.conversations);
        resolve(user.conversations);
      });

    });


  }
  sortByTime(message1: Message,message2: Message){
    if (message1.time < message2.time)
      return -1;
    if (message1.time > message2.time)
      return 1;
    return 0;
  }

  public importMessagesOfConversationObs(conversationID: string): Observable<Message[]> {
    return this.fireStore.collection('chats').doc(conversationID).collection('Messages').snapshotChanges().pipe(map(dataVar => {
      return dataVar.map(messageDoc => {
        const fmessage = messageDoc.payload.doc.data() as FirebaseMessage;
        const message = new Message(fmessage.text);
        this.importFriend(fmessage.userID).then(user => {
          if (user) {
            message.setUser(user);
          }

        })
        message.setMessageID(fmessage.messageID);
        message.setTime(fmessage.time);

        let users: User[] = [];
        fmessage.usersHasReadMessage.forEach(userIDs => {
          this.importFriend(userIDs).then(user => {
            if (user) {
              users.push(user);
            }
          });
        });
        message.setUserHasReadMessage(users);

        return message;
      }).sort(this.sortByTime);
    }))

  }


  async importConversation(conversationID: string): Promise<Conversation> {
    return new Promise<Conversation>((resolve, reject) => {
      const newConversation = new Conversation(conversationID);

      this.fireStore.collection('chats').doc(conversationID).snapshotChanges().pipe(map(conversationDataVar => {
        return conversationDataVar.payload.data() as FirebaseConversation;
      })).forEach(conversation => {
        newConversation.setConversationID(conversation.conversationID);

        this.fireStore.collection('chats').doc(conversationID).collection('Messages').snapshotChanges().pipe(map(messageDataVar => {
          return messageDataVar.map(firebaseMessageDocument => {
            const firebaseMessage = firebaseMessageDocument.payload.doc.data() as FirebaseMessage;

            return firebaseMessage;
          });
        })).subscribe(messages => {
          const messageList: Message[] = new Array<Message>();
          messages.forEach(message => {
            //NEED TO FIX HERE
            //messageList.push(new Message(message.text, message.userID));
          });
          newConversation.setMessages(messageList);
        });
      });
      resolve(newConversation);
    });
  }

  async importFriendRequestIDs(userID: string): Promise<string[]> {
    let friendRequests: string[] = [];
    return new Promise<string[]>((resolve, reject) => {
      this.fireStore.collection('users').doc(userID).snapshotChanges().pipe(map(dataVar => {
        return dataVar.payload.data() as FirebaseUser;
      })).forEach(user => {
        friendRequests = user.getFriendRequests();
      });
      resolve(friendRequests);
    });
  }

  async importFriendIDs(userID: string): Promise<string[]> {
    let friendIDs: string[] = [];
    return new Promise<string[]>((resolve, reject) => {
      this.fireStore.collection('users').doc(userID).snapshotChanges().pipe(map(dataVar => {
        return dataVar.payload.data() as FirebaseUser;
      })).forEach(user => {
        friendIDs = user.getFriends();
      });
      resolve(friendIDs);
    });
  }



  get importUsers(): Observable<User[]> {

    let userList: User[] = [];
    this.fireStore.collection<FirebaseUser>('users').valueChanges().forEach(firebaseUserListArray => {
      firebaseUserListArray.forEach(firebaseUser => {

        let user = new User();
        user.setName(firebaseUser.name);
        user.setUserID(firebaseUser.userID);

        userList.push(user);

      })
    })
    if (userList != null) {
      return of(userList);
    } else {
      return null;
    }

  }

}
