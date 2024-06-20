package aed;

// trie con materias de cada carrera.
public class Carrera {
    
    private Trie<String, Materia> materias;

    public Carrera() {
        materias = new Trie<String, Materia>();
    }

    public Materia datosMateria(String nombreMat) {
        return this.materias.obtener(nombreMat);
    }
}
