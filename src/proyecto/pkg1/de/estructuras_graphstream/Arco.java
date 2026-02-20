/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.de.estructuras_graphstream;

/**
 * Representa una conexion o arista entre dos proteinas en el Grafo.
 * @author andre
 */
public class Arco {
    private Nodo destino;
    private double peso;
    private Arco pNext;

    
 /** 
  * Inicializa una nueva interaccion entre proteinas con un peso especifico.
  * @param d el nuevo nodo destino a la conexion.
  * @param peso El valor de resistencia o costo de la interaccion.
  */
    public Arco(Nodo d, double peso) {
        this.destino = d;
        this.pNext = null;
        this.peso = peso;
    }
    
    /**
     * @return el Nodo al que apunta esta conexion. 
     */
    public Nodo getDestino(){
        return destino;
    }
    
    /**
     * @return el Peso o resistencia de esta interaccion especifica 
     */
    public double getPeso(){
        return peso;
    }
    /**
     * @return el siguiente arco en la lista de adyacencia. 
     */
    public Arco ObtenerpNext(){
        return pNext;
    }
    /**
     * @param pNext establece el siguiente arco en la cadena 
     */
    public void AgregarpNext(Arco pNext){
        this.pNext = pNext;
    }
    
    
}
