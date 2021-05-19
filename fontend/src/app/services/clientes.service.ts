import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cliente } from '../clientes/Cliente';

@Injectable({
  providedIn: 'root',
})
export class ClientesService {
  constructor(private http: HttpClient) {}

  apiURL: string = environment.apiURLBase;

  salvar(cliente: Cliente): Observable<Cliente> {

    return this.http.post<Cliente>(`${this.apiURL}/clientes`,cliente);
  }

  listaCliente(): Observable<Cliente[]> {

    return this.http.get<Cliente[]>(`${this.apiURL}/clientes`);
  }

  alterarCliente(cliente: Cliente, id: any):Observable<any>{
    return this.http.put<Cliente>(`${this.apiURL}/clientes/${id}`,cliente);
  }

  getClienteById(id: any): Observable<Cliente>{
    return this.http.get<Cliente>(`${this.apiURL}/clientes/${id}`);
  }

  deleteById(id: any) : Observable<any>{
    return this.http.delete<any>(`${this.apiURL}/clientes/${id}`);
  }
}
