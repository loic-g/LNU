import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, FormControl } from '@angular/forms';


import { FirebaseExporterService } from '../../services/firebaseHandler/firebase-exporter.service';
import { FirebaseImporterService } from '../../services/firebaseHandler/firebase-importer.service';
import { User } from 'src/app/models/User';
import { FirebaseUser } from 'src/app/models/FirebaseUser';
import { FirebaseConversation } from 'src/app/models/FirebaseConversation';
import { FirebaseMessage } from 'src/app/models/FirebaseMessage';
import { FirebaseAuthConnectorService } from '../../services/firebaseHandler/firebase-auth-connector.service';
import { MainControllerService } from '../../services/main-controller.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-test-firebase',
  templateUrl: './test-firebase.component.html',
  styleUrls: ['./test-firebase.component.css']
})
export class TestFirebaseComponent implements OnInit {

  
 
  
  testAdd = new FormGroup({
    email: new FormControl(''),
    name: new FormControl('')
  });

  testUpdate = new FormGroup({
    userID: new FormControl(''),
    update: new FormControl('')
  });

  testDelete = new FormGroup({
    userID: new FormControl('')
  });

  testImportUser = new FormGroup({
    userID: new FormControl('')
  });
  testImportFriend = new FormGroup({
    userID: new FormControl('')
  });
  testImportConversation = new FormGroup({
    convID: new FormControl('')
  });

  testAuthRegister = new FormGroup({
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl('')
  });
  testAuthLogin = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });


  constructor(
    private fb: FormBuilder,
    private fExport: FirebaseExporterService,
    private fImport: FirebaseImporterService,
    private fAuth: FirebaseAuthConnectorService,
    public controller: MainControllerService,
    public router: Router

    ) { }

  ngOnInit(): void {
  }

  addUser(value){

    let user = new FirebaseUser()
    user.setEmail(value.email);
    user.setName(value.name)

    

    this.fExport.addUser(user);

  }

  updateUser(value){
    let user = new User()
    user.setEmail("123@email.com");
    user.setName(value.update)
    user.setUserID(value.userID)
    
  

    this.fExport.updateUser(value.userID,user)
  }

  deleteUser(value){
    this.fExport.deleteUser(value.userID);
  }

  createChat(){

    let conv = new FirebaseConversation();
    conv.setUserIDList(["dwf6EwxlwfaBDcgGxXqe","78SXkJ1bj4fffh5I5e54"]);
    this.fExport.AddChat(conv);
  }

  addMessage(){
    let message = new FirebaseMessage();
    message.setText("YO WHATS UP?");
    message.setUserID("7iq2zeRxNXF6qyTqmOdw");
   // message.setTime("12th Jan. 19:01");

    //this.fExport.AddMessageToChat("BK4gTAGfRQcPADN3Y3vF",message);
  }

  async importUserTest(value){
    let user = await this.fImport.importUser(value.userID);
    console.log(user);
  }
  async importFriendTest(value){
    let user = await this.fImport.importFriend(value.userID);
    console.log(user);
  }

  async importConvTest(value){
    let conversation = await this.fImport.importConversation(value.convID);
    console.log(conversation);
  }

  registerUser(value ){
    console.log(value);
    this.fAuth.register(value.email,value.password).then(e => {
      let user = new FirebaseUser();
      user.setEmail(value.email);
      user.setName(value.username);
      user.setUserID(e.user.uid);
      this.fExport.addUser(user);
    })
    
  }

  login(value){
    console.log(value);
    this.fAuth.login(value.email,value.password).then(e=>{
      console.log(e.user.uid);
    })
  }

  sendFriendRequest(){
    
    let friendID = "7NMH6oKr1bYeBnhbXStdYl8s06D3";

    
    this.controller.attemptSendFriendRequest(friendID);
  }

  
}
