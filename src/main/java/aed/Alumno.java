package aed;

public class Alumno<T extends Trie<T>>/* ????? */ {
    private int cantMaterias;

    public Alumno(String libreta/*??????????? Alumno() o Alumno(libreta) ?????????*/) {
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
}
