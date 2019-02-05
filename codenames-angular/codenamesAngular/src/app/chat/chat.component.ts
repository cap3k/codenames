import { Component, OnInit } from '@angular/core';
import{ Router } from '@angular/router';
import { Chat } from './chat';
import { ChatService } from '../chat.service'

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
  providers: [ ChatService ]
})
export class ChatComponent implements OnInit {

  constructor(private chatService: ChatService) { }

  ngOnInit() {
  }

}
