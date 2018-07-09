import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

import { AppRoutes } from '../../app-routes.enum'

import { AuthService } from '../auth.service'
import { TokenService } from '../../commons/token.service'

import { Login, Token } from '../models'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private tokenService: TokenService,
  ) { }

  ngOnInit() {

    this.form = this.formBuilder.group({
      username: ['', [
        Validators.required,
      ]],
      password: ['', [
        Validators.required,
      ]]
    });

  }

  login(login: Login) {

    this.authService
      .login(login)
      .subscribe(

        (token: Token) => {

          this.tokenService.save(token.token);

          this.router.navigate([`/${AppRoutes.EVENTS}`]);

        },
        error => this.snackBar.open('Username e password incorretos', '', { duration: 3000 })
      );


  }

}