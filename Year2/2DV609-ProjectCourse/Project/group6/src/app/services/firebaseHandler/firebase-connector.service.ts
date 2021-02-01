import { Injectable } from '@angular/core';

//Firebase Import/Export
import { FirebaseExporterService } from './firebase-exporter.service';
import { FirebaseImporterService } from './firebase-importer.service';
import { FirebaseAuthConnectorService } from './firebase-auth-connector.service';

//Model
import { User } from 'src/app/models/User';
import { FirebaseUser } from 'src/app/models/FirebaseUser';
import { of, Observable } from 'rxjs';
import { error } from 'protractor';
import { Message } from 'src/app/models/Message';
import { FirebaseMessage } from 'src/app/models/FirebaseMessage';

@Injectable({
  providedIn: 'root'
})
export class FirebaseConnectorService {

  constructor(
    private fbExporter: FirebaseExporterService,
    private fbImporter: FirebaseImporterService,
    private fbAuth: FirebaseAuthConnectorService
  ) {

  }


  public async registerUser(email: string, password: string, name: string): Promise<User> {

    let userCredential = await this.fbAuth.register(email, password);
    let userUID = userCredential.user.uid;
    let firebaseUser = new FirebaseUser();
    firebaseUser.setEmail(email);
    firebaseUser.setUserID(userUID);
    firebaseUser.setName(name);

    let Usercreated = await this.fbExporter.addUser(firebaseUser);
    let user = new User();
    return new Promise<User>((resolve, reject) => {
      if (Usercreated) {

        user.setEmail(email);
        user.setName(name);
        user.setUserID(userUID)

        resolve(user);
      } else {
        reject()
      }

    });


  }

  public messagesOfConversationObs(convID: string)  : Observable<Message[]>{
    return this.fbImporter.importMessagesOfConversationObs(convID); 
  }

  //Some bugs to fix 
  async loginUser(email: string, password: string): Promise<User> {
    let authCredentials = await this.fbAuth.login(email, password);

    return await this.fbImporter.importUser(authCredentials.user.uid);

  }
  public get userLoggedIn(): User {
    let fUser = this.fbAuth.user;
    if (fUser) {
      this.fbImporter.importUser(fUser.userID).then(user => {
        if (user) {
          return user;
        } else {
          return null;
        }

      }).catch(error => {
        console.log("The user could not be retrived: " + error)
        return null
      })
    } else {
      return null;
    }


  }

  public getFriends(userID: string): Observable<User[]> {

    if (userID != null) {
      let friends: User[] = [];
      this.fbImporter.importFriendIDs(userID).then(userIDS => {
        userIDS.forEach(async userid => {
          if (userid != null) {
            let user = await this.fbImporter.importFriend(userid);
            if (user) {
              friends.push(user);
            }
          }

        })
      }).catch(error => {
        console.log("Error occured: " + error);
        return null;
      });
      if (friends != null) {
        return of(friends);
      } else {
        return null;
      }

    }

  }

  public 


  public async importUser(userID: string): Promise<User> {
    let user = await this.fbImporter.importUser(userID);
    return new Promise<User>((resolve, reject) => {


      if (user) {
        resolve(user);
      } else {
        reject()
      }
    })
  }

  public sendMessage(convID: string,message: Message){
    
    this.fbExporter.AddMessageToChat(convID,message);
  }

  public sendFriendRequest(user: User, friend: User) {
    this.fbExporter.AddFriendRequestToUsers(user, friend);
  }

  public acceptFriendRequest(user: User, friend: User) {
    this.fbExporter.AcceptFriendRequest(user, friend);
  }

  public declineFriendRequest(user: User, friend: User) {

  }

  public importUsersList() {
    return this.fbImporter.importUsers;
  }

}
