package aed;

public class Alumno {

    /*
    Objeto Alumno con atributos que representan:
        - cantMaterias: cantidad de materias en la que está incripto un alumno.

    Inv de representación: 
        - La cantidad de materias es mayor o igual a cero.
        --Debo hacerla conincidir con otra cosa
    */

    private int cantMaterias;

    public Alumno() {
        cantMaterias=0;
    }

    public void sumarUnaInscripcion() {
        this.cantMaterias++;
    }

    public void restarUnaInscripcion() {
        this.cantMaterias--;
    }

    public int cantMaterias() {
        return this.cantMaterias;
    }

}
