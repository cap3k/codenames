import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes, ActivatedRoute} from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlateauComponent } from './plateau/plateau.component';
import { AppConfigService } from './app-config.service';

import {HttpClientModule} from '@angular/common/http';
import { JeuComponent } from './jeu/jeu.component';
import { ChatComponent } from './chat/chat.component';

import { HttpClientModule } from '@angular/common/http';


const routes: Routes = [
    { path: 'plateau/:id', component: PlateauComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    PlateauComponent,
    JeuComponent,
    ChatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [AppConfigService],
  bootstrap: [AppComponent]
})
export class AppModule { }
