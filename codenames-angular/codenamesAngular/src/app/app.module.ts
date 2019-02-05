import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppConfigService } from './app-config.service';
import { PlateauComponent } from './plateau/plateau.component';
import { JeuComponent } from './jeu/jeu.component';
import { ChatComponent } from './chat/chat.component';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
    { path: 'jeu', component: JeuComponent },
    { path: 'plateau', component: PlateauComponent },
    { path: 'chat', component: ChatComponent }
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
    FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [AppConfigService],
  bootstrap: [AppComponent]
})
export class AppModule { }
