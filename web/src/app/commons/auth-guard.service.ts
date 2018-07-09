import { Injectable } from '@angular/core';

import {
    CanActivate, Router,
    ActivatedRouteSnapshot,
    RouterStateSnapshot
} from '@angular/router';

import { TokenService } from './token.service'

import { AppRoutes } from '../app-routes.enum'

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(
        private tokenService: TokenService,
        private router: Router
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

        if (this.tokenService.get()) {
            return true;
        }

        this.router.navigate([`/${AppRoutes.LOGIN}`]);

        return false;
    }

}