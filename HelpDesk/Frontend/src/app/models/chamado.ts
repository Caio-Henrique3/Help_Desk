export interface Chamado {
  id?: any;
  prioridade: string;
  status: string;
  titulo: string;
  observacoes: string;
  tecnico: any;
  cliente: any;
  nomeTecnico: string;
  nomeCliente: string;
  dataAbertura?: string;
  dataFechamento?: string;
}