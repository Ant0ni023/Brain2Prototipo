# PrototipoBrain2

El prototipo hasta ahora permite cargar, guardar, mostrar y eliminar una imagen en el sistema. Actualmente, el sistema gestiona una imagen a la vez.

Funcionalidades principales:
- Cargar una imagen desde una ruta especificada.
- Guardar la imagen en una carpeta designada.
- Mostrar la imagen cargada en una ventana.
- Eliminar una imagen guardada en el sistema.




El archivo principal del código se encuentra en la siguiente ubicación:
src/main/java/main/java/PrototipoBrain2App.java



En esta primera versión del código hemos creada tres pruebas unitarias utilizando JUnit 5, sin la integración de Maven ni Gradle. Estas pruebas están diseñadas para no depender de rutas de imágenes específicas, lo que asegura su ejecución en diferentes entornos.
1. Asegura que la aplicación maneje adecuadamente rutas no válidas.
2.  Verifica que no se guarde ninguna imagen si no se ha cargado una previamente.
3. Asegura que el formato de la imagen se detecta correctamente a partir del nombre del archivo.



Las pruebas unitarias están ubicadas en:
src/test/java/test/java/PrototipoBrain2AppTest.java
