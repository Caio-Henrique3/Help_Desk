import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-cliente-delete',
  templateUrl: './cliente-delete.component.html',
  styleUrls: ['./cliente-delete.component.css']
})
export class ClienteDeleteComponent implements OnInit {

  cliente: Cliente = {
    id: '',
    nome: '',
    cpf: '',
    email: '',
    senha: '',
    perfis: [],
    dataCriacao: ''
  }

  @Input() isCheckedAdmin: boolean = false;
  @Input() isCheckedTecnico: boolean = false;
  @Input() isCheckedCliente: boolean = false;

  constructor(private clienteService: ClienteService,
    private toastr: ToastrService,
    private router: Router,
    private route: ActivatedRoute) { }

    ngOnInit(): void {
      this.cliente.id = this.route.snapshot.paramMap.get('id');
      this.findById();
     }
  
    findById(): void {
      this.clienteService.findById(this.cliente.id).subscribe(resposta => {
        //resposta.perfis = [];
        this.cliente = resposta;
        for (let e = this.cliente.perfis.length; e >= 0; e--) {
          if (this.cliente.perfis[e] == 'ADMIN') {
            this.isCheckedAdmin = true;
            this.addPerfil(0);
            this.cliente.perfis.splice(this.cliente.perfis.indexOf(this.cliente.perfis[e]), 1);
          } 
          if (this.cliente.perfis[e] == 'TECNICO') {
            this.isCheckedTecnico = true;
            this.addPerfil(1);
            this.cliente.perfis.splice(this.cliente.perfis.indexOf(this.cliente.perfis[e]), 1);
          }
          if (this.cliente.perfis[e] == 'CLIENTE') {
            this.isCheckedCliente = true;
            this.addPerfil(2);
            this.cliente.perfis.splice(this.cliente.perfis.indexOf(this.cliente.perfis[e]), 1);
          }
        }
      });
    }
  
  addPerfil(perfil: any): void {
    if (this.cliente.perfis.includes(perfil)) {
      this.cliente.perfis.splice(this.cliente.perfis.indexOf(perfil), 1);
    } else {
      this.cliente.perfis.push(perfil);
    }
  }
  
  delete(): void {
    this.clienteService.delete(this.cliente.id).subscribe(() => {
      this.toastr.success('Cliente deletado com sucesso!');
      this.router.navigate(['clientes']);
    }, ex => {
      if (ex.error.errors) {
        ex.error.errors.forEach(e => {
          this.toastr.error(e.message);
        });
      } else {
        this.toastr.error(ex.error.message);
      }
    });
  }

}