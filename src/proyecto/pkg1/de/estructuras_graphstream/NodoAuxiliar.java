/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 *
 * @author andre
 */
public class NodoAuxiliar {
    public Nodo proteina;
    public NodoAuxiliar pNextAuxiliar;
    
    public NodoAuxiliar(Nodo proteina){
        this.proteina = proteina;
        this.pNextAuxiliar = null;
    }
    
}
