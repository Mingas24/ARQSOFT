import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from 'src/modules/home/home-page/home-page.component';

const routes: Routes = [
  { path: 'home-page', component: HomePageComponent},
  { path: '', redirectTo: '/home-page', pathMatch: 'full' },
  { path: '**', redirectTo: '/home-page', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
