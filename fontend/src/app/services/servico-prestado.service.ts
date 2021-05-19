import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ServicoPrestado } from '../servico-prestado/servicoprestado';
import { ServicoPrestadoBusca } from '../servico-prestado/servico-prestado-lista/servico-prestado';


@Injectable({
  providedIn: 'root'
})
export class ServicoPrestadoService {

  constructor(private http: HttpClient) { }

    apiURL: string = environment.apiURLBase;

    insertServicoPrestado(servicoPrestado: ServicoPrestado) :Observable<ServicoPrestado>{
    return this.http.post<ServicoPrestado>(`${this.apiURL}/servicos-prestado`,servicoPrestado);
    }

    buscar(nome: string, mes: number): Observable<ServicoPrestadoBusca[]>{

      const httpParams = new HttpParams().set("nome", nome).set("mes", mes ? mes.toString(): ''); 
      const url = this.apiURL + '/servicos-prestado/search?+httpParams' + httpParams.toString();
      return this.http.get<ServicoPrestadoBusca[]>(url)
    }

}

