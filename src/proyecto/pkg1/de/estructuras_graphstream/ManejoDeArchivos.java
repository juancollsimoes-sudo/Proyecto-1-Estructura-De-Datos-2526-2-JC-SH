/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 *
 * @author andre
 */
public class ManejoDeArchivos {

    // Máximo de proteínas/nodos al cargar (súper simple). Si necesitas más, sube el número.
    private static final int MAX_NODOS = 2000;

    /**
     * Guarda el grafo en un archivo de texto.
     *
     * Formato por línea:
     * origen;destino;peso
     *
     * Como tu grafo es NO dirigido y guarda A->B y B->A, aquí se escribe
     * una sola vez cuando (origen <= destino) para no duplicar.
     */
    public static void guardarGrafo(String rutaArchivo, Grafo grafo) {
        if (grafo == null) {
            return;
        }

        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(rutaArchivo))) {
            Nodo primero = obtenerPrimero(grafo);
            for (Nodo origen = primero; origen != null; origen = origen.pNext) {
                String nombreOrigen = nombreNodo(origen);
                Arco arco = origen.lista.ObtenerPrimero();
                while (arco != null) {
                    Nodo destino = arco.getDestino();
                    String nombreDestino = nombreNodo(destino);
                    double peso = arco.getPeso();

                    if (!nombreOrigen.isEmpty() && !nombreDestino.isEmpty()) {
                        if (nombreOrigen.compareTo(nombreDestino) <= 0) {
                            writer.write(nombreOrigen + "," + nombreDestino + "," + Double.toString(peso));
                            writer.newLine();
                        }
                    }

                    arco = arco.ObtenerpNext();
                }
            }
        } catch (java.io.IOException e) {
            // se ignora el error aquí; la interfaz puede verificar la existencia del archivo
        }
    }

    /**
     * Carga el grafo desde un archivo de texto con líneas:
     * origen;destino;peso
     *
     * Devuelve un Grafo (vacío si hay error).
     */
    public static Grafo cargarGrafo(String rutaArchivo) {
        java.io.File archivo = new java.io.File(rutaArchivo);

        if (!archivo.exists()) {
            return null;
        }

        String[] nombres = new String[MAX_NODOS];
        Nodo[] nodos = new Nodo[MAX_NODOS];
        int[] cantidad = new int[] { 0 };

        Grafo grafo = new Grafo();

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(",");
                if (partes.length < 3) {
                    continue;
                }

                String origenNombre = partes[0].trim();
                String destinoNombre = partes[1].trim();
                String pesoTexto = partes[2].trim();

                if (origenNombre.isEmpty() || destinoNombre.isEmpty()) {
                    continue;
                }

                double peso;
                try {
                    peso = Double.parseDouble(pesoTexto);
                } catch (NumberFormatException e) {
                    continue;
                }

                Nodo origen = obtenerONuevoNodo(grafo, origenNombre, nombres, nodos, cantidad);
                Nodo destino = obtenerONuevoNodo(grafo, destinoNombre, nombres, nodos, cantidad);

                if (origen != null && destino != null) {
                    grafo.AgregarArco(origen, destino, peso);
                }
            }
        } catch (java.io.IOException e) {
            return null;
        }

        return grafo;
    }

    /**
     * "Modificar" = sobrescribir el archivo con el grafo actual.
     */
    public static void modificarGrafo(String rutaArchivo, Grafo grafoActualizado) {
        guardarGrafo(rutaArchivo, grafoActualizado);
    }

    /**
     * Borra el archivo donde está guardado el grafo.
     */
    public static boolean borrarGrafo(String rutaArchivo) {
        java.io.File archivo = new java.io.File(rutaArchivo);
        if (!archivo.exists()) {
            return false;
        }
        boolean ok = archivo.delete();
        return ok;
    }

    // --- helpers sencillos ---

    public static Nodo obtenerPrimero(Grafo grafo) {
        try {
            java.lang.reflect.Field f = Grafo.class.getDeclaredField("pFirst");
            f.setAccessible(true);
            return (Nodo) f.get(grafo);
        } catch (Exception e) {
            return null;
        }
    }

    public static String nombreNodo(Nodo n) {
        if (n == null) return "";
        Object d = n.dato;
        if (d == null) return "";
        return String.valueOf(d).trim();
    }

    private static Nodo obtenerONuevoNodo(
            Grafo grafo,
            String nombre,
            String[] nombres,
            Nodo[] nodos,
            int[] cantidad
    ) {
        int idx = buscarNombre(nombre, nombres, cantidad[0]);
        if (idx != -1) {
            return nodos[idx];
        }

        if (cantidad[0] >= nombres.length) {
            return null;
        }

        Nodo nuevo = grafo.AgregarNodo(nombre);
        nombres[cantidad[0]] = nombre;
        nodos[cantidad[0]] = nuevo;
        cantidad[0]++;
        return nuevo;
    }

    private static int buscarNombre(String nombre, String[] nombres, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            if (nombre.equals(nombres[i])) {
                return i;
            }
        }
        return -1;
    }
}
