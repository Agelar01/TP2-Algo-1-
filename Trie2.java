import java.util.ArrayList;

public class Trie2 {
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
        
        
        if (listaClave.lenght == 0){ // si clave == "" digo false
            return false; // no me fijé en la especificación, quizás está de más esto
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
                    actual = actual.hijos.get((int) listaClave[indice]);
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
}
