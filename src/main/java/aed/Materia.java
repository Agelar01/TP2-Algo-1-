package aed;

import java.util.ArrayList;

//objeto materia con atributos que representan la cant de cada tipo de docente, la lista de los alumnos inscriptos, punteros a los distintos nombres de la materia 
public class Materia {
    //private String nombre;
    private int profesores;
    private int jtps;
    private int ayudantes1;
    private int ayudantes2; 
    private ArrayList<String> alumnosInscriptos;
    private InfoMateria paresCarreraMateria; // me parece que con esto no puedo hacer borrarMateria en O(1) pero bueno
    // padres?? no sé cómo representarlos
    //private Nodo[] padres;


    //Constructor. inicializa una materia nueva con 0 inscriptos y 0 docentes
    public Materia(/*String nombre ,*/ InfoMateria paresCarreraMateria){ // no sé si tiene sentido guardar los otros nombres acá 
        //this.nombre = nombre;
        this.paresCarreraMateria = paresCarreraMateria;
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

    public int cupo() {
        return 250*this.profesores + 100*this.jtps + 20*this.ayudantes1 + 30*this.ayudantes2;
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
