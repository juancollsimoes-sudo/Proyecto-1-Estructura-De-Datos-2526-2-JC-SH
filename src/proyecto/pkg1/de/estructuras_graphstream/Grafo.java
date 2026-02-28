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
        // el algortimo comienza marcando el inciio y poniendolo en la pila
        pila.Apilar(pInicio);
        pInicio.AgregarVisitado(true);
        while (!pila.esVacia()){
            // el uso de LIFO nos permite explorar lo mas profundo del grafo
            Nodo pActual = pila.Desapilar();
            pVisitados.insertar(pActual);
            
            Arco arco = pActual.lista.ObtenerPrimero();
            while(arco != null){
                Nodo pVecino = arco.getDestino();
                // Si el vecino no ha sido procesado, se marca y se apila para futura exploracion.
                if(!pVecino.esVisitado()){
                    pVecino.AgregarVisitado(true);
                    pila.Apilar(pVecino);
                }
                arco = arco.ObtenerpNext();
            }
        }
        return pVisitados;
    }
    /**
     * Prepara el grafo para una ejecucion de Dijkstra.
     * Coloca la distancia minima de todos los nodos en infinito,
     * elimina las referencias a predecesores y marca todos los nodos como no visitados.
     */
    private void ReiniciarDijkstra(){
        Nodo pActual = pFirst;
        while(pActual != null){
            pActual.AgregarDistanciaMinima(Double.MAX_VALUE);
            pActual.AgregarPredecesor(null);
            pActual.AgregarVisitado(false);
            pActual = pActual.pNext;
        }
    }
    /**
     * Rastrea los padres desde el destino al inicio para armar el camino
     * @param pInicio Nodo de partida.
     * @param pDestino Nodo de llegada.
     * @return Lista con la ruta desde el inicio hasta el destino
     */
    private ListaAuxiliar ReconstruirRuta(Nodo pInicio, Nodo pDestino){
        ListaAuxiliar ruta = new ListaAuxiliar();
        Nodo pActual = pDestino;
        
        if (pDestino.ObtenerDistanciaMinima() == Double.MAX_VALUE){
            return ruta;
        }
        
        while (pActual != null){
            ruta.insertar(pActual);
            if(pActual == pInicio) break;
            pActual = pActual.ObtenerPredecesor();
        }
        return ruta;
    }
    /**
     * Calcula la ruta de menor resistencia entre dos proteinas.
     * Usa la cola de prioridad para explorar siempre el camino mas corto
     * disponible y actualiza las distancia de los nodos vecinos
     * @param pInicio Nodo de partida.
     * @param pDestino Nodo objetivo
     * @return ListaAuxiliar con la ruta optima desde inicio a destino
     */
    public ListaAuxiliar Dijkstra(Nodo pInicio, Nodo pDestino){
        // preparamos el algortimo y creamos la cola de prioridad
        ReiniciarDijkstra();
        pInicio.AgregarDistanciaMinima(0);
        ColaPrioridad cola = new ColaPrioridad();
        cola.Encolar(pInicio);
        
        while(!cola.esVacia()){
            Nodo pActual = cola.Desencolar();
            if(pActual == pDestino){
                break;
            }
            Arco arco = pActual.lista.ObtenerPrimero();
            while (arco != null){ 
                Nodo pVecino = arco.getDestino();
                double peso = arco.getPeso();
                double nuevaDistancia = pActual.ObtenerDistanciaMinima() + peso;
                // Relajacion de la arista: solo actualizamos si el camino es mas corto
                if(nuevaDistancia < pVecino.ObtenerDistanciaMinima()){
                    pVecino.AgregarDistanciaMinima(nuevaDistancia);
                    pVecino.AgregarPredecesor(pActual);
                    cola.Encolar(pVecino);
                }
                arco = arco.ObtenerpNext();
            }
        }
        // el backtracking reconstruye la ruta a partir de los predecesores marcados
        return ReconstruirRuta(pInicio, pDestino);
    }
}

