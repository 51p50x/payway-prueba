import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LibroService {

  private apiUrl = 'http://localhost:8081/libro-servicio-1.0/api';

  constructor(private http: HttpClient) { }

  obtenerLibros(page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/libros?page=${page}&size=${size}`);
}

  obtenerAutores(): Observable<any> {
    return this.http.get(`${this.apiUrl}/autores`);
  }

  obtenerCategorias(): Observable<any> {
    return this.http.get(`${this.apiUrl}/categorias`);
  }

  agregarLibro(nuevoLibro: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/libros`, nuevoLibro);
  }

  actualizarLibro(libroActualizado: any, id: number): Observable<any> {
    const url = `${this.apiUrl}/libros/${id}`;
    return this.http.put(url, libroActualizado);
  }

  desactivarLibro(id: number): Observable<any> {
    const url = `${this.apiUrl}/desactivarLibro/${id}`;
    return this.http.put(url,'');
  }
}
