import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { AppRoutes } from './app-routes.enum';

const routes: Routes = [
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule { }
