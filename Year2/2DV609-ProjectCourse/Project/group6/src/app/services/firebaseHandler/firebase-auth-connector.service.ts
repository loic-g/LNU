
//Angular Imports
import { Injectable, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

//Firebase Import
import { AngularFireAuth } from '@angular/fire/auth';
import { User } from 'firebase';
import { AngularFirestore } from '@angular/fire/firestore';

//models
import { FirebaseUser } from 'src/app/models/FirebaseUser';







@Injectable({
  providedIn: 'root'
})
export class FirebaseAuthConnectorService {
  user: FirebaseUser;
  userDetails: User;
  isLoggedIn: boolean;
  
  constructor(
    public fireAuth: AngularFireAuth,
    public firestore: AngularFirestore,
    private router: Router,
    public ngZone: NgZone

  ) { 
    this.fireAuth.authState.subscribe(user => {
      if (user) {
        this.userDetails = user;
        this.isLoggedIn = true;
        localStorage.setItem('user', JSON.stringify(this.userDetails));
        this.firestore
          .doc(`users/${this.userDetails.uid}`)
          .ref.get()
          .then(doc => {
            if (doc.exists) {
              this.user = doc.data() as FirebaseUser;
            }
          });
      } else {
        this.isLoggedIn = false;
        localStorage.setItem('user', null);
      }
    })
  }

  get isLogged(): boolean {
    const user = JSON.parse(localStorage.getItem('user'));
    return (user !== null) ? true : false;
  }
   
  async login(
    email: string,
    password: string
  ): Promise<firebase.auth.UserCredential> {
    return this.fireAuth.signInWithEmailAndPassword(email, password);
      
  }

  async register(
    email: string,
    password: string
  ): Promise<firebase.auth.UserCredential> {
    return this.fireAuth.createUserWithEmailAndPassword(email, password);
  }

  logout(): void {
    this.fireAuth.signOut().then(() => {
      this.userDetails = null;
      localStorage.removeItem('user');
      this.router.navigate(['login']);
    });
  }

  uid: Observable<string> = this.fireAuth.authState.pipe(
    map(authState => {
      if (!authState) {
        return null;
      } else {
        return authState.uid;
      }
    })
  );


  
}


