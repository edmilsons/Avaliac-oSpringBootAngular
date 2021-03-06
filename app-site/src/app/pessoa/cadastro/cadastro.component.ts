import { Component, OnInit } from '@angular/core';

import {Router} from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import {PessoaService} from '../../services/pessoa.service';

import {Pessoa} from '../../services/pessoa';
import {TipoRisco} from '../../services/tipoRisco';


import {Response} from '../../services/response';

import { Observable } from 'rxjs/Observable';

@Component({
    selector: 'app-cadastro-pessoa',
    templateUrl: './cadastro.component.html',
    styleUrls:["./cadastro.component.css"]
  })
  export class CadastroComponent implements OnInit {
  
    private titulo:string;
    private pessoa:Pessoa = new Pessoa();
    private listTipoRisco: TipoRisco[] = new Array();


    constructor(private pessoaService: PessoaService,
                private router: Router,
                private activatedRoute: ActivatedRoute){}
  
    /*CARREGADO NA INICIALIZAÇÃO DO COMPONENTE */
    ngOnInit() {
      
      this.activatedRoute.params.subscribe(parametro=>{
        
        
        if(parametro["id"] == undefined){
          
          this.titulo = "Avaliação de Crédito Pessoal";
        }
        else{

          this.titulo = "Editar Avaliação de Crédito Pessoal";
          this.pessoaService.getPessoa(Number(parametro["id"])).subscribe(res => this.pessoa = res);
        }
        this.pessoaService.getTipoRiscos().subscribe(res => this.listTipoRisco = res);


      });      
    }

    /*FUNÇÃO PARA SALVAR UM NOVO REGISTRO OU ALTERAÇÃO EM UM REGISTRO EXISTENTE */
    salvar():void {
    
      /*SE NÃO TIVER CÓDIGO VAMOS INSERIR UM NOVO REGISTRO */
      if(this.pessoa.id == undefined){
        
        /*CHAMA O SERVIÇO PARA ADICIONAR UMA NOVA PESSOA */
        this.pessoaService.addPessoa(this.pessoa).subscribe(response => {
            
           //PEGA O RESPONSE DO RETORNO DO SERVIÇO
           let res:Response = <Response>response;
                
           /*SE RETORNOU 1 DEVEMOS MOSTRAR A MENSAGEM DE SUCESSO
           E LIMPAR O FORMULÁRIO PARA INSERIR UM NOVO REGISTRO*/
           if(res.codigo == 1){
            alert(res.mensagem);
            this.pessoa = new Pessoa();
           }
           else{
             /*
             ESSA MENSAGEM VAI SER MOSTRADA CASO OCORRA ALGUMA EXCEPTION
             NO SERVIDOR (CODIGO = 0)*/
             alert(res.mensagem);
           }
         },
         (erro) => {   
           /**AQUI VAMOS MOSTRAR OS ERROS NÃO TRATADOS
             EXEMPLO: SE APLICAÇÃO NÃO CONSEGUIR FAZER UMA REQUEST NA API                        */                 
            try{
              let respostaServidor = JSON.parse(erro._body);
              if(respostaServidor.codigo == 0){
                alert("Existe campos Vazios");
              }else{
                alert(erro);
              }
            }catch(e){
              alert(erro);
            }
         });

      }
      else{

        /*AQUI VAMOS ATUALIZAR AS INFORMAÇÕES DE UM REGISTRO EXISTENTE */
        this.pessoaService.atualizarPessoa(this.pessoa).subscribe(response => {
          
        //PEGA O RESPONSE DO RETORNO DO SERVIÇO
        let res:Response = <Response>response;
              
         /*SE RETORNOU 1 DEVEMOS MOSTRAR A MENSAGEM DE SUCESSO
           E REDIRECIONAR O USUÁRIO PARA A PÁGINA DE CONSULTA*/
        if(res.codigo == 1){
          alert(res.mensagem);
          this.router.navigate(['/consulta-pessoa']);
        }
         else{
          /*ESSA MENSAGEM VAI SER MOSTRADA CASO OCORRA ALGUMA EXCEPTION
          NO SERVIDOR (CODIGO = 0)*/
           alert(res.mensagem);
         }
       },
       (erro) => {                    
         /**AQUI VAMOS MOSTRAR OS ERROS NÃO TRATADOS
          EXEMPLO: SE APLICAÇÃO NÃO CONSEGUIR FAZER UMA REQUEST NA API                        */                 
          alert(erro);
       });
      }

    }
  
  }