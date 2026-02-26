/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 * lista sencilla de adyacencia para un Nodo.
 * 
 * guarda los arcos (conexiones) como una lista enlazada
 * usando la clase Arco.
 */
public class ListaAdyacencia {

    // primer arco de la lista (cabeza)
    private Arco primero;

    /**
     * crea una lista de adyacencia vacia.
     */
    public ListaAdyacencia() {
        this.primero = null;
    }

    /**
     * Agrega un nuevo arco al inicio de la lista.
     * 
     * @param destino nodo destino del arco
     * @param peso    peso o costo de la conexion
     */
    public void Agregar(Nodo destino, double peso) {
        Arco nuevo = new Arco(destino, peso);
        nuevo.AgregarpNext(primero);
        primero = nuevo;
    }

    /**
     * @return el primer arco de la lista (puede ser null si esta vacia)
     */
    public Arco ObtenerPrimero() {
        return primero;
    }

    /**
     * @return true si la lista no tiene arcos
     */
    public boolean EstaVacia() {
        return primero == null;
    }
}
