import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSliderModule } from '@angular/material/slider';
import { MatIconModule } from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import { AdminComponent } from './admin/admin.component';
import { ClientComponent } from './client/client.component';
import { LayoutComponent } from './layout/layout.component';
import { AngularSplitModule } from 'angular-split';
import {MatDialogModule} from '@angular/material/dialog';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import { MatcomponentComponent } from './matcomponent/matcomponent.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {  MatInputModule } from '@angular/material';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {HttpClientModule} from '@angular/common/http';
import {MatExpansionModule} from '@angular/material/expansion';

import {MatButtonModule} from '@angular/material/button';
import { DragDirective } from './drag-drop.directive';


@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    ClientComponent,
    LayoutComponent,
    MatcomponentComponent,
    DragDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSliderModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    AngularSplitModule.forRoot(),
      MatDialogModule,
      FormsModule,
      ReactiveFormsModule,
      MatFormFieldModule,
      MatInputModule,
      MatRadioModule,
      MatSelectModule,
      HttpClientModule,
      MatExpansionModule

  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [MatcomponentComponent]
})
export class AppModule { }
