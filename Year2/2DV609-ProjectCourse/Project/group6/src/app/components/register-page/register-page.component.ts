import { Component, OnInit } from '@angular/core';

//Angular Imports
import {FormGroup, Validators, FormControl} from '@angular/forms';

//Service
import { MainControllerService } from '../../services/main-controller.service';
@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {
  register = new FormGroup({
    name: new FormControl('', Validators.required),
    password: new FormControl('',Validators.required),
    email: new FormControl('',Validators.required)
  });
  constructor(
    public mainController: MainControllerService
  ) { }

  ngOnInit(): void {
  }


  registerUser(value){

    let result = this.mainController.attemptRegisterNewUser(value.email,value.password,value.name); 

    console.log(result); 
  }

}
