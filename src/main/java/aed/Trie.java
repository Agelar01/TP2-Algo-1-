package aed;

import java.util.ArrayList;

public class Trie<T> implements Diccionario<String, T> {

    private Nodo raiz;
    private int tamaño;

    private class Nodo {
        
        //Character letra;
        //char letra; 
            // OBS me parece que esto es al pedo, la letra (o el caracter ascii) está representado por la posición en hijos
        
            T definicion;
        ArrayList<Nodo> hijos;    
        
        Nodo() {
            this.hijos = new ArrayList<>();
            int i = 0;
            while (i < 256){ // Si quiero hacer esto, para qué uso un ArrayList y no un array normal? //porque lo dice el pdf de la clase jeje 
                this.hijos.add(null);
                i++;
            }
            this.definicion = null;
        }

        /*public char letra(){
            return 
        }*/

        private int tamañoDeHijos(){
            int contador = 0;
            int ind = 0;
            while (ind < 256){
                if(hijos.get(ind) != null){
                    contador++;
                }
                ind++;
            }
            return contador;
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
        char[] listaClave = clave.toCharArray(); //clave.toString().toCharArray(); 
        // si hago clave.toCharArray() me tira el error The method toCharArray() is undefined for the type String
        

        if (listaClave.length == 0){ // si clave == "" digo false, excede los 256 caracteres de ascii me parece
            return false; // no me fijé en la especificación, quizás esto está de más
        }
        else {
            for (int indice = 0; indice < listaClave.length; indice++){
                if (indice == listaClave.length - 1){ // si estoy al final de la clave y:
                    if (actual.hijos.get((int) listaClave[indice]).definicion == null){ // no hay definición, digo false
                        return false;
                    } 
                    if (actual.hijos.get((int) listaClave[indice]).definicion != null){ // hay definición, digo true
                        return true;
                    }
                } 
                else { // si no estoy al final de la clave, avanzo
                    if (actual == null){
                        return false; // no puedo llegar al nodo que representa la clave
                    } 
                    else{
                        actual = actual.hijos.get((int) listaClave[indice]);
                    }
                }
            }
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
        char[] listaClave = clave.toCharArray(); // paso de 'clave' a ['c','l','a','v','e'], se entiende
        for (int indice = 0; indice < listaClave.length; indice++){ // Hago un for recorriendo ['c','l','a','v','e']
            if (actual.hijos.get((int) listaClave[indice]) == null) { //  Avanzo creando un nodo si no había palabra con este char
                Nodo nuevo = new Nodo();
                //nuevo.letra = listaClave[indice];
                actual.hijos.set((int) listaClave[indice], nuevo); // Añado un nodo nuevo en actual.hijos en la posición posición indicada por (int) listaClave[indice] 
                if (indice == listaClave.length - 1){ // Si es el final de la clave, le añado el valor
                    nuevo.definicion = valor;
                }
                actual = nuevo; // avanzo al nodo que acabo de crear.
            }
            else { // si en el Trie ya hay una palabra que empieza con el char listaClave[indice], avanzo al nodo que corresponde a ese caracter
                actual = actual.hijos.get((int) listaClave[indice]); // Avanzo si ya había palabra con ese char 
                if (indice == listaClave.length - 1){ // Si estoy en el final de mi clave, añado el valor
                    actual.definicion = valor;
                }
            }
        }
        tamaño++;
    } 

    public void eliminar(String clave){ // ANDA, faltaría testearla más
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray(); // Vale usar toCharArray?
        Nodo ultimoNodo = raiz; // Último nodo que NO hay que borrar
        int ultimoIndice = 0;
        for (int indice = 0; indice < listaClave.length; indice++){
            if (indice == listaClave.length - 1){ // Si estoy al final de la clave
                if(actual.hijos.get((int) listaClave[indice]).tamañoDeHijos() == 0){ // Si no hay claves con este mismo prefijo...
                actual = ultimoNodo;
                actual.hijos.set((int) listaClave[ultimoIndice], null); // Le borro el hijo correspondiente al último que no hay que borrar
                }
                else {
                    actual.hijos.get((int) listaClave[indice]).definicion = null; // Si si hay otra clave con este prefijo, solo le borro el significado
                } 
                tamaño--;
            }
            if (actual.tamañoDeHijos() >= 2 || (indice != listaClave.length -1 && actual.definicion != null)){ // Actualizo el último a no borrar: Si el nodo es el final de una clave, o si tiene dos hijos (Solo quiero borrarle uno).
                ultimoNodo = actual;
                ultimoIndice = indice;
                actual = actual.hijos.get((int) listaClave[indice]);
            }
            else { // si no estoy al final de la clave, avanzo
                    actual = actual.hijos.get((int) listaClave[indice]);
            }
        }
    }
}
