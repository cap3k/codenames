import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes, ActivatedRoute} from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlateauComponent } from './plateau/plateau.component';
import { AppConfigService } from './app-config.service';


const routes: Routes = [
{ path: 'plateau/:id', component: PlateauComponent },
];




@NgModule({
  declarations: [
    AppComponent,
    PlateauComponent
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
