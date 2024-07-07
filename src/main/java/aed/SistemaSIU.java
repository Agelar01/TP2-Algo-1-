package aed;

import java.util.ArrayList;

public class SistemaSIU {

    /* 
    Objeto SistemasSIU con atributos que representan:
        - facultad: Trie cuyas claves son strings con los nombres de las carreras,
        y los valores son Tries cuyas claves son strings con el nombre de las materias y sus valores son de clase Materia.

        - alumnos: Trie cuyas claves son strings de las libretas de todos los alumnos de la facultad 
        y sus valores son las cantidades de materias en las que están inscriptos los alumnos. 
 
    Inv de representación: 
        - Todas las clases cumplen con su respectivo invariante de presentación.
        - Las claves del Trie facultad deben ser la primera componente de la tupla ParCarreraMateria.
        - Las claves del Trie materias, obtenido como valor del Trie facultad, deben ser elementos de la segunda componente de la tupla ParCarreraMateria.
        - Las claves del Trie alumno son las LUs (elems) de la lista libretasUniversitarias.
        - Los valores del Trie alumno deben ser como máximo la cantidad claves que haya en el Trie materias, obtenido como valor del Trie carrera.
        - La cantidad de alumnos inscriptos a las materias debe ser como máximo la cantidad de claves que haya en el Trie alumno.
        - Los elementos de la lista padres de la clase Materia deben ser los Tries que se obtienen como valor del Trie facultad.
    */

    private Trie<Trie<Materia>> facultad; 
    private Trie<Alumno> alumnos; 

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }


    /*
    La complejidad del constructor es O( ∑ |c|∗|Mc| + ∑    ∑ |n|+ E)
                                        c∈C          m∈M n∈Nm
    Donde:
     - C es el conjunto de carreras de grado, y c sus elementos;
     - M es el conjunto de todas las materias y m sus elementos;
     - Mc es el conjunto de materias de la carrera de grado c;
     - Nm es el conjunto de nombres de la materia m y n sus elementos;
     - E es la cantidad de alumnos de la facultad;
     - Em es el total de alumnos inscriptos a la materia m;

    */
    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        
        // Inicializamos los atributos privados.
        Trie<Alumno> alumnos = new Trie<Alumno>();
        Trie<Trie<Materia>> facultad = new Trie<Trie<Materia>>();

        /*
        Este for completa el Trie alumnos, con todas las LUs de libretasUniversitarias.
         Esto nos costaría |LU| por cada LU en la lista,
        pero como la long de las LUs está acotada, la complejidad del for es O(E)
        */
        for(int i=0; i < libretasUniversitarias.length; i++) {  
            Alumno alumno = new Alumno();
            alumnos.definir(libretasUniversitarias[i], alumno); 
        }

