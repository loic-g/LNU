import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';

//Services
import { MainControllerService } from '../../services/main-controller.service';

//Model
import { Message } from 'src/app/models/Message';


@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class ConversationComponent implements OnInit {

  messages$: Observable<Message []>;
  messageToSend = new FormGroup({
    message: new FormControl('', Validators.required)
  });

  convID: string = "";

  constructor(
    public controller: MainControllerService,
    private activatedRoute: ActivatedRoute,
    private route: Router


  ) { }

  ngOnInit(): void {
    this.messages$ = this.activatedRoute.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.controller.getConversation(params.get('convID')))

    )
    console.log(this.messages$);

  }

  sendMessage(value) {
    let msg = value.message;
    let convID = this.route.url.substring(14, this.route.url.length);
    
    if(msg){
      console.log(msg);
      let message = new Message(msg);
      this.controller.attemptSendMessage(convID,message);
    }


  }


}
