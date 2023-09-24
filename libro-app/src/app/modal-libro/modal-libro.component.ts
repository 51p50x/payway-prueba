import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-modal-libro',
  templateUrl: './modal-libro.component.html',
  styleUrls: ['./modal-libro.component.css']
})
export class ModalLibroComponent implements OnInit {

  libroForm: FormGroup = new FormGroup({});
  autores: any[] = [];
  categorias: any[] = [];

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<ModalLibroComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit(): void {
    this.inicializarFormulario();
    this.autores = this.data.autores;
    this.categorias = this.data.categorias;
  }

   inicializarFormulario(): void {
    this.libroForm = this.fb.group({
      titulo: [this.data.libro?.titulo || '', Validators.required],
      precio: [this.data.libro?.precio || '', Validators.required],
      autorId: [this.data.libro?.autor.id || '', Validators.required],
      categoriaIds: [this.data.libro?.categorias.map((cat: any) => cat.id) || []]
    });
  }

  onSubmit(): void {
    if (this.libroForm.valid) {
      this.dialogRef.close(this.libroForm.value);
    }
  }

  onClose(): void {
    this.dialogRef.close();
  }cerrarDialogo(): void {
    this.dialogRef.close();
  }
}
