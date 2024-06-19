package aed;

import java.util.ArrayList;

public class Materia {
    private String nombre;
    private int profesores;
    private int jtps;
    private int ayudantes1;
    private int ayudantes2; 
    private ArrayList<String> alumnosInscriptos;
    // padres?? no sé cómo representarlos
    //private Nodo[] padres;

    public Materia(String nombre){
        this.nombre = nombre;
        profesores = 0;
        jtps = 0;
        ayudantes1 = 0;
        ayudantes2 = 0;
        alumnosInscriptos = new ArrayList<String>();
        // padres = [] ni idea
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
    }

    public void desinscribirAlumno(  String libreta) {
        this.alumnosInscriptos.remove(libreta);
    }

}
