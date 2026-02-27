/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 *
 * @author Juan Coll
 * Esta Clase es necesaria para guardar los cambios del DFS
 */
public class ListaAuxiliar {
    private NodoAuxiliar pFirst;

    public ListaAuxiliar() {
        this.pFirst = null;
    }
    /**
     * Esta clase SOLO inserta al incio de la lista para mas eficiencia.
     * @param Proteina el nodo del grafo que queremos guardar.
     */
    public void insertar(Nodo Proteina){
        NodoAuxiliar pNew = new NodoAuxiliar(Proteina);
        pNew.pNextAuxiliar = this.pFirst;
        this.pFirst = pNew;
    }   
}
