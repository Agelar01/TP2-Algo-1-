package aed;

// quizás es al pedo toda esta clase
public class Alumno/*<T extends Trie<T>>*//* ????? */ {
    private String libreta;
    //private Integer cantMaterias;
    private int cantMaterias;

    public Alumno(String libreta) {
        this.libreta = libreta;
        cantMaterias=0; //??????????
    }

    public void sumarUnaInscripcion() {
        this.cantMaterias++;
    }

    public void restarUnaInscripcion() {
        if (this.cantMaterias > 0){
            this.cantMaterias--;
        }
    }

    public int cantMaterias() {
        return this.cantMaterias;
    }
}
