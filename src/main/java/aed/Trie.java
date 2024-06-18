package aed;

import java.util.ArrayList;

public class Trie<T> {

    private Nodo raiz;
    private int tamaño;

    private class Nodo {
        T definicion;
        ArrayList<Nodo> hijos;    
        
        Nodo() {
            this.hijos = new ArrayList<>();
            int i = 0;
            while (i < 256){ // Si quiero hacer esto, para qué uso un ArrayList y no un array normal?
                this.hijos.add(null);
                i++;
            }
            this.definicion = null;
        }
    }
    
    public Trie() {
        raiz = new Nodo();
        tamaño = 0;
    }
    
    public int tamaño(){
        return tamaño;
    }

    public boolean esta(String clave) { //Santi: No lo revisé faltaría chequear y pasar en limpio.
        Nodo actual = raiz;
        //int indice = 0;
        char[] listaClave = clave.toCharArray();
        
        if (raiz == null) {
            return false;
        }

        if (listaClave.length == 0){ // si clave == "" digo false, excede los 256 caracteres de ascii me parece
            return false; // no me fijé en la especificación, quizás esto está de más
        }
        else {
            for (int indice = 0; indice < listaClave.length; indice++){
                if (indice == listaClave.length - 1){ // si estoy al final de la clave y:
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
        return false;    
    }

    public T obtener(String clave){
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray();
        for (int indice = 0; indice < listaClave.length + 1; indice++){ // Más uno pq el primer indice es para la raíz
            if (indice == listaClave.length){ // Si estoy al final de la clave, devuelvo el valor
                return actual.definicion;
            } 
            else { // Si no estoy al final de la clave, avanzo
                    actual = actual.hijos.get((int) listaClave[indice]);
            }
        }
        return actual.definicion; // En el caso de que no entre en el for, es decir sea la clave vacía
    }

    public void definir(String clave, T valor){
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray();
        for (int indice = 0; indice < listaClave.length; indice++){ // Hago un for recorriendo el string "clave"
            if (actual.hijos.get((int) listaClave[indice]) == null){ //  Avanzo creando un nodo si no había palabra con este char
                Nodo nuevo = new Nodo();
                actual.hijos.set((int) listaClave[indice], nuevo); // Si era null, le añado un nodo en esa posición
                if (indice == listaClave.length - 1){ // Si es el final de la clave, le añado el valor
                    nuevo.definicion = valor;
                }
                actual = nuevo;
            }
            else{
                actual = actual.hijos.get((int) listaClave[indice]); // Avanzo si ya había palabra con ese char 
                if (indice == listaClave.length - 1){ // Si estoy en el final de mi clave, añado el valor
                    actual.definicion = valor;
                }
            }
        }
        tamaño++;
    } 

    public void eliminar(String clave){ // NO anda
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray(); // Vale usar toCharArray?
        Nodo ultimoNodo = raiz; // último nodo que NO hay que borrar
        int ultimoIndice = 0;
        for (int indice = 0; indice < listaClave.length; indice++){
            if (indice == listaClave.length - 1){ // si estoy al final de la clave
                actual = ultimoNodo;
                actual.hijos.set((int) listaClave[ultimoIndice], null); // o ultimoIndice, sin el +1. No estoy seguro.
                tamaño--;
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
