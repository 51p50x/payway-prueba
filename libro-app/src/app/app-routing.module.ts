import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BienvenidaComponent } from './bienvenida/bienvenida.component';
import { AdministrarLibrosComponent } from './administrar-libros/administrar-libros.component';

const routes: Routes = [
  { path: '', redirectTo: '/bienvenida', pathMatch: 'full'},
  { path: 'bienvenida', component: BienvenidaComponent},
  { path: 'administrar-libros', component: AdministrarLibrosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
