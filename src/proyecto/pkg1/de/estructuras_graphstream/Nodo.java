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
    protected Object dato;
    protected ListaAdyacencia lista;
    protected Nodo pNext;
    private double distanciaMinima = Double.MAX_VALUE; // Almacena la distancia acomulada desde el Nodo origen
    private boolean visitado = false; // detecta componentes conexos mediante BFS o DFS
    private Nodo predecesor = null; // referencia al nodo anterior en la ruta de menos resistencia, permite reconstruir la trayectoria de la ruta metabolica
    private int grado = 0; // sirve para identificar Hubs.
    public Nodo(Object elem){
        /**
         * Constructor de la clase Nodo.
         * @param elem El objeto que representa la proteina a almacenar
         */
        this.dato = elem;
        this.lista = new ListaAdyacencia();    
        this.pNext = null;
    }
    /**
     * Estos metodos son necesarios para el Algoritmo de Dijkstra.
     * sirven para consultar y actualizar el peso del Nodo.
     */
    public double ObtenerDistanciaMinima(){
        return distanciaMinima;
    }
    public void AgregarDistanciaMinima(double d){
        this.distanciaMinima = d;
    }
    /**
     * Estos metodos sirven para marcar por donde ya paso el Nodo.
     * Es fundamental para Dijkstra y para DFS/DFS.
     */
    public boolean esVisitado(){
        return visitado;
    }
    public void AgregarVisitado(boolean v){
        this.visitado = v;
    }
    /**
     * Estos metodos sirven para guardar el camino exacto para mostrar el nodo anterior.
     */
    public Nodo ObtenerPredecesor(){
        return predecesor;
    }
    public void AgregarPredecesor(Nodo p){
        this.predecesor = p;
    }
    /**
     * Estos metodos permiten identificar "Hubs" o dianas terapeuticas primarias
     * segun la cantidad de interacciones de la proteina.
     */
    public int ObtenerGrado(){
        return grado;
    }
    public void IncrementoGrado(){
        this.grado++;
    }
    
}
