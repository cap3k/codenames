import { Injectable } from '@angular/core';
import { Chat } from './chat/chat';
import { AppConfigService } from './app-config.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ChatService {
  public chatAsync: any = null;
  constructor(private httpClient: HttpClient, private appConfig: AppConfigService) { }

  findAllAsync(){
    if(this.chatAsync==null){
      this.chatAsync = this.httpClient.get("http://localhost:8080/api/chat")
    }
    return this.chatAsync;
  }

  refresh() {
    this.chatAsync = null;
  }

}