/////////////////////// 

        // Este otro for agrega las carreras al Trie facultad, y como valor asigna un Trie vacío, que luego llenaremos.
        
        // Cabe recalcar que no nos importa meter una misma carrera dos veces, 
        // ya que se sobreescribirá y como el valor es un trie vacío, no habrá problemas.
        for (int i=0; i < infoMaterias.length; i++) {         
            for (int j = 0; j < infoMaterias[i].getParesCarreraMateria().length; j++){ 
                    Trie<Materia> carrera = new Trie<Materia>(); 
                    facultad.definir(infoMaterias[i].getParesCarreraMateria()[j].getCarrera(), carrera);
            }
        }

    // Hasta ahora tenemos la raíz del SistemaSIU que tiene como atributos a un Trie<Alumnos> (cuyas claves son libretas y sus valores ints),
    // y a un Trie<Materia> (cuyas claves son los nombres de las materias y sus valores, por ahora vacíos, son de la clase Materia),

    // Nos falta definir las materias de cada carrera:

        for (int i = 0; i < infoMaterias.length; i++) {
            
            Materia materia = new Materia(infoMaterias[i]);
            
            for (int j = 0; j < infoMaterias[i].getParesCarreraMateria().length; j++) {
                Trie<Materia> carrera = facultad.obtener(infoMaterias[i].getParesCarreraMateria()[j].getCarrera()); 
                materia.agregarPadre(carrera); // agregamos un puntero al Trie con la carrera en la que está la materia.
                carrera.definir(infoMaterias[i].getParesCarreraMateria()[j].getNombreMateria(), materia); 
            }
        }
    
        this.alumnos = alumnos;
        this.facultad = facultad;
    }

    //Complejidad: O(|c| + |m|)
    public void inscribir(String estudiante, String carrera, String materia){

        facultad.obtener(carrera).obtener(materia).inscribirAlumno(estudiante);
        /* Esto accede al trie carreras, con el el nombre de la carrera |c|,
        luego baja por el segundo trie con el nombre de la materia |m|,
        y en o(1) inscribe al alumno, añadiendolo en una lista. */

        alumnos.obtener(estudiante).sumarUnaInscripcion();
        /* Esto baja por el trie estudiante con su LU como clave, pero como está acotada, es O(1) acceder a su significado.
        y luego en o(1) le suma uno a sus materias inscriptas. */
    }

    // Complejidad: O(|c| + |m|)
    public int inscriptos(String materia, String carrera){

        /* Primero bajamos por el primer trie con el nombre de la carrera |c|,
        luego bajamos por el segundo trie con el nombre de la materia |m|,
        y luego devolvemos en O(1) la cantidad de inscriptos ya que tenemos guardada esa variable. */

        return facultad.obtener(carrera).obtener(materia).cantidadAlumnosInscriptos();    
    }

    // Complejidad: O(|c| + |m|)
    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        if (cargo == CargoDocente.PROF) {
            facultad.obtener(carrera).obtener(materia).agregarPROF();
        }
        
        if (cargo == CargoDocente.JTP) { 
            facultad.obtener(carrera).obtener(materia).agregarJTP();
        }

        if (cargo == CargoDocente.AY1) { 
            facultad.obtener(carrera).obtener(materia).agregarAY1();
        }

        if (cargo == CargoDocente.AY2) { 
            facultad.obtener(carrera).obtener(materia).agregarAY2();
        }

        /* Hacemos comparaciones con una tupla en O(1),
        luego bajamos por el trie con el nombre de la carrera |c|,
        volvemos a bajar por el segundo trie con el nombre de la materia |m|,
        y en O(1) sumamos 1 a la variable correspondiente. */
    }

    // Complejidad: O(|c| + |m|)
    public int[] plantelDocente(String materia, String carrera){

        /*Bajamos por el trie con el nombre de la carrera |c|,
        volvemos a bajar por el segundo trie con el nombre de la materia |m|,
        y en O(1) creamos una lista y agregamos 4 elementos.*/

        return facultad.obtener(carrera).obtener(materia).plantelDocente();	    
    }

    // Complejidad: O(|c|+ |m|)
    public boolean excedeCupo(String materia, String carrera){
        Materia materiaAcomparar = facultad.obtener(carrera).obtener(materia);

        /* Bajamos por el trie con el nombre de la carrera |c|,
        volvemos a bajar por el segundo trie con el nombre de la materia |m|,
        guardamos la Materia en una variable, accedemos a la cantidad de alumnos en O(1),
        luego hacemos una cuenta entre variables de Materia, que es O(1),
        y hacemos una comparación entre ints en O(1). */
        
        return materiaAcomparar.cantidadAlumnosInscriptos() > materiaAcomparar.cupo();	    
    }

    // Complejidad: O( ∑ |c|)
    //                c∈C
    public String[] carreras(){ 

        /* Recorremos todas las claves del Trie y las devolvemos en forma de array con el método inorder en O(|c|),
        por cada palabra en el trie, por lo que la complejidad total es  O( ∑ |c|)
                                                                           c∈C
        */

        return facultad.inorder();
    }

    // Complejidad: O(|C| + ∑ |m|)
    //                     m∈C
    public String[] materias(String carrera){

        /* Idem anterior, pero primero entramos al trie correspondiente a la carrera en O(|C|),
        luego recorremos todas las claves del Trie y las devolvemos en forma de array con el método inorder en O(|m|),
        por cada palabra en el trie, por lo que la complejidad total es  O(|C|+ ∑ |m|)
                                                                               m∈C
        */

        return facultad.obtener(carrera).inorder();
    }

    // Complejidad: O(1)
    public int materiasInscriptas(String estudiante){

        /* Bajamos por el trie alumnos con el string del estudiante (LU) como clave,	 
        teniendo en cuenta que las LUs están acotadas, se hace en O(1),
        entramos al significado, que es una clase Alumno,
        y devolvemos la cantidad de materias en O(1) ya que la teníamos guardada. */

        return alumnos.obtener(estudiante).cantMaterias();	    
    }

    // Complejidad: O(|c|+ |m|+∑ |n|+ Em)
    //                        n∈Nm
    public void cerrarMateria(String materia, String carrera){
        Trie<Materia> carr = facultad.obtener(carrera);
        Materia materiaABorrar = carr.obtener(materia);
        ArrayList<Trie<Materia>> listaPadres = materiaABorrar.getPadres();
        ParCarreraMateria[] paresCM = materiaABorrar.infoMateria();
        ArrayList<String> alumnosInscriptos = materiaABorrar.alumnosInscriptos();
        int cantNombres = paresCM.length;
    
        // Desincribimos a los alumnos,
        int cantidadAlumnosInscriptos = materiaABorrar.cantidadAlumnosInscriptos();
        while (cantidadAlumnosInscriptos > 0) {
            alumnos.obtener(alumnosInscriptos.get(0)).restarUnaInscripcion();
            materiaABorrar.desinscribirAlumno(alumnosInscriptos.get(0));
            cantidadAlumnosInscriptos--;
        }

        // borramos la materia de todas las carreras de las que forma parte,
        int i = 0;
        while (i < cantNombres) {
            listaPadres.get(i).eliminar(paresCM[i].getNombreMateria());
            i++;
        }
    }
}
