import { Component, OnInit} from '@angular/core';


import { Router } from '@angular/router';

import { FirebaseAuthConnectorService } from '../../services/firebaseHandler/firebase-auth-connector.service';
import { MainControllerService } from '../../services/main-controller.service';
import { User } from 'src/app/models/User';
import { Observable } from 'rxjs';

@Component({
  selector: 'sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
    
  constructor(
    public authService: FirebaseAuthConnectorService,
    public controller: MainControllerService, 
    public router: Router
    ) { 
      
        
      }

  ngOnInit(): void {
    
  }
  

  signOut(){
    this.authService.logout();
    this.router.navigate(['login']);
  }

  

}
