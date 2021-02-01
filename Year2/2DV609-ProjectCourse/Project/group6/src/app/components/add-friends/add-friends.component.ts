//Angular Imports
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable, combineLatest } from 'rxjs';

//Controller
import { MainControllerService } from '../../services/main-controller.service';

//Models
import { User } from 'src/app/models/User';
import { map, startWith } from 'rxjs/operators';


@Component({
  selector: 'app-add-friends',
  templateUrl: './add-friends.component.html',
  styleUrls: ['./add-friends.component.css']
})
export class AddFriendsComponent implements OnInit {
  users$: Observable<User[]>;
  filter: FormControl;
  filter$: Observable<string>;
  filteredUsers$: Observable<User[]>;

  constructor(
    public controller: MainControllerService
  ) {
    this.users$ = this.controller.getAllUsers();
    this.filter = new FormControl('');
    this.filter$ = this.filter.valueChanges.pipe(startWith(''));
    this.filteredUsers$ = combineLatest(this.users$, this.filter$).pipe(
      map(([users, filterString]) => users.filter(user => {
        if (filterString === "") {
          return false;
        } else if (user.name.toLowerCase().indexOf(filterString.toLowerCase()) !== -1) {
          let connectedUser = this.controller.getUser();
          //console.log(connectedUser);
          //console.log(user );
          if(connectedUser != null){
            if (connectedUser.userID === user.userID) {
            
              return false;
            }
            return true;
          }
          
        } else {
          return false;
        }
      }))
    )
  }

  ngOnInit(): void {

  }

  addFriend(userID: string) {
    //console.log(userID);
    this.controller.attemptSendFriendRequest(userID);
    

  }

}
