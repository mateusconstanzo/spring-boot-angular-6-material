import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutes } from '../app-routes.enum';

import { IndexComponent } from './index/index.component';
import { FormComponent } from './form/form.component';

import { AuthGuard } from '../commons/auth-guard.service'

const routes: Routes = [
    { path: AppRoutes.EVENTS, component: IndexComponent, canActivate: [AuthGuard] },
    { path: AppRoutes.EVENTS_NEW, component: FormComponent, canActivate: [AuthGuard] },
    { path: AppRoutes.EVENTS_EDIT, component: FormComponent, canActivate: [AuthGuard] },
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class EventRoutingModule { }
