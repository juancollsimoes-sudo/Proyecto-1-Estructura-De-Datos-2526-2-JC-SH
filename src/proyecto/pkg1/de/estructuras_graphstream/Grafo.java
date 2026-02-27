/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 * 
 * @author Juan Coll
 */
public class Grafo {
    private Nodo pFirst;
    private int iN;
    private Nodo HubPrincipal;
    
    /**
     * Constructor de la clase grafo
     * Inicializa un grafo Vacio
     */
    public Grafo() {
        this.pFirst = null;
        this.iN = 0;
        this.HubPrincipal = null;
    }
    /**
     * Agrega un nuevo nodo/proteina al grafo.
     * @param dato es el objeto que representa a la proteina
     * @return el nodo pNew
     */
    public Nodo AgregarNodo(Object dato){
        Nodo pNew = new Nodo(dato);
        pNew.pNext = pFirst;
        pFirst = pNew;
        iN++;
        return pNew;
    }
    
    /**
     * Agrega un Arco entre dos Proteinas/Nodos
     * @param pOrigen es el Nodo de origen de la conexion.
     * @param pDestino es el Nodo destino de la conexion.
     * @param peso es la resistencia de la conexion.
     */
    public void AgregarArco(Nodo pOrigen, Nodo pDestino, double peso){
        if (pOrigen != null && pDestino != null){
            pOrigen.lista.Agregar(pDestino, peso);
            pOrigen.IncrementoGrado();
            // actualiza el Hub si y solo si este nodo tiene mas conexiones.
            if (HubPrincipal == null || pOrigen.ObtenerGrado() > HubPrincipal.ObtenerGrado()){
                HubPrincipal = pOrigen;
            }
        }
    }
    
    /**
     * Reinicia el estado de visitado de todos los nodos.
     * Necesario antes de ejecutar el DFS
     */
    private void ReiniciarVisitados(){
        Nodo pActual = pFirst;
        while(pActual != null){
            pActual.AgregarVisitado(false);
            pActual = pActual.pNext;
        }
    }
}
