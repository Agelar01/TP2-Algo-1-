package aed;

public class SistemaSIU {

    //////////////////////
    
    //SANTI
    // Falta agregar atributos privados, van a ser dos: un Trie(carrera,materiaDeCarrera), donde materia de carrera es otro Trie(materia,datosMateria)
    // y datosMateria va ser un objeto con los datos de los profesores, datos de los alumnos y algo que nos ayude a borrar materias en O(1)
    
    // El otro atributo va a ser otro trie(alumno, cantidadDeMateriasInscripto)
    
    /////////////////////


    
    
    
    /////////////////////
    
    //LUCAS
    //¿¿¿hago una clase Carrera que tenga un atributo Materias que sea un Trie<Materia>???
    private Trie<String, String> facultad;
    private Trie<String, Integer> alumnos;
    // OBS si pongo private Trie<String>, no me deja que en el método definir le pase un valor(definición) de tipo que no sea String.

    // me gustaría ponerlo como acá abajo pero java se queja
    //private Trie<String,Carrera> facultad; // el que tiene las carreras. OBS a java no le gusta esto, pero me parece que sería la posta
    //private Trie<String,Alumno> alumnos; // trie con las libretas de todos los alumnos de la facultad. cada una apunta a la cantidad de materias en las que está inscripto. 
    

    /////////////////////

    // En el constructor debo construir correctamente estos atributos, hay que corregir lo que está hecho actualmente

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        /*Trie<String> carreras = new Trie<String>();
        int i = 0;
        while (i < infoMaterias.length){
            InfoMateria infoMateriaActual = infoMaterias[i];
            ParCarreraMateria[] listaParesCarreraMateria = infoMateriaActual.getParesCarreraMateria();
            int j = 0;
            while (j < listaParesCarreraMateria.length) {
                ParCarreraMateria parActual = listaParesCarreraMateria[j];
                String carrera = parActual.getCarrera();
                if (!carreras.esta(carrera)){ // Chequear si estaba, me afecta a la complejidad? // *****
                    carreras.definir(carrera, valor); // Falta ver que valor le quiero meter. Debería ser un trie con las materias de esa carrera.
                }
                j++;
            }
            i++;
        }*/

        // *****
        //OBSERVACIÓN: no hace falta el if porque no importa si el string del nombre de la carrera ya está en el trie de carreras. a esa carrera le vamos a asignar otra materia (¿otra definición?) 
        

        // lo volví a escribir para entenderlo, lo de santi creo que está bien salvo por el if
        //este for agrega las carreras al SistemaSIU y las materias a cada carrera
        for (int i=0; i < infoMaterias.length; i++) {
            for (int j=0; j < infoMaterias[i].getParesCarreraMateria().length; j++){
                facultad.definir(infoMaterias[i].getParesCarreraMateria()[j].carrera, infoMaterias[i].getParesCarreraMateria()[j].nombreMateria);
            }
        }

        // esta parte no está en lo que escribió santi
        //este for agrega las libretas de todos los alumnos al Trie alumnos, y no asigna ninguna inscripción a materias
        for(int i=0; i < libretasUniversitarias.length; i++) {
            alumnos.definir(libretasUniversitarias[i], 0);
        }
    }

    public void inscribir(String estudiante, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int[] plantelDocente(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }
}
