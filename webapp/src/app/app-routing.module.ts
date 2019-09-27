import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  {path: 'admin', component: AdminComponent},
  {path: '', redirectTo: '/admin', pathMatch: 'full'},
  {path: 'layout/:id', component: LayoutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
