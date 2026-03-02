/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase sencilla para leer, escribir y borrar archivos de texto.
 * Todos los métodos son estáticos para que se usen de forma directa:
 *
 */
public class ManejoDeArchivos {

    /**
     * Guarda todo el texto en la ruta indicada.
     * Si el archivo ya existe se sobreescribe.
     */
    public static void guardarTexto(String rutaArchivo, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write(contenido);
        } catch (IOException e) {
            System.err.println("No se pudo guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Carga todo el texto del archivo y lo devuelve como un String.
     * Si el archivo no existe o hay un error, devuelve una cadena vacía.
     */
    public static String cargarTexto(String rutaArchivo) {
        StringBuilder sb = new StringBuilder();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.err.println("El archivo no existe: " + rutaArchivo);
            return "";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("No se pudo leer el archivo: " + e.getMessage());
            return "";
        }

        return sb.toString();
    }

    /**
     * Borra el archivo indicado por la ruta.
     * Devuelve true si se borró correctamente, false en caso contrario.
     */
    public static boolean borrarArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.err.println("El archivo no existe: " + rutaArchivo);
            return false;
        }

        boolean borrado = archivo.delete();
        if (!borrado) {
            System.err.println("No se pudo borrar el archivo: " + rutaArchivo);
        }
        return borrado;
    }
}
