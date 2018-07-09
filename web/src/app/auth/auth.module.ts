import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { MaterialModule } from '../material/material.module'
import { CommonsModule } from "../commons/commons.module"

import { AuthService } from './auth.service'

import { LoginComponent } from './login/login.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    CommonsModule
  ],
  providers: [
    AuthService,
  ],
  declarations: [
    LoginComponent
  ]
})
export class AuthModule { }
