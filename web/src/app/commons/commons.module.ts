import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FieldErrorDisplayComponent } from './field-error-display/field-error-display.component';

import { TokenService } from './token.service'
import { AuthGuard } from './auth-guard.service'

@NgModule({
  imports: [
    CommonModule
  ],
  exports: [
    FieldErrorDisplayComponent,
  ],
  providers: [
    TokenService,
    AuthGuard,
  ],  
  declarations: [
    FieldErrorDisplayComponent,
  ]
})
export class CommonsModule { }
