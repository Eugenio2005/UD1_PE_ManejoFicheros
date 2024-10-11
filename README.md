# UD1_PE_ManejoFicheros

## Estructura del Proyecto

El proyecto está diseñado utilizando el patrón de arquitectura **MVC (Modelo-Vista-Controlador)** junto con el patrón **DAO (Data Access Object)**. Este enfoque modular y organizado permite separar las distintas preocupaciones del software, facilitando su mantenimiento, escalabilidad y pruebas.

### Arquitectura MVC

La arquitectura MVC divide la aplicación en tres componentes principales:

1. **Modelo**: Representa la lógica de datos del sistema. En este caso, gestiona la información de los jugadores, incluyendo atributos como el identificador, nombre de usuario, experiencia, nivel de vida y monedas. Esta capa se encarga de la manipulación de datos y la lógica de negocio.

2. **Vista**: Es la interfaz de usuario que presenta la información al usuario y le permite interactuar con el sistema. Se encarga de mostrar los datos de los jugadores y de recibir las entradas del usuario.

3. **Controlador**: Actúa como intermediario entre el modelo y la vista. Procesa las entradas del usuario, realiza las operaciones correspondientes en el modelo y actualiza la vista con los resultados. En este sistema, el controlador gestiona la carga, adición, eliminación, modificación y listado de jugadores.

### Patrón DAO

El patrón DAO se utiliza para separar la lógica de acceso a datos de la lógica de negocio. En este proyecto, se implementa la interfaz **IDAO**, que define las operaciones básicas para gestionar los jugadores. La implementación concreta de esta interfaz, llamada **JugadorDAO**, se encarga de realizar las operaciones de lectura y escritura en los diferentes tipos de archivos.

### Tipos de Archivos

El sistema permite manejar los siguientes tipos de archivos:

1. **Archivos de Texto**: Utilizados para el almacenamiento secuencial de datos en un formato legible por humanos. Este tipo de archivo es útil para la visualización y edición manual de los datos.

2. **Archivos Binarios**: Estos archivos almacenan datos en formato binario, lo que resulta en un uso más eficiente del espacio en disco. Se utilizan para almacenar datos de forma compacta y rápida, aunque no son legibles directamente.

3. **Archivos Binarios de Objetos**: Permiten la persistencia de objetos de Java en un archivo. Utilizan la serialización para convertir objetos en una secuencia de bytes, facilitando el almacenamiento y recuperación de estructuras de datos complejas.

4. **Archivos Binarios con Acceso Aleatorio**: Este tipo de archivo permite acceder a cualquier parte del archivo directamente, sin necesidad de leer todo el contenido secuencialmente. Esto es especialmente útil para operaciones que requieren modificaciones rápidas en partes específicas del archivo.

### Ejecución del Programa

Para ejecutar el programa, sigue estos pasos:

1. Navega a la carpeta `run` en tu entorno de desarrollo o terminal.
2. Ejecuta el archivo `Start.java` utilizando el comando:
   ```bash
   java Start
