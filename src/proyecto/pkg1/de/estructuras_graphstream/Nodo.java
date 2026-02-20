/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 *Representa una proteina dentro del grafo de interacciones.
 * @author Juan Coll
 */
public class Nodo {
    /** 
     * Crea un nuevo nodo con el nombre de la proteina.
     * @param dato el objeto que contiene la informacion
    */
    Object dato;
    ListaAdyacencia lista;
    Nodo pNext;
    private double distanciaMinima = Double.MAX_VALUE; // Almacena la distancia acomulada desde el Nodo origen
    private boolean visitado = false; // detecta componentes conexos mediante BFS o DFS
    private Nodo predecesor = null; // referencia al nodo anterior en la ruta de menos resistencia, permite reconstruir la trayectoria de la ruta metabolica
    
    public Nodo(Object elem){
        /**
         * Constructor de la clase Nodo.
         * @param elem El objeto que representa la proteina a almacenar
         */
        this.dato = elem;
        this.lista = new ListaAdyacencia();    
        this.pNext = null;
    }
}
