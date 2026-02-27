/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 *
 * @author Juan Coll
 * Clase Necesaria para la implementacion del DFS
 */
public class Pila {
    private NodoAuxiliar pFirst;
    
    public Pila(){
        this.pFirst = null;
    }
    
    public boolean esVacia(){
        return pFirst == null;
    }
    /**
     * Metodo para agregar al inicio de la pila (apilar)
     * @param pValor es el valor a añadir a la pila.
     */
    public void Apilar(Nodo pValor){
        NodoAuxiliar pNew = new NodoAuxiliar(pValor);
        pNew.pNextAuxiliar = this.pFirst;
        this.pFirst = pNew;
    }
    /**
     * metodo para quitar el primero de la pila.
     * @return necesario para retornar el Nodo completo.
     */
    public Nodo Desapilar(){
        if (esVacia()){
            return null;
        }
        NodoAuxiliar contenedor = this.pFirst;
        Nodo proteinaExtraida = contenedor.proteina;
        this.pFirst = this.pFirst.pNextAuxiliar;
        return proteinaExtraida;
    }
}
