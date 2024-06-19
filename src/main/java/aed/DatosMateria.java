package aed;

public class DatosMateria{
    int[] datosProfesores;
    String[] datosAlumnos; // creo que no se le puede cambiar el tamaño a esto (inscribir o desinscribir) 
    String[] nombresDeMateria;

    public DatosMateria(int[] datosProfesores, String[] datosAlumnos, String[] nombresDeMateria){
        this.datosProfesores = datosProfesores;
        this.datosAlumnos = datosAlumnos;
        this.nombresDeMateria = nombresDeMateria;
    }
}