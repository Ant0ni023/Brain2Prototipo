package main.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;

public class PrototipoBrain2App extends JPanel {
    private BufferedImage imagen;
    public String formatoImagen; // Variable para guardar el formato de la imagen

    // Constructor para cargar la imagen desde el archivo, o mostrar un mensaje si la ruta es null
    public PrototipoBrain2App(String rutaImagen) {
        if (rutaImagen != null) {
            cargarImagen(rutaImagen);
        } else {
            System.out.println("No se ha proporcionado una ruta de imagen.");
        }
    }

    // Método encargado de cargar la imagen desde una ruta especificada
    public void cargarImagen(String rutaImagen) {
        try {
            // Obtenemos el archivo desde la ruta especificada
            File archivoImagen = new File(rutaImagen);
            if (!archivoImagen.exists()) { // Verificamos si el archivo existe
                System.out.println("El archivo de la imagen no existe: " + archivoImagen.getAbsolutePath());
            } else {
                // Cargamos la imagen en la variable 'imagen'
                imagen = ImageIO.read(archivoImagen);
                System.out.println("Imagen cargada correctamente.");
                // Extraemos el formato de la imagen desde su nombre (extensión del archivo)
                formatoImagen = rutaImagen.substring(rutaImagen.lastIndexOf(".") + 1);
            }
        } catch (IOException e) {
            // Este bloque catch captura cualquier error al intentar cargar la imagen
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    // Método encargado de guardar la imagen en una carpeta designada por el usuario
    public void guardarImagenEnCarpeta(String nombreCarpeta, String nombreArchivo) {
        try {
            // Crear la carpeta si no existe
            File carpeta = new File(nombreCarpeta);
            boolean creada = carpeta.mkdirs(); // Verificamos si la carpeta fue creada
            if (creada) {
                System.out.println("Carpeta creada exitosamente.");
            } else {
                System.out.println("No se pudo crear la carpeta o ya existía.");
            }
            // Verificamos si hay una imagen cargada antes de intentar guardarla
            if (imagen != null) {
                File archivoSalida = new File(carpeta, nombreArchivo + "." + formatoImagen);
                ImageIO.write(imagen, formatoImagen, archivoSalida);
                System.out.println("Imagen guardada correctamente en: " + archivoSalida.getAbsolutePath());
            } else {
                System.out.println("No hay imagen cargada para guardar.");
            }
        } catch (IOException e) {
            // Este bloque catch captura cualquier error que pueda ocurrir al intentar guardar la imagen
            System.out.println("Error al guardar la imagen: " + e.getMessage());
        }
    }

    // Método para eliminar la imagen guardada
    public void eliminarImagen(String nombreCarpeta, String nombreArchivo) {
        // Verificamos si la imagen cargada y la carpeta existen
        File archivo = new File(nombreCarpeta, nombreArchivo + "." + formatoImagen);
        if (archivo.exists()) {
            boolean eliminado = archivo.delete(); // Intentamos eliminar el archivo
            if (eliminado) {
                System.out.println("Imagen eliminada correctamente.");
            } else {
                System.out.println("No se pudo eliminar la imagen.");
            }
        } else {
            System.out.println("La imagen no existe.");
        }
    }

    // Mostrar la imagen cargada en una ventana
    public static void mostrarImagenEnVentana(PrototipoBrain2App panel) {
        JFrame ventana = new JFrame("Imagen cargada");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600, 600);
        ventana.add(panel);
        ventana.setVisible(true);
    }

    // Método para dibujar la imagen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            // Dibujamos la imagen en el panel si está cargada
            g.drawImage(imagen, 0, 0, this);
        }
    }

    // Método para obtener la imagen cargada
    public BufferedImage getImagen() {
        return this.imagen;
    }

    // Método para mostrar el menú al usuario
    public static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Cargar imagen");
        System.out.println("2. Guardar imagen");
        System.out.println("3. Mostrar imagen");
        System.out.println("4. Eliminar imagen");
        System.out.println("5. Salir");
    }

    // Método para obtener la opción del menú
    public static int obtenerOpcionMenu(Scanner scanner) {
        System.out.println("Seleccione una opción:");
        return Integer.parseInt(scanner.nextLine());
    }

    // Método principal para gestionar el flujo del programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrototipoBrain2App app = null; // Inicializamos la aplicación sin imagen
        String nombreCarpeta = "imagenes";
        String nombreArchivo = "imagenGuardada";
        int opcion;

        do {
            // Mostramos el menú al usuario
            mostrarMenu();
            // Obtenemos la opción seleccionada por el usuario
            opcion = obtenerOpcionMenu(scanner);

            // Evaluamos la opción seleccionada por el usuario
            switch (opcion) {
                case 1:
                    // Cargar imagen
                    System.out.println("Ingrese la ruta de la imagen a cargar:");
                    String rutaImagen = scanner.nextLine();
                    app = new PrototipoBrain2App(rutaImagen); // Creamos una nueva instancia con la imagen cargada
                    break;
                case 2:
                    // Guardar imagen
                    if (app != null) {
                        app.guardarImagenEnCarpeta(nombreCarpeta, nombreArchivo); // Guardamos la imagen
                    } else {
                        System.out.println("Primero debe cargar una imagen.");
                    }
                    break;
                case 3:
                    // Mostrar imagen
                    if (app != null && app.getImagen() != null) {
                        mostrarImagenEnVentana(app); // Mostramos la imagen si está cargada
                    } else {
                        System.out.println("Primero debe cargar una imagen.");
                    }
                    break;
                case 4:
                    // Eliminar imagen
                    if (app != null && app.getImagen() != null) {
                        app.eliminarImagen(nombreCarpeta, nombreArchivo); // Eliminamos la imagen guardada
                    } else {
                        System.out.println("Primero debe cargar una imagen.");
                    }
                    break;
                case 5:
                    // Salir
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 5); // Repetimos hasta que el usuario elija la opción de salir

        scanner.close(); // Cerramos el scanner al finalizar el programa
    }
}
