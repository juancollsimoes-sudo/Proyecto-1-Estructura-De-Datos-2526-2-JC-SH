/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 * Clase Obligatoria para el algoritmo de dijkstra, para que pueda hacer las comparaciones
 * @author Juan Coll
 */
public class ColaPrioridad {
    private NodoAuxiliar pFirst;

    public ColaPrioridad() {
        this.pFirst = null;
    }
    
    public boolean esVacia(){
        return pFirst == null;
    }
    /**
     * Inserta la proteina en orden segun su distancia minima (siguiendo lo que es una Priority Queque)
     * el que tenga la menor distancia siempre quedara de primero. 
     */
    public void Encolar(Nodo proteina){
        NodoAuxiliar pNew = new NodoAuxiliar(proteina);
        /*
        Este es e caso en que la cola este vacia o el nuevo Nodo es el mas liviano
        */
        if(esVacia() || proteina.ObtenerDistanciaMinima() < pFirst.proteina.ObtenerDistanciaMinima()){
            pNew.pNextAuxiliar = pFirst;
            pFirst = pNew;
        } else{
            NodoAuxiliar pActual = pFirst;
            /*
            Este caso, se busca el punto de insercion para que la cola siempre este ordenada
            de menor a mayor distancia.
            */
            while (pActual.pNextAuxiliar != null && pActual.pNextAuxiliar.proteina.ObtenerDistanciaMinima() <= proteina.ObtenerDistanciaMinima()){
                pActual = pActual.pNextAuxiliar;
            }
            
            pNew.pNextAuxiliar = pActual.pNextAuxiliar;
            pActual.pNextAuxiliar = pNew;
        }
    }
    
    /**
     * Saca y devuelve siempre el nodo con la distancia mas corta
     */
    
    public Nodo Desencolar(){
        if (esVacia()){
            return null;
        }
        Nodo proteinaExtraida = pFirst.proteina;
        pFirst = pFirst.pNextAuxiliar;
        return proteinaExtraida;
    }
    
}
