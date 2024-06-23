package aed;

import java.util.ArrayList;

public class Materia {

    /* 
    Objeto Materia con atributos que representan:
        - La cantidad de cada tipo de docente.
        - alumnosInscriptos: lista de los alumnos inscriptos.
        - cantAlumnos: cantidad de alumnos inscriptos.
        - padres: lista de punteros a los Tries carrera que cursan la misma materia pero con diferentes nombres.
        - paresCarreraMateria: lista de paresCarreraMateria.
 
    Inv de representación:
        - Los ints que representan a los profesores son mayores o iguales a cero.
        - Los elementos de alumnosInscriptos son strings de LUs, no debe haber repetidos.
        - La var cantidadInscriptos es igual a la longitud de la lista AlumnosInscriptos.
        --Falta hablar de paresCarreraMateria y padres.
    */

    private int profesores;
    private int jtps;
    private int ayudantes1;
    private int ayudantes2;
    private int cantAlumnos; 
    private ArrayList<String> alumnosInscriptos;
    private InfoMateria paresCarreraMateria;
    private ArrayList<Trie<Materia>> padres;


    //Constructor: inicializa una materia nueva con 0 inscriptos y 0 docentes.
    public Materia(InfoMateria paresCarreraMateria) { 
        this.paresCarreraMateria = paresCarreraMateria;
        profesores = 0;
        jtps = 0;
        ayudantes1 = 0;
        ayudantes2 = 0;
        cantAlumnos = 0;
        alumnosInscriptos = new ArrayList<String>();
        padres = new ArrayList<Trie<Materia>>();
    }

    public ArrayList<Trie<Materia>> getPadres() {
        return this.padres;
    }

    public void agregarPadre(Trie<Materia> carrera) {
        this.padres.add(carrera);
    }

    public void borrarPadre(Trie<Materia> carrera) {
        this.padres.remove(carrera);
    }

    public int cupo() {
        int minimo = 250*this.profesores;
        if(100*this.jtps < minimo) {
            minimo = 100*this.jtps;
        }
        if (20*this.ayudantes1 < minimo) {
            minimo = 20*this.ayudantes1;
        }
        if (30*this.ayudantes2 < minimo) {
            minimo = 30*this.ayudantes2;
        }
        return minimo;
    }

    public ArrayList<String> alumnosInscriptos() {
        return this.alumnosInscriptos;
    }

    public int cantidadAlumnosInscriptos() {
        return cantAlumnos;
    }

    public int[] plantelDocente() {
        int[] plantel = new int[4];
        plantel[0] = this.profesores;
        plantel[1] = this.jtps;
        plantel[2] = this.ayudantes1;
        plantel[3] = this.ayudantes2;
        return plantel;
    }
    
    public void agregarPROF() {
        this.profesores++;
    }

    public void agregarJTP() {
        this.jtps++;
    }

    public void agregarAY1() {
        this.ayudantes1++;
    }

    public void agregarAY2() {
        this.ayudantes2++;
    }

    public void inscribirAlumno(String libreta) {
        this.alumnosInscriptos.add(libreta);
        this.cantAlumnos++;
    }

    public void desinscribirAlumno(String libreta) {
        this.alumnosInscriptos.remove(libreta);
        this.cantAlumnos--;
    }
    
    // devuelve una lista con los pares carrera-materia que representan la misma materia
    public ParCarreraMateria[] infoMateria() {
        return this.paresCarreraMateria.getParesCarreraMateria();
    }
    
}