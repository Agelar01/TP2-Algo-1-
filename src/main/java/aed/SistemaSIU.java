package aed;

import java.util.ArrayList;

import javax.crypto.spec.RC2ParameterSpec;

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
    

    
    //private Trie<String, String> facultad;
    //private Trie<String, Integer> alumnos;
    
    // OBS si pongo private Trie<String>, no me deja que en el método definir le pase un valor(definición) de tipo que no sea String.

    // me gustaría ponerlo como acá abajo pero java se queja:

    private Trie<Trie<Materia>> facultad; // el que tiene las carreras. OBS a java no le gusta esto, pero me parece que sería la posta
    private Trie<Alumno> alumnos; // trie con las libretas de todos los alumnos de la facultad. cada una apunta a la cantidad de materias en las que está inscripto. 
    private ArrayList<String> carreras; // algo para contar las carreras? 

    /////////////////////

    // En el constructor debo construir correctamente estos atributos, hay que corregir lo que está hecho actualmente

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        
        // inicializo los atribuutos privados
        Trie<Alumno> alumnos = new Trie<Alumno>();
        Trie<Trie<Materia>> facultad = new Trie<Trie<Materia>>();
        ArrayList<String> carreras = new ArrayList<String>();

        //este for agrega las libretas de todos los alumnos al Trie alumnos, y no asigna ninguna inscripción a materias
        for(int i=0; i < libretasUniversitarias.length; i++) {
            Alumno alumno = new Alumno(libretasUniversitarias[i]);
            alumnos.definir(libretasUniversitarias[i], alumno); 
        }
        

        //este for agrega las carreras a la lista de carreras.
        for (int i=0; i < infoMaterias.length; i++) { // OBS infoMaterias.length me dice cuántas materias distintas hay (o sea sin contar las repetidas)
                        
            for (int j=0; j < infoMaterias[i].getParesCarreraMateria().length; j++){ // OBS infoMaterias[i].getParesuCarreraMateria().length me dice en cuántas carreras está la materia (o cuántos nombres tiene la materia)
                if (j == 0){
                    carreras.add(infoMaterias[i].getParesCarreraMateria()[j].getCarrera());
                }

                if (!carreras.contains(infoMaterias[i].getParesCarreraMateria()[j].getCarrera())) { // si la carrera que dice ahí no está en mi lista de carreras, la agrego.
                    carreras.add(infoMaterias[i].getParesCarreraMateria()[j].getCarrera());
                }
            }
        }


        //este for define como valor de la clave "nombreDeCarrera" (carreras.get[i]) a un nuevo Trie<Materia>
        for (int i = 0; i < carreras.size(); i++) {
            if (facultad.obtener(carreras.get(i)) == null){
                Trie<Materia> carrera = new Trie<Materia>();
                facultad.definir(carreras.get(i), carrera);
            }
        }

        //hasta ahora tenemos la raíz del SistemaSIU apuntando a un Trie<Alumnos> (cuyas claves son libretas y sus valores ints) y a un Trie<Materia> (cuyas claves son los nombres de las materias y sus valores, por ahora vacíos, son de la clase Materia).
        //ahora falta asignarle las definiciones (materias) a cada carrera
        for (int i = 0; i < infoMaterias.length; i++) { // OBS infoMaterias.length me dice cuántas materias distintas hay (o sea sin contar las repetidas)
            
            Materia materia = new Materia(infoMaterias[i]); // creo las materias que se dictan en la facu. después voy a poder cerrarlas, pero no agregar materias nuevas
            
            for (int j = 0; j < infoMaterias[i].getParesCarreraMateria().length; j++) { // OBS infoMaterias[i].getParesuCarreraMateria().length me dice en cuántas carreras está la materia (o cuántos nombres tiene la materia)
                facultad.obtener(infoMaterias[i].getParesCarreraMateria()[j].getCarrera()).definir(infoMaterias[i].getParesCarreraMateria()[j].getNombreMateria(), materia);
            }
        }

        /*for (int i = 0; i < infoMaterias.length; i++) { // OBS infoMaterias.length me dice cuántas materias distintas hay (o sea sin contar las repetidas)
            
            Materia materia = new Materia(infoMaterias[i]); // creo las materias que se dictan en la facu. después voy a poder cerrarlas, pero no agregar materias nuevas
                  
                // este for crea los Tries de cada carrera
                for (int j=0; j < infoMaterias[i].getParesCarreraMateria().length; j++){
                    if (facultad.obtener(infoMaterias[i].getParesCarreraMateria()[j].getCarrera() == null)) {
                        Trie<Materia> carrera = new Trie<Materia>();
                        facultad.definir(infoMaterias[i].getParesCarreraMateria()[j].getCarrera(), carrera);
                    }
                }
            }
        }*/


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
        //OBSERVACIÓN: creo que no hace falta el if porque no importa si el string del nombre de la carrera ya está en el trie de carreras. a esa carrera le vamos a asignar otra materia (¿otra definición?) 
    }

    public void inscribir(String estudiante, String carrera, String materia){
        facultad.obtener(carrera).obtener(materia).inscribirAlumno(estudiante); 
        // para todos los métodos que siguen, creo que esto es lo que queremos que funcione
        // por eso quiero poner private Trie<String,  Materia> facultad 
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        if (cargo == CargoDocente.PROF) { // ni idea cómo usar CargoDocente
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
    }

    public int[] plantelDocente(String materia, String carrera){
        return facultad.obtener(carrera).obtener(materia).plantelDocente();	    
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        return facultad.obtener(carrera).obtener(materia).alumnosInscriptos().size();	    
    }

    public boolean excedeCupo(String materia, String carrera){
        return facultad.obtener(carrera).obtener(materia).alumnosInscriptos().size() > facultad.obtener(carrera).obtener(materia).cupo();	    
    }

    public String[] carreras(){ 
        String[] listaCarreras = new String[this.carreras.size()];
        //ArrayList<String> carrerasOredenadas = this.carreras.sort(); // habría que hacer una función que ordene el array carreras o aprender a usar el método sort()
        
        for (int i = 0; i< carreras.size(); i++) {
            listaCarreras[i] = carreras.get(i);
        }
        return listaCarreras;	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    //private String[] ordenarLexicograficamente(String[] lista) {}

    public int materiasInscriptas(String estudiante){
        return alumnos.obtener(estudiante).cantMaterias();	    
    }
}