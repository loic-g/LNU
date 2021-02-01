import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { LoginHandlerService } from '../../services/login-handler.service';



//Service
import { MainControllerService } from '../../services/main-controller.service';
import { Router } from '@angular/router';
@Component({
  selector: 'login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  loginMsg: string;
  loginAttempt: boolean = false;

  login = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  constructor(
    public controller: MainControllerService,
    public router: Router) { }

  ngOnInit(): void {
  }

  loginUser(value) {
    let result = this.controller.attemptLogin(value.email, value.password);
    console.log(result);
    this.loginAttempt = true;
    if (result) {
      //Print that login worked on screen
      this.loginMsg = "Login Successful";
    } else {
      this.loginMsg = "Login Failed, please try again"
    }
  }


}


