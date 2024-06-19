package aed;

public class SistemaSIU {

    // Falta agregar atributos privados, van a ser dos: un Trie(carrera,materiaDeCarrera), donde materia de carrera es otro Trie(materia,datosMateria)
    // y datosMateria va ser un objeto con los datos de los profesores, datos de los alumnos y algo que nos ayude a borrar materias en O(1)
    
    // El otro atributo va a ser otro trie(alumno, cantidadDeMateriasInscripto)

    
    
    // En el constructor debo construir correctamente estos atributos, hay que corregir lo que está hecho actualmente

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        Trie<String> carreras = new Trie<String>();
        int i = 0;
        while (i < infoMaterias.length){
            InfoMateria infoMateriaActual = infoMaterias[i];
            ParCarreraMateria[] listaParesCarreraMateria = infoMateriaActual.getParesCarreraMateria();
            int j = 0;
            while (j < listaParesCarreraMateria.length) {
                ParCarreraMateria parActual = listaParesCarreraMateria[j];
                String carrera = parActual.getCarrera();
                if (!carreras.esta(carrera)){ // Chequear si estaba, me afecta a la complejidad?
                    carreras.definir(carrera, valor); // Falta ver que valor le quiero meter. Debería ser un trie con las materias de esa carrera.
                }
                j++;
            }
            i++;
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
