import { ToastrService } from 'ngx-toastr';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Tecnico } from 'src/app/models/tecnico';
import { TecnicoService } from 'src/app/services/tecnico.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tecnico-delete',
  templateUrl: './tecnico-delete.component.html',
  styleUrls: ['./tecnico-delete.component.css']
})
export class TecnicoDeleteComponent implements OnInit {

  tecnico: Tecnico = {
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

  constructor(private tecnicoService: TecnicoService,
    private toastr: ToastrService,
    private router: Router,
    private route: ActivatedRoute) { }

    ngOnInit(): void {
      this.tecnico.id = this.route.snapshot.paramMap.get('id');
      this.findById();
     }
  
    findById(): void {
      this.tecnicoService.findById(this.tecnico.id).subscribe(resposta => {
        //resposta.perfis = [];
        this.tecnico = resposta;
        for (let e = this.tecnico.perfis.length; e >= 0; e--) {
          if (this.tecnico.perfis[e] == 'ADMIN') {
            this.isCheckedAdmin = true;
            this.addPerfil(0);
            this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(this.tecnico.perfis[e]), 1);
          } 
          if (this.tecnico.perfis[e] == 'TECNICO') {
            this.isCheckedTecnico = true;
            this.addPerfil(1);
            this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(this.tecnico.perfis[e]), 1);
          }
          if (this.tecnico.perfis[e] == 'CLIENTE') {
            this.isCheckedCliente = true;
            this.addPerfil(2);
            this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(this.tecnico.perfis[e]), 1);
          }
        }
      });
    }
  
  addPerfil(perfil: any): void {
    if (this.tecnico.perfis.includes(perfil)) {
      this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(perfil), 1);
    } else {
      this.tecnico.perfis.push(perfil);
    }
  }
  
  delete(): void {
    this.tecnicoService.delete(this.tecnico.id).subscribe(() => {
      this.toastr.success('T??cnico deletado com sucesso!');
      this.router.navigate(['tecnicos']);
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
