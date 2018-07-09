import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Login, Token } from './models'

@Injectable()
export class AuthService {

  endpoint: string = 'http://localhost:8080';

  constructor(
    private httpClient: HttpClient
  ) { }

  login(login: Login): Observable<Token> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    }

    const uri = this.endpoint + "/auth"
    return this.httpClient.post<Token>(uri, JSON.stringify(login), httpOptions);
  }


}
