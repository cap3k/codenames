import { Component } from '@angular/core';
import { AppConfigService } from './app-config.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[AppConfigService]
})
export class AppComponent {
  constructor(private service: AppConfigService) { }
  }
