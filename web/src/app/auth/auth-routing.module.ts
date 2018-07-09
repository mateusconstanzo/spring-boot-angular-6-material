import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutes } from '../app-routes.enum';

import { LoginComponent } from './login/login.component';

const routes: Routes = [
    { path: AppRoutes.LOGIN, component: LoginComponent },
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class AuthRoutingModule { }
