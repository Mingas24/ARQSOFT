import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShopRoutingModule } from './shop-routing.module';
import { ShopAddComponent } from './shop-add/shop-add.component';
import { ShopEditComponent } from './shop-edit/shop-edit.component';



@NgModule({
  declarations: [
    ShopAddComponent,
    ShopEditComponent
  ],
  imports: [
    CommonModule,
    ShopRoutingModule
  ]
})
export class ShopModule { }
