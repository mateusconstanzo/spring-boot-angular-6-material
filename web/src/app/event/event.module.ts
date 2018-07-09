import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { MaterialModule } from '../material/material.module'
import { CommonsModule } from "../commons/commons.module"

import { IndexComponent } from './index/index.component';
import { FormComponent } from './form/form.component';

import { ApiService } from './api.service';
import { RemoveModalComponent } from './remove-modal/remove-modal.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    CommonsModule
  ],
  declarations: [
    IndexComponent,
    FormComponent,
    RemoveModalComponent,
  ],
  providers: [
    ApiService,
  ],
  entryComponents: [
    RemoveModalComponent
  ]
})
export class EventModule { }
