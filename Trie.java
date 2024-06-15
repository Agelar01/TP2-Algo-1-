import java.util.ArrayList;

public class Trie /*implements Diccionario*/{
    
    private class Nodo {
        boolean esFinalDePalabra;
        T definicion;
        Nodo[] hijos;
        //ArrayList<Nodo> hijos;    
        
        Nodo(){
            this.esFinalDePalabra = false;
            this.definicion = null;
            this.hijos = new Nodo[256];
        }
    }

    private Nodo raiz;
    private int tamaño;
    //private Nodo actual;

    public Trie() {
        raiz = new Nodo();
        tamaño = 0;
    }

    public boolean está(T clave){
        Nodo actual = raiz;
        for (char c : clave.toString().toCharArray()){
            int indice = c;
            actual = actual.hijos[indice];
            if (actual == null) {
                return false;
            }
        }
        return actual.esFinalDePalabra;
    }

    private void definir(T clave, T valor) {
        Nodo actual = raiz;
        for (char c : clave.toString().toCharArray()) {
            int indice = c;
            if (actual.hijos[indice] == null) {
                actual.hijos[indice] = new Nodo();
            }
            actual = actual.hijos[indice];
        }
        if (!actual.esFinalDePalabra) {
            actual.esFinalDePalabra = true;
            actual.definicion = valor;
            tamaño++;
        } else {
            actual.definicion = valor;
        }
    }

    //public void definirRapido(T clave, T valor){}

    public T obtener(T clave){
        Nodo actual = raiz;
        for (char c : clave.toString().toCharArray()) {
            int indice = c;
            actual = actual.hijos[indice];
            if (actual == null) {
                return null;
            }
        }
        return actual.esFinalDePalabra ? actual.definicion : null;
    }

    public void borrar(T clave){

    }

    public int tamaño(){
        return tamaño;
    }
}
