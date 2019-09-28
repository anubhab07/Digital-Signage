import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import {ClientComponent} from './client/client.component';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  {path: 'admin', component: AdminComponent},
  {path: 'client', component: ClientComponent},
  {path: '', redirectTo: '/admin', pathMatch: 'full'},
  {path: 'layout/:id', component: LayoutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
