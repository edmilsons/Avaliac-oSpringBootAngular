import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { HomeComponent } from './home/home.component';
import { ConsultaComponent } from './pessoa/consulta/consulta.component';
import { CadastroComponent} from './pessoa/cadastro/cadastro.component';

import {routing} from './../app.routes';


import {ConfigService} from './services/config.service';
import {PessoaService} from './services/pessoa.service';

import { CurrencyMaskModule } from "ng2-currency-mask";



@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HomeComponent,
    ConsultaComponent,
    CadastroComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    routing,
    CurrencyMaskModule
  ],
  providers: [ConfigService,PessoaService],
  bootstrap: [AppComponent,CadastroComponent]
})
export class AppModule { }
