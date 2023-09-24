# Aplicación de Libros

Este proyecto consta de dos partes principales: un servicio backend construido con Java y una aplicación frontend construida con Angular.

## Estructura del Proyecto

El proyecto tiene la siguiente estructura de carpetas:

- `libro-service`: Contiene el proyecto Java.
- `libro-app`: Contiene el proyecto Angular.

## Despliegue del Proyecto Java

1. **Navegación a la Carpeta:**
   - Abre una terminal o línea de comandos.
   - Navega a la carpeta `libro-service`.
     ```bash
     cd libro-service
     ```

2. **Compilación:**
   - Ejecuta el siguiente comando para compilar el proyecto.
     ```bash
     mvn clean package
     ```

3. **Despliegue:**
   - Después de la compilación, se generará un archivo `.war` en el directorio `target`.
   - Despliega este archivo en un servidor Tomcat 9.

## Despliegue del Proyecto Angular

1. **Navegación a la Carpeta:**
   - Navega a la carpeta `libro-app`.
     ```bash
     cd ../libro-app
     ```

2. **Instalación de Dependencias (Opcional):**
   - Si es la primera vez que despliegas la aplicación o si has agregado nuevas dependencias, ejecuta el siguiente comando para instalar las dependencias del proyecto.
     ```bash
     npm install
     ```

3. **Inicio de la Aplicación:**
   - Inicia la aplicación Angular con el siguiente comando.
     ```bash
     ng serve
     ```

   La aplicación Angular ahora debería estar corriendo en `http://localhost:4200`.
