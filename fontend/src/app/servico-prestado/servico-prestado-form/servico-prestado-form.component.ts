import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/clientes/Cliente';
import { ClientesService } from '../../services/clientes.service';
import { ServicoPrestado } from '../servicoprestado';
import { Router, RouterModule } from '@angular/router';
import { ServicoPrestadoService } from '../../services/servico-prestado.service';

@Component({
  selector: 'app-servico-prestado-form',
  templateUrl: './servico-prestado-form.component.html',
  styleUrls: ['./servico-prestado-form.component.css']
})
export class ServicoPrestadoFormComponent implements OnInit {

  clientes: Cliente[] = [];
  servico: ServicoPrestado;
  success: boolean = false;
  errors: string[];


  constructor(
    private clienteService: ClientesService,
    private servicoPrestadoService: ServicoPrestadoService,
    private router: Router
  ) {
    this.servico = new ServicoPrestado();
  }

  ngOnInit(): void {
    this.clienteService.listaCliente().subscribe(
      response => this.clientes = response
    );

  }

  onSubmit() {
    this.servicoPrestadoService.insertServicoPrestado(this.servico).subscribe(
      response => {
        this.success = true;
        this.errors = null;
        this.servico = new ServicoPrestado();
      }, (errorResponse) => {
        this.success = false;
        this.errors = errorResponse.error.erros;
      }
    )
  }

  voltarParaPesquisa() {
    this.router.navigate(['/servicos-prestado/listagem']);
  }
}
