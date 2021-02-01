//Angular 
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

//Models
import { User } from '../models/User';
import { Conversation } from '../models/Conversation';
import { Message } from '../models/Message';

//Firebase
import { FirebaseConnectorService } from './firebaseHandler/firebase-connector.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MainControllerService {
  private loggedInUser: User;
  private friends: User[];
  private conversations: Conversation[];
  private FBConnection: FirebaseConnectorService;

  constructor(FBConnection: FirebaseConnectorService, private router: Router) {


    this.FBConnection = FBConnection;
    this.loggedInUser = null;
    this.friends = [];
    this.conversations = [];
    let userFirebase = JSON.parse(localStorage.getItem('user'));
    if(userFirebase != null){
      let userUID = userFirebase.uid;
    this.FBConnection.importUser(userUID).then(user =>{
      if(user !=null){
        //console.log("LOGGED USER");
        //console.log(user);
        this.loggedInUser = user
        
      }
      //console.log(this.loggedInUser);
    })
    }
    
    
  }
  get loggedUser(): User{
    let user : User = this.FBConnection.userLoggedIn; 
    if( user != null){
      this.loggedInUser = user; 
      return user; 
    }else{
      return null;
    }
    
    
  }
  


  public attemptFriendList(userID: string): Observable<User[]>{
    if(userID != null){
      return this.FBConnection.getFriends(userID);
    }else{
      return null; 
    }
    
  }

  public async attemptLogin(email: string, password: string) {

    //Need to fix the output (but the functionality works)
    this.loggedInUser = await this.FBConnection.loginUser(email, password);
    /*
    this.FBConnection.loginUser(email, password).then(userPromise => {
      //console.log(userPromise);
      if (userPromise) {
        //console.log("WENT HERE ");
        user.setName(userPromise.name);
        user.setEmail(userPromise.email);
        user.setUserID(userPromise.userID);
        user.setConversations(userPromise.conversations);
        user.setFriends(userPromise.friends);
        user.setFriendRequests(userPromise.friendRequests);
        console.log(user);
      }

    }).catch((error) => {
      console.log("Promise rejected with " + error);
      return false;
      
    });
    
    if(user !== null){
      if(user.userID !== ""){
        return true;
      }
    }else{
      return false;
    }
    */
  }

  public attemptRegisterNewUser(email: string, password: string, name: string) : boolean {
    //Need to fix the output (but the functionality works)

    //change to handle returned value from firebase
    this.FBConnection.registerUser(email, password, name).then(user => {
      if(user){
        return true; 
      }else {
        return false;
      }
    }) 
    //change the view to the newly created userinterface or some confirmation on successful creation
    
    return false; 
      //change the view to notify that the account was not created
    
  }

  public attemptUpdateConversation(conversation: Conversation, message: Message) {
    //logic for updating the conversation in the database and in the view
  }

  public attemptUpdateProfile(user: User) {
    //update profile information in the database
  }

  public attemptSendFriendRequest(userID: string) {
    let friend = new User; 
    //console.log(userID)
    let loggedUser = this.FBConnection.userLoggedIn; 
    console.log(loggedUser);
    //this.loggedInUser = loggedUser; 
        
    this.FBConnection.importUser(userID).then(user =>{
      console.log(user)
      friend.setUserID(userID);
      friend.setEmail(user.getEmail());
      friend.setName(user.name);
      friend.setFriends(user.friends);
      friend.setConversations(user.conversations);
      friend.setFriendRequests(user.friendRequests);
      //console.log(user);
      console.log(friend);
      if(user !=null){
        //console.log(user);
        this.FBConnection.sendFriendRequest(this.loggedInUser, friend);
      }
     // console.log(user);
      //console.log(this.loggedInUser);
      
      //console.log(friend);
     
    })
    
  }

  public attemptSendMessage(convID: string, message: Message ){
    message.setUser(this.loggedInUser);
    console.log(); 
    message.setTime(Date.now());
    this.FBConnection.sendMessage(convID,message); 
  }
  public attemptAcceptFriendRequest(user: User) {
    this.FBConnection.acceptFriendRequest(this.loggedInUser, user);

  }

  public attemptDeleteFriend(user: User) {
    //remove friend, update visuals
  }

  public attemptDeleteMessage(conversation: Conversation, message: Message) {
    //remove message from the conversation, update visuals
  }

  public getUser(): User {
    return this.loggedInUser;
  }

  public getFriends(): User[] {
    return this.friends;
  }

  public getConversations(): Conversation[] {
    return this.conversations;
  }

  public getAllUsers(){
    return this.FBConnection.importUsersList(); 
  }

  public getConversation(convID: string): Observable<Message[]>{
    return this.FBConnection.messagesOfConversationObs(convID); 
  }
}
