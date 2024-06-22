package aed;

public class Alumno {
    private String libreta;
    private int cantMaterias;

    public Alumno(String libreta) {
        this.libreta = libreta;
        cantMaterias=0;
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

    private String libreta() {
        return this.libreta;
    }
}
