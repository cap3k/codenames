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
  private chat: Chat = new Chat("");

  constructor(private chatService: ChatService) { }

  ngOnInit() {}

  ajouterChat(){
    this.chat.partie = {id: 1};
    this.chat.joueur = {id: 7};
    this.chatService.save(this.chat);
  }
}
