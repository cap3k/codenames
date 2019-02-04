import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  public url: string ="http://192.168.1.103:8080/api/"
  constructor() {
}
}
