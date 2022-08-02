import { AuthService } from './../../services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { Credenciais } from './../../models/credenciais';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  creds : Credenciais = {
    email: '',
    senha: ''
  }

  email = new FormControl(null, Validators.email);
  senha = new FormControl(null, Validators.minLength(3));

  constructor(private toastr: ToastrService,
    private service: AuthService,
    private router: Router) { }

  ngOnInit(): void {}

  validaCampos(): boolean {
    return this.email.valid && this.senha.valid;
  }

  logar() {
    this.service.authenticate(this.creds).subscribe(resposta => {
      this.service.successfulLogin(resposta.headers.get('Authorization').substring(7));
      this.router.navigate(['']);
    }, () => {
      this.toastr.error('Usuário e/ou senha inválido!');
    });
  }

}
