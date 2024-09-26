PrototipoBrain2
Descripción del proyecto
Este prototipo permite cargar, guardar, mostrar y eliminar una imagen en el sistema. Actualmente, el sistema gestiona una imagen a la vez.

Funcionalidades principales:
Cargar una imagen desde una ruta especificada.
Guardar la imagen en una carpeta designada.
Mostrar la imagen cargada en una ventana.
Eliminar la imagen guardada del sistema.


El archivo principal del código se encuentra en la siguiente ubicación:
src/main/java/main/java/PrototipoBrain2App.java

Las pruebas unitarias fueron creadas utilizando JUnit 5, sin la integración de Maven ni Gradle. Estas pruebas verifican el correcto funcionamiento del sistema, y están diseñadas para no depender de rutas de imágenes específicas, lo que asegura su ejecución en diferentes entornos.

Las pruebas unitarias están ubicadas en:
src/test/java/test/java/PrototipoBrain2AppTest.java
