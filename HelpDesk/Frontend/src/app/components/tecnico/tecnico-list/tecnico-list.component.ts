import { Tecnico } from './../../../models/tecnico';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-tecnico-list',
  templateUrl: './tecnico-list.component.html',
  styleUrls: ['./tecnico-list.component.css']
})
export class TecnicoListComponent implements OnInit {
  ELEMENT_DATA: Tecnico[]= [
    {
    id: 1,
    nome: 'Caio Henrique',
    cpf: '654-644-604.10',
    email: 'caio@mail.com',
    senha: 'senha',
    perfis: ['0'],
    dataCriacao: '15/08/2022'
    }
  ]

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol', 'acoes'];
  dataSource = new MatTableDataSource<Tecnico>(this.ELEMENT_DATA);

  constructor() { }

  ngOnInit(): void {
  }


  @ViewChild(MatPaginator) paginator: MatPaginator;
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}