package test.java;

import main.java.PrototipoBrain2App;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrototipoBrain2AppTest {

    // Para garantizar que las pruebas pasen correctamente en cualquier entorno,
    // se requiere que las imágenes de prueba no sean utilizadas en rutas que no existan o que
    // dependan de la existencia de archivos en todos los sistemas.

    // A continuación se muestran pruebas que no dependen de archivos locales.

    // Prueba para verificar que la imagen no se carga si la ruta es inexistente
    @Test
    public void testCargarImagenInexistente() {
        String rutaInvalida = "imagen_inexistente.jpg"; // Ruta no válida
        PrototipoBrain2App app = new PrototipoBrain2App(rutaInvalida);

        // Se espera que no haya imagen cargada si la ruta no existe
        assertNull(app.getImagen(), "Debe devolver null si el archivo no existe.");
    }

    // Prueba para guardar la imagen sin haber cargado una imagen
    @Test
    public void testGuardarSinCargarImagen() {
        String nombreCarpeta = "testCarpeta";
        String nombreArchivo = "testImagen";

        // Creamos una instancia sin cargar imagen
        PrototipoBrain2App app = new PrototipoBrain2App(null);

        // Intentamos guardar sin haber cargado una imagen
        app.guardarImagenEnCarpeta(nombreCarpeta, nombreArchivo);

        // Verificamos que no se guarda ninguna imagen
        assertNull(app.getImagen(), "No debe guardarse ninguna imagen si no hay imagen cargada.");
    }
}
