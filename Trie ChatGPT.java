// con arraylist
import java.util.ArrayList;
import java.util.List;

public class Trie<T> {

    private class Nodo {
        boolean esFinalDePalabra;
        T definicion;
        char caracter;
        List<Nodo> hijos;

        Nodo() {
            this.esFinalDePalabra = false;
            this.definicion = null;
            this.caracter = '\0'; // Valor predeterminado para la raíz
            this.hijos = new ArrayList<>();
        }

        Nodo(char caracter) {
            this.esFinalDePalabra = false;
            this.definicion = null;
            this.caracter = caracter;
            this.hijos = new ArrayList<>();
        }

        Nodo getChild(char c) {
            for (Nodo hijo : hijos) {
                if (hijo.caracter == c) {
                    return hijo;
                }
            }
            return null;
        }

        void addChild(Nodo nodo) {
            hijos.add(nodo);
        }

        void removeChild(char c) {
            hijos.removeIf(hijo -> hijo.caracter == c);
        }
    }

    private Nodo root;
    private int size;

    public Trie() {
        root = new Nodo();
        size = 0;
    }

    public int tamaño() {
        return size;
    }

    public boolean está(T clave) {
        return search(clave.toString());
    }

    public void definir(T clave, T valor) {
        insert(clave.toString(), valor);
    }

    public void definirRapido(T clave, T valor) {
        insert(clave.toString(), valor);
    }

    public T obtener(T clave) {
        return searchValue(clave.toString());
    }

    public void borrar(T clave) {
        delete(clave.toString());
    }

    private void insert(String key, T value) {
        Nodo current = root;
        for (char c : key.toCharArray()) {
            Nodo child = current.getChild(c);
            if (child == null) {
                child = new Nodo(c);
                current.addChild(child);
            }
            current = child;
        }
        if (!current.esFinalDePalabra) {
            current.esFinalDePalabra = true;
            current.definicion = value;
            size++;
        } else {
            current.definicion = value;
        }
    }

    private boolean search(String key) {
        Nodo current = root;
        for (char c : key.toCharArray()) {
            current = current.getChild(c);
            if (current == null) {
                return false;
            }
        }
        return current.esFinalDePalabra;
    }

    private T searchValue(String key) {
        Nodo current = root;
        for (char c : key.toCharArray()) {
            current = current.getChild(c);
            if (current == null) {
                return null;
            }
        }
        return current.esFinalDePalabra ? current.definicion : null;
    }

    private boolean delete(String key) {
        return delete(root, key, 0);
    }

    private boolean delete(Nodo current, String key, int index) {
        if (index == key.length()) {
            if (!current.esFinalDePalabra) {
                return false;
            }
            current.esFinalDePalabra = false;
            current.definicion = null;
            size--;
            return current.hijos.isEmpty();
        }
        char c = key.charAt(index);
        Nodo child = current.getChild(c);
        if (child == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(child, key, index + 1);

        if (shouldDeleteCurrentNode) {
            current.removeChild(c);
            return current.hijos.isEmpty() && !current.esFinalDePalabra;
        }
        return false;
    }
}



//////
//////
////////
/////////
///////


public class Trie<T> {
    private Nodo root;  // Nodo raíz del trie
    private int size;  // Número de elementos en el trie

    public Trie() {
        root = new Nodo();  // Inicializa la raíz del trie
        size = 0;  // Inicializa el tamaño del trie a 0
    }

    public boolean está(T clave) {
        return search(clave.toString());  // Convierte la clave a cadena y busca en el trie
    }

    public void definir(T clave, T valor) {
        insert(clave.toString(), valor);  // Convierte la clave a cadena y la inserta en el trie
    }

    public void definirRapido(T clave, T valor) {
        // Asumiendo que "definirRapido" es igual a "definir" para esta implementación
        insert(clave.toString(), valor);  // Convierte la clave a cadena y la inserta en el trie
    }

    public T obtener(T clave) {
        return searchValue(clave.toString());  // Convierte la clave a cadena y obtiene el valor asociado
    }

    public void borrar(T clave) {
        delete(clave.toString());  // Convierte la clave a cadena y la borra del trie
    }

    public int tamaño() {
        return size;  // Devuelve el tamaño actual del trie
    }

    // Inserta una clave y su valor en el trie
    private void insert(String key, T value) {
        Nodo current = root;
        for (char c : key.toCharArray()) {
            int index = c;  // Convierte el carácter a su valor ASCII
            if (current.hijos[index] == null) {
                current.hijos[index] = new Nodo();  // Crea un nuevo nodo si no existe
            }
            current = current.hijos[index];
        }
        if (!current.esFinalDePalabra) {
            current.esFinalDePalabra = true;
            current.definicion = value;
            size++;
        } else {
            current.definicion = value;
        }
    }

    // Busca una clave en el trie
    private boolean search(String key) {
        Nodo current = root;
        for (char c : key.toCharArray()) {
            int index = c;
            current = current.hijos[index];
            if (current == null) {
                return false;
            }
        }
        return current.esFinalDePalabra;
    }

    // Busca el valor asociado a una clave en el trie
    private T searchValue(String key) {
        Nodo current = root;
        for (char c : key.toCharArray()) {
            int index = c;
            current = current.hijos[index];
            if (current == null) {
                return null;
            }
        }
        return current.esFinalDePalabra ? current.definicion : null;
    }

    // Borra una clave del trie
    private boolean delete(String key) {
        return delete(root, key, 0);
    }

    // Método recursivo para borrar una clave del trie
    private boolean delete(Nodo current, String key, int index) {
        if (index == key.length()) {
            if (!current.esFinalDePalabra) {
                return false;
            }
            current.esFinalDePalabra = false;
            current.definicion = null;
            size--;
            return isEmpty(current);
        }
        char c = key.charAt(index);
        int idx = c;
        Nodo node = current.hijos[idx];
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, key, index + 1);

        if (shouldDeleteCurrentNode) {
            current.hijos[idx] = null;
            return isEmpty(current) && !current.esFinalDePalabra;
        }
        return false;
    }

    // Verifica si un nodo está vacío
    private boolean isEmpty(Nodo node) {
        for (int i = 0; i < node.hijos.length; i++) {
            if (node.hijos[i] != null) {
                return false;
            }
        }
        return true;
    }

    // Clase interna privada Nodo
    private class Nodo {
        boolean esFinalDePalabra;  // Indica si el nodo es el final de una palabra
        T definicion;  // Valor asociado al nodo
        Nodo[] hijos;  // Array de nodos hijos

        Nodo() {
            this.esFinalDePalabra = false;
            this.definicion = null;
            this.hijos = new Nodo[256];  // Inicializa el array de hijos con 256 posiciones
        }
    }
}




///////
/////
///
//
//
//
//
//
//
// primera implementacion

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        this.children = new TrieNode[26]; // Para el alfabeto inglés
        this.isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a'; // Asignación de índice según el carácter
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current != null && current.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return true;
    }

    public boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false; // La palabra no está en el trie
            }
            current.isEndOfWord = false;
            return allChildrenNull(current); // Verificar si el nodo actual tiene hijos
        }
        int charIndex = word.charAt(index) - 'a';
        if (current.children[charIndex] == null) {
            return false; // La palabra no está en el trie
        }
        boolean shouldDeleteCurrentNode = deleteHelper(current.children[charIndex], word, index + 1);
        if (shouldDeleteCurrentNode) {
            current.children[charIndex] = null;
            return allChildrenNull(current);
        }
        return false;
    }

    private boolean allChildrenNull(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }
}

