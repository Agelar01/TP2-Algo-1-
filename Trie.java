import java.util.ArrayList;

public class Trie {
    private class Nodo {
        boolean esFinalDePalabra;
        T definicion;
        ArrayList<Nodo> hijos;    
        
        Nodo() {
            this.hijos = new ArrayList<>();
            this.definicion = null;
            
            //this.esFinalDePalabra = false; // no sé si tiene sentido esto, si no tiene definición es porque no es final de palabra
        }
    }
    private Nodo raiz;
    private int tamaño;
    
    // no creo que hagan falta pero los pongo
    private Nodo actual; // para recorrer los nodos
    private int indice; // ubicación en la clave (si son cadenas de texto, en qúe caracter estamos posicionados)
    
    public Trie() {
        raiz = new Nodo();
        tamaño = 0;
    }
    
    public int tamaño(){
        return tamaño;
    }

    public boolean está(String clave) {
        Nodo actual = raiz;
        //int indice = 0;
        char[] listaClave = clave.toCharArray();
        
        if (raiz == null) {
            return false;
        }

        if (listaClave.lenght == 0){ // si clave == "" digo false, excede los 256 caracteres de ascii me parece
            return false; // no me fijé en la especificación, quizás esto está de más
        }
        else {
            for (int indice = 0; indice < listaClave.length; indice++){
                if (indice = listaClave.length -1){ // si estoy al final de la clave y:
                    if (actual.hijos.get((int) listaClave[indice]).definicion == null){ // no hay definición, digo false
                        return false;
                    } else if (actual.hijos.get((int) listaClave[indice]).definicion != null){ // hay definición, digo true
                        return true;
                    }
                } else { // si no estoy al final de la clave, avanzo
                    if (actual == null){
                        return false; // no puedo llegar al nodo que representa la clave
                    } else {
                        actual = actual.hijos.get((int) listaClave[indice]);
                    }
                }
            }
            
            /*for (int i = 0; i < listaClave.length; i++){
                if (i == listaClave.lenght - 1){
                    if (actual.hijos.get(i))
                }
            }*/
                /*
                if (actual.hijos.get((int) c) == null){
                    return false;
                } else if ((!actual.hijos.get((int) c).esFinalDePalabra) && actual.hijos.get((int) c).definicion == null){
                    actual = actual.hijos.get((int) c);
                } else if (actual.hijos.get((int) c).esFinalDePalabra) { 
                    return true;                    
                }*/
        }    
    }

    public T obtener(String clave){
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray();
        for (int indice = 0; indice < listaClave.length; indice++){
            if (indice = listaClave.length -1){ // si estoy al final de la clave
                return actual.definicion;
            } 
            else { // si no estoy al final de la clave, avanzo
                    actual = actual.hijos.get((int) listaClave[indice]);
            }
        }
        return actual.definicion;
    }

    public void definir(String clave, T valor){
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray();
        if (raiz != null) { // probablemente al pedo
            if (listaClave.length == 0){ // probablemente al pedo, ya lo hace Nodo()
                raiz.definicion = null;
            } else if (listaClave.length == 1){ //clave con un caracter
                if (raiz.hijos.get((int) listaClave[0]) == null){
                    Nodo nuevo = new Nodo();
                    raiz.hijos.add(nuevo);
                    nuevo.definicion = valor;
                    tamaño++;
                    //actual = nuevo; 
                } else {
                    actual = raiz.hijos.get((int) listaClave[0]);
                    actual.definicion = valor;
                }
            } else { //clave con varios caracteres
                for (int indice = 0; indice < listaClave.length -1; indice++){  // avanzo/agrego nodos con todos los caracteres menos el último
                    if (actual.hijos.get((int) listaClave[indice]) == null){
                        Nodo nuevo = new Nodo();
                        actual.hijos.add(nuevo);
                        actual = nuevo;
                        tamaño++;
                    } else {
                        actual = actual.hijos.get((int) listaClave[indice]);   
                    }
                }
                //cuando estoy en el último caracter de la clave
                if (actual.hijos.get((int) listaClave[listaClave.length -1]) == null){
                    Nodo nuevo = new Nodo();
                    nuevo.definicion = valor;
                    tamaño++;
                } else {
                    actual.hijos.get((int) listaClave[listaClave.length -1]).definicion = valor;
                }
            } 
        } 
    }

    public void eliminar(String clave){
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray();
        Nodo ultimoNodo = raiz; // último nodo que NO hay que borrar
        int ultimoIndice = 0;
        for (int indice = 0; indice < listaClave.length; indice++){
            if (indice = listaClave.length - 1){ // si estoy al final de la clave
                actual = ultimoNodo;
                actual.hijos.set((int) listaClave[ultimoIndice + 1], null); // o ultimoIndice, sin el +1. No estoy seguro.
            }
            if (actual.hijos.size() >= 2 || (indice != listaClave.length -1 && actual.definicion != null)){
                ultimoNodo = actual;
                ultimoIndice = indice;
            }
            else { // si no estoy al final de la clave, avanzo
                    actual = actual.hijos.get((int) listaClave[indice]);
            }
        }
    }




}
