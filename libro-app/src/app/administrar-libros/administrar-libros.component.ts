import { Component, OnInit, ViewChild, ChangeDetectorRef, AfterViewInit } from '@angular/core';
import { LibroService } from '../libro.service';
import { MatDialog } from '@angular/material/dialog';
import { ModalLibroComponent } from '../modal-libro/modal-libro.component';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';


@Component({
  selector: 'app-administrar-libros',
  templateUrl: './administrar-libros.component.html',
  styleUrls: ['./administrar-libros.component.css']
})
export class AdministrarLibrosComponent implements OnInit, AfterViewInit {

  libros = new MatTableDataSource<any>();
  autores: any[] = [];
  categorias: any[] = [];
  mostrandoFormulario = false;
  libroActual: any = {};
  paginas: number = 0;

  constructor(private libroService: LibroService, public dialog: MatDialog, private snackBar: MatSnackBar, private cdr: ChangeDetectorRef) { }

  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  ngOnInit(): void {
    this.cargarLibros(0, 5);
    this.cargarAutores();
    this.cargarCategorias();
  }

  ngAfterViewInit(): void {
    this.paginator.page.subscribe(() => {
      this.cargarLibros(this.paginator.pageIndex, this.paginator.pageSize);
  });
  }

  cargarLibros(pageIndex: number, pageSize: number): void {
    this.libroService.obtenerLibros(pageIndex, pageSize).subscribe(data => {
        this.libros.data = data.libros;
        this.paginas = data.totalRegistros;
    });
  }

  

  
  abrirDialogo(isEdit: boolean, libro?: any): void {
    const dialogRef = this.dialog.open(ModalLibroComponent, {
      width: '400px',
      data: {
        isEdit,
        libro,
        autores: this.autores,
        categorias: this.categorias
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (isEdit) {
          this.actualizarLibro(result, libro.id);
        } else {
          this.agregarLibro(result);
        }
      }
    });
  }

  showMessage(message: string, action: string = 'Cerrar') {
    this.snackBar.open(message, action, {
      duration: 3000
    });
  }

  obtenerNombreAutor(autor: any): string {
    return autor ? autor.nombre : 'Desconocido';
  }

  obtenerNombresCategorias(categorias: any[]): string {
    return categorias.map(c => c.nombre).join(', ');
  }

  cargarAutores(): void {
    this.libroService.obtenerAutores().subscribe(data => {
      this.autores = data;
    });
  }

  cargarCategorias(): void {
    this.libroService.obtenerCategorias().subscribe(data => {
      this.categorias = data;
    });
  }

  mostrarFormulario(libro?: any): void {
    if (libro) {
      this.abrirDialogo(true, libro);
    } else {
      this.abrirDialogo(false);
    }
  }

  editarLibro(libro: any): void {
    this.abrirDialogo(true, libro);
  }

  desactivarLibro(libro: any): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.libroService.desactivarLibro(libro.id).subscribe({
          next: () => {
            this.showMessage('Libro eliminado con éxito!');
            this.cargarLibros(0, 5);
          },
          error: (err) => {
            console.error("Hubo un error al elimnar el libro", err);
            this.showMessage('Error al agregar el libro.');
          }
        })
      }
    });
  }

  agregarLibro(nuevoLibro: any): void {
    this.libroService.agregarLibro(nuevoLibro).subscribe({
      next: () => {
        this.showMessage('Libro agregado con éxito!');
        this.cargarLibros(0, 5);
      },
      error: (err) => {
        console.error("Hubo un error al agregar el libro", err);
        this.showMessage('Error al agregar el libro.');
      }
    });
  }

  actualizarLibro(libroActualizado: any, id: number): void {
    this.libroService.actualizarLibro(libroActualizado, id).subscribe({
      next: () => {

        this.showMessage('Libro actualizado con éxito!');
        this.cargarLibros(0, 5);
      },
      error: (err) => {
        this.showMessage('Hubo un error al actualizar el libro.');
        console.error(err);
      }
    });
  }

  cancelarFormulario(): void {
    this.mostrandoFormulario = false;
  }

}
