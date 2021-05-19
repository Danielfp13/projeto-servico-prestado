import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../login/usuario';
import { environment } from '../../environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  urlApi: string = environment.apiURLBase + '/usuarios';
  tokenURL: string = environment.apiURLBase + environment.obterTokenUrl;
  clientId: string = environment.clientId;
  clientSecret: string = environment.clientSecret;
  jwtHelper: JwtHelperService = new JwtHelperService();


  constructor(private http: HttpClient) { }

  insertUsuario(usuario: Usuario): Observable<any> {
    return this.http.post<Usuario>(`${this.urlApi}`, usuario);
  }

  tentarLogar(username: string, password: string): Observable<any> {
    const params = new HttpParams()
      // .append('username',username)
      // .append('password',password)
      //  .append('grant_type','password');
      .set('username', username)
      .set('password', password)
      .set('grant_type', 'password');
    console.log('Basic ' + btoa(`${this.clientId}:${this.clientSecret}`));

    const headers = {
      'Authorization': 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
      'Content-Type': 'application/x-www-form-urlencoded',
    };

    return this.http.post(this.tokenURL, params, { headers: headers });
  }

  obterToken() {
    const tokenString = localStorage.getItem('access_token');
    if (tokenString) {
      const token = JSON.parse(tokenString).access_token;
      return token;
    }
    return null;
  }

  isAutheticated(): boolean {

    const token = this.obterToken();
    if (token) {
      const expired = this.jwtHelper.isTokenExpired(token);
      return !expired;
    }
    return false;
  }

  enserrarSessao() {
    localStorage.removeItem('access_token');
  }

  getUsuarioAutenticado() {
    const token = this.obterToken();
    if (token) {
      const usuario = this.jwtHelper.decodeToken(token).user_name;
      return usuario;
    }
    return null;
  }

}
