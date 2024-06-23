package aed;

import java.util.ArrayList;

public class SistemaSIU {

    private Trie<Trie<Materia>> facultad; // Trie cuyas claves son strings con los nombres de las carreras. los valores son Tries cuyas claves son strings con el nombre de las materias y sus valores son de clase Materia.
    private Trie<Alumno> alumnos; // Trie cuyas claves son strings de las libretas de todos los alumnos de la facultad. sus valores son las cantidades de materias en las que están inscriptos los alumnos. 
    private ArrayList<String> carreras; // lista para guardar las carreras. lo podemos reemplazar por el de abajo
    private InfoMateria[] infoMaterias; // 

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        
        // inicializo los atributos privados.
        Trie<Alumno> alumnos = new Trie<Alumno>();
        Trie<Trie<Materia>> facultad = new Trie<Trie<Materia>>();
        ArrayList<String> carreras = new ArrayList<String>();

        //este for agrega las libretas de todos los alumnos al Trie alumnos, y no asigna ninguna inscripción a materias
        for(int i=0; i < libretasUniversitarias.length; i++) {  
            Alumno alumno = new Alumno(libretasUniversitarias[i]);
            alumnos.definir(libretasUniversitarias[i], alumno); 
        }

/////////////////////// 

        //este for agrega las carreras a la lista de carreras.
                    // OBS infoMaterias.length me dice cuántas materias distintas hay (o sea sin contar las repetidas)
        for (int i=0; i < infoMaterias.length; i++) {         
            for (int j = 0; j < infoMaterias[i].getParesCarreraMateria().length; j++){ 
                
                if (j == 0 && i == 0){
                    carreras.add(infoMaterias[i].getParesCarreraMateria()[j].getCarrera());
                } // OBS si no ponía este if se quejaba en el segundo cuando el array estaba vacío y le preguntaba si contains
                    
                // si la carrera que dice ahí no está en mi lista de carreras, la agrego.
                if (!carreras.contains(infoMaterias[i].getParesCarreraMateria()[j].getCarrera())) { 
                    carreras.add(infoMaterias[i].getParesCarreraMateria()[j].getCarrera());
                }
            }
        }
            
////////////////////// 

        //este for define como valor de la clave "nombreDeCarrera" (carreras.get[i]) a un nuevo Trie<Materia>
        for (int i = 0; i < carreras.size(); i++) {
                Trie<Materia> carrera = new Trie<Materia>();
                facultad.definir(carreras.get(i), carrera);
        }

    //hasta ahora tenemos la raíz del SistemaSIU que tiene como atributos a un Trie<Alumnos> (cuyas claves son libretas y sus valores ints) 
    //y a un Trie<Materia> (cuyas claves son los nombres de las materias y sus valores, por ahora vacíos, son de la clase Materia).
    //ahora falta definir las materias de cada carrera
        for (int i = 0; i < infoMaterias.length; i++) { // OBS infoMaterias.length me dice cuántas materias distintas hay (o sea sin contar las repetidas)
            
            Materia materia = new Materia(infoMaterias[i]); // creo las materias que se dictan en la facu. después voy a poder cerrarlas, pero no agregar materias nuevas
            
            for (int j = 0; j < infoMaterias[i].getParesCarreraMateria().length; j++) { // OBS infoMaterias[i].getParesuCarreraMateria().length me dice en cuántas carreras está la materia (o cuántos nombres tiene la materia)
                Trie<Materia> carrera = facultad.obtener(infoMaterias[i].getParesCarreraMateria()[j].getCarrera()); 
                materia.agregarPadre(carrera); // agrego un puntero al Trie con la carrera en la que está la materia 
                carrera.definir(infoMaterias[i].getParesCarreraMateria()[j].getNombreMateria(), materia); 
                 //facultad.obtener(nombre de carrera) es de tipo Trie<Materia>
            }
        }
        this.alumnos = alumnos;
        this.carreras = carreras;
        this.facultad = facultad;
        this.infoMaterias = infoMaterias;
    }

    public void inscribir(String estudiante, String carrera, String materia){
        facultad.obtener(carrera).obtener(materia).inscribirAlumno(estudiante);
        alumnos.obtener(estudiante).sumarUnaInscripcion();
    }

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
    }

    public int[] plantelDocente(String materia, String carrera){
        return facultad.obtener(carrera).obtener(materia).plantelDocente();	    
    }

    public void cerrarMateria(String materia, String carrera){
        String[] listaDeCarreras = facultad.obtener(carrera).obtener(materia).listaCarreras();
        String[] listaDeMaterias = facultad.obtener(carrera).obtener(materia).listaNombresMateria();

        int i = 0;
        int j= 0;
        while(i < listaDeCarreras.length){
        j = 0;
            while (j < listaDeMaterias.length){
                if (facultad.obtener(listaDeCarreras[i]).esta(listaDeMaterias[j])){
                facultad.obtener(listaDeCarreras[i]).eliminar(listaDeMaterias[j]);
                    i++;
                }
                j++;
            }
        i++;

        }
    }

    public int inscriptos(String materia, String carrera){
        return facultad.obtener(carrera).obtener(materia).alumnosInscriptos().size();	    
    }

    public boolean excedeCupo(String materia, String carrera){
        return facultad.obtener(carrera).obtener(materia).alumnosInscriptos().size() > facultad.obtener(carrera).obtener(materia).cupo();	    
    }

    public String[] carreras(){ 
    
        String[] listaCarreras = new String[this.carreras.size()];
        
        carreras.sort(null);

        for (int i = 0; i< carreras.size(); i++) {
            listaCarreras[i] = carreras.get(i);
        }
        
        return listaCarreras;
        
    }

    public String[] materias(String carrera){
        ArrayList<String> lista = new ArrayList<String>(); 
        for (int i = 0; i < this.infoMaterias.length; i++) {
            for (int j = 0; j < infoMaterias[i].getParesCarreraMateria().length; j++){
                if (infoMaterias[i].getParesCarreraMateria()[j].getCarrera() == carrera) {
                    lista.add(infoMaterias[i].getParesCarreraMateria()[j].getNombreMateria());
                }
            }
        }
        
        lista.sort(null);

        String[] listaMaterias = new String[lista.size()];
        
        for (int i = 0; i < lista.size(); i++) {
            listaMaterias[i] = lista.get(i);
        }

        return listaMaterias;
    
    }

    public int materiasInscriptas(String estudiante){
        return alumnos.obtener(estudiante).cantMaterias();	    
    }
}
