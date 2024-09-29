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
    private String formatoImagen;

    // Constructor para cargar la imagen desde el archivo
    public PrototipoBrain2App(String rutaImagen) {
        if (rutaImagen != null) {
            cargarImagen(rutaImagen);
        } else {
            System.out.println("No se ha proporcionado una ruta de imagen.");
        }
    }

    // Metodo para cargar la imagen
    public void cargarImagen(String rutaImagen) {
        try {
            File archivoImagen = new File(rutaImagen);
            if (!archivoImagen.exists()) {
                System.out.println("El archivo de la imagen no existe: " + archivoImagen.getAbsolutePath());
            } else {
                imagen = ImageIO.read(archivoImagen);
                System.out.println("Imagen cargada correctamente.");
                formatoImagen = rutaImagen.substring(rutaImagen.lastIndexOf(".") + 1);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    // Mostrar la imagen en una ventana
    public static void mostrarImagenEnVentana(PrototipoBrain2App panel) {
        JFrame ventana = new JFrame("Imagen cargada");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600, 600);
        ventana.add(panel);
        ventana.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, this);
        }
    }

    // Métodos para login (mantenidos del sistema anterior)
    public static void setUp() {
        String[] users = {"Alex", "Antonio", "Antonia"};
        String[] passwords = {"Rojas_ufromail", "Acuna_ufromail", "Cordova_ufromail"};
        Scanner scanner = new Scanner(System.in);

        if (run(users, passwords, scanner)) {
            System.out.println("¡Bienvenido! Ahora puedes cargar imágenes.");
            mostrarMenuImagenes(scanner); // Mostrar opciones de imágenes después del login
        } else {
            System.out.println("Autenticación fallida.");
        }

        scanner.close();
    }

    public static boolean run(String[] users, String[] passwords, Scanner scanner) {
        System.out.println("Ingrese su nombre de usuario:");
        String user = scanner.nextLine();

        if (!validateUser(users, user)) {
            System.out.println("Usuario " + user + " no encontrado");
            return false;
        }

        int index = getIndex(users, user);
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        if (!validateCredentials(passwords, index, password)) {
            System.out.println("Contraseña incorrecta para el usuario " + user);
            return false;
        }

        System.out.println("Contraseña correcta. Bienvenido " + user);
        return true;
    }

    public static boolean validateUser(String[] usersArr, String user) {
        for (String u : usersArr) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public static int getIndex(String[] arr, String value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean validateCredentials(String[] passwordsArr, int index, String password) {
        return passwordsArr[index].equals(password);
    }

    // Mostrar menú para manejo de imágenes
    public static void mostrarMenuImagenes(Scanner scanner) {
        PrototipoBrain2App app = null;

        while (true) {
            System.out.println("Menú de imágenes:");
            System.out.println("1. Cargar imagen");
            System.out.println("2. Mostrar imagen");
            System.out.println("3. Salir");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la ruta de la imagen a cargar:");
                    String rutaImagen = scanner.nextLine();
                    app = new PrototipoBrain2App(rutaImagen);
                    break;
                case 2:
                    if (app != null && app.imagen != null) {
                        mostrarImagenEnVentana(app);
                    } else {
                        System.out.println("Primero debe cargar una imagen.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void main(String[] args) {
        setUp(); // Iniciar sistema de login y luego manejo de imágenes
    }
}
