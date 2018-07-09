import { Injectable } from '@angular/core';

@Injectable()
export class TokenService {

    TOKEN_KEY = 'token';

    constructor(
    ) { }

    public save(token: string) {
        sessionStorage.removeItem(this.TOKEN_KEY);
        sessionStorage.setItem(this.TOKEN_KEY, token);
    }

    public get(): string {
        return sessionStorage.getItem(this.TOKEN_KEY);
    }

}
