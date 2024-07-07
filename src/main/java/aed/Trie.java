package aed;

import java.util.ArrayList;

public class Trie<T> implements Diccionario<String, T> {

    /*
    Inv de representación: 
        - Todos los nodos tienen un único padre (salvo la raíz y nodo con el significado).
        - Es un árbol.
        - Todos los nodos si no tienen hijos, tienen significado (no hay nodos inútiles).
    */

    private Nodo raiz;
    private int tamaño;

    private class Nodo {

        T definicion;
        ArrayList<Nodo> hijos;    
        
        Nodo() {
            this.hijos = new ArrayList<>();
            int i = 0;
            while (i < 256){
                this.hijos.add(null);
                i++;
            }
            this.definicion = null;
        }

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

    public boolean esta(String clave) {
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray();

        if (listaClave.length == 0){ // Si clave == "" digo false
            return false; 
        }
        else {
            for (int indice = 0; indice < listaClave.length; indice++){
                if (indice == listaClave.length - 1){ // Si estoy al final de la clave y:
                    // No hay definición, digo false;
                    if (actual.hijos.get((int) listaClave[indice]) != null && actual.hijos.get((int) listaClave[indice]).definicion == null){
                        return false;
                    }
                    // Hay definición, digo true; 
                    if (actual.hijos.get((int) listaClave[indice]) != null && actual.hijos.get((int) listaClave[indice]).definicion != null){
                        return true;
                    }
                } 
                else { // Si no estoy al final de la clave, avanzo:
                    if (actual == null) {
                        return false; // No puedo llegar al nodo que representa la clave;
                    }
                    if (actual.hijos.get((int) listaClave[indice]) == null) {
                        return false; // no puedo llegar al nodo que representa la clave;
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
        for (int indice = 0; indice < listaClave.length + 1; indice++){ // Más uno porque el primer indice es para la raíz
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
        char[] listaClave = clave.toCharArray(); // paso de 'clave' a {'c','l','a','v','e'}
        for (int indice = 0; indice < listaClave.length; indice++){ // Hago un for recorriendo {'c','l','a','v','e'}
            if (actual.hijos.get((int) listaClave[indice]) == null) { // Avanzo creando un nodo si no había palabra con este char
                Nodo nuevo = new Nodo();
                actual.hijos.set((int) listaClave[indice], nuevo); // Añado un nodo nuevo en actual.hijos en la posición posición indicada por (int) listaClave[indice] 
                if (indice == listaClave.length - 1){ // Si es el final de la clave, le añado el valor
                    nuevo.definicion = valor;
                }
                actual = nuevo; // Avanzo al nodo que acabo de crear
            }
            else { // Si en el Trie ya hay una palabra que empieza con el char listaClave[indice], avanzo al nodo que corresponde a ese caracter
                actual = actual.hijos.get((int) listaClave[indice]); // Avanzo si ya había palabra con ese char 
                if (indice == listaClave.length - 1){ // Si estoy en el final de mi clave, añado el valor
                    actual.definicion = valor; 
                }
            }
        }
        tamaño++;
    } 

    public void eliminar(String clave){
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray();
        Nodo ultimoNodo = raiz; // Último nodo que NO hay que borrar
        int ultimoIndice = 0;

        for (int indice = 0; indice < listaClave.length + 1; indice++) { // +1 porque indice = 0 mira la raíz
            // Si estoy en un nodo con más de un hijo o que es final de otra palabra, no lo debo borrar
            if (actual.tamañoDeHijos() > 1 || (actual.tamañoDeHijos() > 0 && actual.definicion != null)) {
                ultimoNodo = actual;
                ultimoIndice = indice;
            }
            if (indice == listaClave.length) { // Si estoy al final de la clave
                actual.definicion = null; // Le borro la definición
                tamaño--;
                if (actual.tamañoDeHijos() == 0) { // Si el nodo al final de la clave no tiene hijos
                    ultimoNodo.hijos.set((int) listaClave[ultimoIndice], null); // Borro todos los nodos que vienen después del último que no quiero borrar
                }
                if (actual.tamañoDeHijos() > 0) { // Si el nodo al final de la clave tiene hijos, ese nodo ese el último que no debo borrar
                    ultimoNodo = actual; 
                    ultimoIndice = indice;
                }
            }
            else { // Si no estoy al final de la clave, avanzo
                actual = actual.hijos.get((int) listaClave[indice]);
            }
        }
    }

    public String[] inorder() {
        ArrayList<String> claves = new ArrayList<>();
        inorderRecursivo(raiz, "", claves);
        return claves.toArray(new String[0]); // Este último paso es necesario ya que luego el TP, nos lo pide en tipo Array y no ArrayList.
    }

    private void inorderRecursivo(Nodo nodo, String prefijo, ArrayList<String> claves) {
        if (nodo.definicion != null) {
            claves.add(prefijo);
        }
        for (int i = 0; i < 256; i++) {
            if (nodo.hijos.get(i) != null) {
                inorderRecursivo(nodo.hijos.get(i), prefijo + (char) i, claves);
            }
        }
    }

}
