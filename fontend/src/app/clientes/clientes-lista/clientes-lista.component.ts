import { Component, OnInit } from '@angular/core';
import { Cliente } from '../Cliente';
import { Router } from '@angular/router';
import { ClientesService } from '../../services/clientes.service';


@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {

  clientes: Cliente[] = [];
  clienteSelecionado : Cliente;
  menssageDeResposta: string;
  cor: string = 'denger';

  constructor(private service: ClientesService,
    private router : Router) {

  }

  ngOnInit(): void {
    this.service.listaCliente().subscribe(resposta =>{
      this.clientes = resposta;
      for(let c of this.clientes){
        c.nome;
      }
    })
  }

  novoCadastro(){
    this.router.navigate(['/clientes/form'])
  }

  preparaDelecao(cliente: Cliente){
    this.clienteSelecionado = cliente;
  }

  deletar(){
    this.service.deleteById(this.clienteSelecionado.id).subscribe(
      (response) =>{
        this.menssageDeResposta = `Cliente  ${this.clienteSelecionado.nome} excluido com sucesso.`
        this.cor = 'success';
        console.log(this.menssageDeResposta);
        console.log(this.cor)
        this.ngOnInit();
      },(errorResponse) =>{
        this.menssageDeResposta = errorResponse.error.message;
        this.cor = 'danger';
        console.log(this.menssageDeResposta);
        console.log(this.cor)
      }
    )
  }
}
