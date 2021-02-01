import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { environment } from "src/environments/environment";


import { FontAwesomeModule, FaIconLibrary } from '@fortawesome/angular-fontawesome';

// Awesome font
import { faSquare, faAngleDown, faExclamationCircle, faBell, faUserPlus,faEnvelopeSquare, 
  faUserFriends, faSearch,faKey, faUser, faAddressBook, faSignOutAlt,faTimesCircle, faCheckCircle} from '@fortawesome/free-solid-svg-icons';

// Firebase imports
import { AngularFireModule } from '@angular/fire';
import { AngularFirestoreModule } from '@angular/fire/firestore';

// Component
import { ConversationComponent } from './components/conversation/conversation.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { AccountPageComponent } from './components/account-page/account-page.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { FooterComponent } from './components/footer/footer.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { TestFirebaseComponent } from './components/test-firebase/test-firebase.component';
import { AddFriendsComponent } from './components/add-friends/add-friends.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { GroupChatsComponent } from './components/group-chats/group-chats.component';
import { FriendsListComponent } from './components/friends-list/friends-list.component';

//Service
import { FirebaseAuthConnectorService } from './services/firebaseHandler/firebase-auth-connector.service';
import { AuthGuard } from './guard/auth.guard';





@NgModule({
  declarations: [
    AppComponent,
    ConversationComponent,
    LoginPageComponent,
    AccountPageComponent,
    SidebarComponent,
    HomePageComponent,
    FooterComponent,
    RegisterPageComponent,
    TestFirebaseComponent,
    AddFriendsComponent,
    MyProfileComponent,
    GroupChatsComponent,
    FriendsListComponent
  ],
  imports: [
    BrowserModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFirestoreModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {
        path:'',
        children:[
          {path: '',redirectTo: '/login', pathMatch: 'full'},
          {path: 'home',component:HomePageComponent,canActivate: [AuthGuard]},
          {path: 'login',component: LoginPageComponent},
          {path: 'test',component:TestFirebaseComponent, canActivate: [AuthGuard]},
          {path: 'register',component:RegisterPageComponent},
          {path: 'addFriend',component:AddFriendsComponent, canActivate: [AuthGuard]},
          {path: 'myProfile',component:MyProfileComponent, canActivate: [AuthGuard]},
          {path: 'groupChats',component:GroupChatsComponent, canActivate: [AuthGuard]},
          {path: 'friendsList',component:FriendsListComponent, canActivate: [AuthGuard]},
          {path: 'conversation/:convID',component:ConversationComponent, canActivate: [AuthGuard]}
        ]}
    ])
  ],
  providers: [FirebaseAuthConnectorService,AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private library: FaIconLibrary) {
    library.addIcons(faSquare, faAngleDown, faExclamationCircle, faBell, faUserPlus, faEnvelopeSquare,
      faSignOutAlt,faAddressBook, faUserFriends, faSearch,faKey,faUser,faTimesCircle,faCheckCircle);

  }
 }
