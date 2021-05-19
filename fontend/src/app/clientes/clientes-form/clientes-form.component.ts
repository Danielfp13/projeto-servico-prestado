import { Component, OnInit } from '@angular/core';
import { Cliente } from '../Cliente';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ClientesService } from '../../services/clientes.service';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css'],
})
export class ClientesFormComponent implements OnInit {
  cliente: Cliente;
  clienteId: any;

  success: boolean = false;
  errors: string[];
  erro: string;

  constructor(
    private service: ClientesService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    let params : Observable<Params> = this.activatedRoute.params;

    params.subscribe(urlParams =>{
      this.clienteId = urlParams['id'];
      if(this.clienteId){
        this.service.getClienteById(this.clienteId).subscribe(
          (response) => {
            this.cliente = response;
          },
          (errorResponse) => {
            this.errors = [`${errorResponse.error.message}`];
          }
        );
      }
    });
  }

  onSubmit() {
    if (this.clienteId) {
      this.service.alterarCliente(this.cliente,this.clienteId).subscribe(
        (response)=>{
          this.success = true;
          this.errors = null;
      },(errorResponse)=>{
        this.success = false;
        this.errors = [`${errorResponse.error.erros}`];
      })

    } else {
      this.service.salvar(this.cliente).subscribe(
        (response) => {
          this.success = true;
          this.errors = [];
          this.cliente = response;
        },
        (errorResponse) => {
          this.errors = null;
          this.errors = errorResponse.error.erros;
          console.log(this.errors);
          this.success = false;
        }
      );
    }
  }

  voltarParaListagem() {
    this.router.navigate(['/clientes/lista']);
  }
}
