/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 * Clase fundamental para el programa, es la representacion de la interaccion entre las proteinas.
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
            
            pDestino.lista.Agregar(pOrigen, peso);
            pDestino.IncrementoGrado();
            // actualiza el Hub si y solo si este nodo tiene mas conexiones.
            if (HubPrincipal == null || pOrigen.ObtenerGrado() > HubPrincipal.ObtenerGrado()){
                HubPrincipal = pOrigen;
            }
            // revisa si el destino es el nuevo Hub
            if (pDestino.ObtenerGrado() > HubPrincipal.ObtenerGrado()){
                HubPrincipal = pDestino;
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
    /**
     * Metodo que nos ayudara a encontrar los componentes convexos de nuestras interacciones entre proteinas
     * @param pInicio es la primera proteina que se va a explorar.
     * @return es para que reciba todos los nodos que visitamos.
     */
    public ListaAuxiliar DFS(Nodo pInicio){
        ListaAuxiliar pVisitados = new ListaAuxiliar();
        Pila pila = new Pila();
        
        ReiniciarVisitados();
        
        pila.Apilar(pInicio);
        pInicio.AgregarVisitado(true);
        while (!pila.esVacia()){
            Nodo pActual = pila.Desapilar();
            pVisitados.insertar(pActual);
            
            Arco arco = pActual.lista.ObtenerPrimero();
            while(arco != null){
                Nodo pVecino = arco.getDestino();
                if(!pVecino.esVisitado()){
                    pVecino.AgregarVisitado(true);
                    pila.Apilar(pVecino);
                }
                arco = arco.ObtenerpNext();
            }
        }
        return pVisitados;
    }
}
