import { Token } from '@angular/compiler/src/ml_parser/lexer';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Usuario } from './usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  usuario: Usuario;
  username: string;
  password: string;
  loginError: boolean;
  cadastrando: boolean;
  mensagem: string;
  errors: string[] =[];

  constructor(
    private router: Router,
    private service: AuthService

  ) { 
    this.usuario = new Usuario();
  }

  inserirUsuario(){
    this.service.insertUsuario(this.usuario).subscribe(
      response=>{
        this.mensagem = 'Cadastro realizado com sucesso! Efetue o login.'
        this.errors = [];
        this.cadastrando = false;
        this.usuario = new Usuario();
      },responseError=>{
        console.log(responseError.error.errons)
        this.errors = responseError.error.erros;
        this.mensagem = null;
      }
    )
  }

  onSubmit(){
    console.log(`Usuarior: ${this.usuario.username}, Pass: ${this.usuario.password}.`)
    this.service.tentarLogar(this.usuario.username, this.usuario.password).subscribe(
      response => {
        const access_token = JSON.stringify(response);
        localStorage.setItem('access_token',access_token);
        this.router.navigate(['/home']);
      },responseError =>{

        this.errors = ['Usuarios e ou senha incorretos!'];
      }
    ) 



  }

  preparaCadastrar(event){
    event.preventDefault();
    this.cadastrando = true;
    this.mensagem = null;
    this.errors = null;

  }

  cancelaCadastro(){
    this.cadastrando = false;
    this.errors = null;
    this.mensagem = null;
  }

}
