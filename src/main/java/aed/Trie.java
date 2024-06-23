package aed;

import java.util.ArrayList;

public class Trie<T> implements Diccionario<String, T> {

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

        if (listaClave.length == 0){ // si clave == "" digo false
            return false; 
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

    public void eliminar(String clave){
        Nodo actual = raiz;
        char[] listaClave = clave.toCharArray();
        Nodo ultimoNodo = raiz; // Último nodo que NO hay que borrar
        int ultimoIndice = 0;
        for (int indice = 0; indice < listaClave.length; indice++){
            if (indice == listaClave.length - 1){ // Si estoy al final de la clave
                if(actual.hijos.get((int) listaClave[indice]).tamañoDeHijos() == 0){ // Si no hay claves con este mismo prefijo...
                actual = ultimoNodo;
                actual.hijos.set((int) listaClave[ultimoIndice], null); // Le borro el hijo correspondiente al último que no hay que borrar
                }
                else {
                    actual.hijos.get((int) listaClave[indice]).definicion = null; // Si si hay otra clave con este prefijo, sólo le borro el significado
                } 
                tamaño--;
            }
            if (actual.tamañoDeHijos() >= 2 || (indice != listaClave.length -1 && actual.definicion != null)){ // Actualizo el último nodo que no hay que borrar: si el nodo es el final de una clave, o si tiene dos hijos (sólo quiero borrarle uno).
                ultimoNodo = actual;
                ultimoIndice = indice;
                actual = actual.hijos.get((int) listaClave[indice]);
            }
            else { // si no estoy al final de la clave, avanzo
                    actual = actual.hijos.get((int) listaClave[indice]);
            }
        }
    }

    public ArrayList<String> devolverListaStrings(Trie<T> Trie){                                // No sé bien si usar Trie o usar Nodo
        Nodo nodo = Trie.raiz;
        ArrayList<String> listaStrings = new ArrayList<String>();
                                                                                                 // No se puede acceder a los nodos desde SistemaSiu y no sé como hacerlos públicos
                                                                                                  // Esta función está para usar la auxiliar y usarla desde SistemasSiu
        
        return devolverListaStringsAux(listaStrings, nodo);
    }

    public ArrayList<String> devolverListaStringsAux(ArrayList<String> arrayListString, Nodo nodo){
        Nodo actual = nodo;
        int i = 0;
        while (i < 256){
            if (actual.hijos.get(i).definicion != null){                        //Si hay un significado
                arrayListString.add(actual.hijos.get(i).definicion.toString()); //Lo hago un string y la pongo en la lista
                    if (actual.hijos.get(i).tamañoDeHijos() != 0){              //Veo si este camino tiene más significados

                        devolverListaStringsAux(arrayListString, actual.hijos.get(i)); // Hago recursión si hay más significados
                    }
                    else {                                                              //De no haber avanzo por el array
                        i++;
                    }
            }
            else {
                if (actual.hijos.get(i).tamañoDeHijos() == 0){                  //Si no hay significado en el nodo, avanzo por el array
                    i++;
                }
                else {                                                          //De haber significado avanzo al nodo siguiente
                    actual = actual.hijos.get(i);
                }
            }
        }
        return arrayListString;
    }
}
