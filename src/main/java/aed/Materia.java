package aed;

import java.util.ArrayList;

//objeto materia con atributos que representan la cant de cada tipo de docente, la lista de los alumnos inscriptos, punteros a los distintos nombres de la materia 
public class Materia {
    private int profesores;
    private int jtps;
    private int ayudantes1;
    private int ayudantes2; 
    private ArrayList<String> alumnosInscriptos;
    private InfoMateria paresCarreraMateria;
    private ArrayList<Trie<Materia>> padres;


    //Constructor. inicializa una materia nueva con 0 inscriptos y 0 docentes
    public Materia(InfoMateria paresCarreraMateria) { 
        this.paresCarreraMateria = paresCarreraMateria;
        profesores = 0;
        jtps = 0;
        ayudantes1 = 0;
        ayudantes2 = 0;
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
        return this.alumnosInscriptos.size();
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

    public void sacarPROF() {
        if (this.profesores > 0) {
            this.profesores--;
        }
    }

    public void agregarJTP() {
        this.jtps++;
    }

    public void sacarJTP() {
        if (this.jtps > 0) {
            this.jtps--;
        }
    }

    public void agregarAY1() {
        this.ayudantes1++;
    }

    public void sacarAY1() {
        if (this.ayudantes1 > 0) {
            this.ayudantes1--;
        }
    }

    public void agregarAY2() {
        this.ayudantes2++;
    }

    public void sacarAY2() {
        if (this.ayudantes2 > 0) {
            this.ayudantes2--;
        }
    }

    public void inscribirAlumno(String libreta) {
        this.alumnosInscriptos.add(libreta);
    }

    public void desinscribirAlumno(String libreta) {
        this.alumnosInscriptos.remove(libreta);
    }
    
    // devuelve una lista con los pares carrera-materia que representan la misma materia
    public ParCarreraMateria[] infoMateria() {
        return this.paresCarreraMateria.getParesCarreraMateria();
    }
    
    // devuelve una lista con los distintos nombres que tiene la materia (si una materia está en más de una carrera, tiene nombres distintos para cada carrera)
    public String[] listaNombresMateria() {
        int cantNombres = this.paresCarreraMateria.getParesCarreraMateria().length;
        String[] listaNombres = new String[cantNombres];
        for (int i = 0; i < cantNombres; i++) {
            listaNombres[i] = this.paresCarreraMateria.getParesCarreraMateria()[i].getNombreMateria();
            
        }
        return listaNombres;
    }

    // devuelve una lista con las carreras en las que está la materia
    public String[] listaCarreras() {
        int cantCarreras = this.paresCarreraMateria.getParesCarreraMateria().length;
        String[] listaCarreras = new String[cantCarreras];
        for (int i = 0; i < cantCarreras; i++) {
            listaCarreras[i] = this.paresCarreraMateria.getParesCarreraMateria()[i].getCarrera();
        }
        return listaCarreras;
    }
}
