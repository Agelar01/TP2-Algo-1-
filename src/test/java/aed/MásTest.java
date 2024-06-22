package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class MásTest {
    String[] estudiantes;
    InfoMateria[] infoMaterias;

    @BeforeEach
    void init() {
        // Este método reinicia los valores de las variables antes de cada test
        // Pedimos disculpas a las carreras no representadas
        infoMaterias = new InfoMateria[] {
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Intro a la Programación"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos1")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Algoritmos"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos2")}),
            /*new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Técnicas de Diseño de Algoritmos"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos3")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Análisis I"), new ParCarreraMateria("Ciencias de Datos", "Análisis I"), new ParCarreraMateria("Ciencias Físicas", "Matemática 1"), new ParCarreraMateria("Ciencias Químicas", "Análisis Matemático I"), new ParCarreraMateria("Ciencias Matemáticas", "Análisis I") }),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias Biológicas", "Química General e Inorgánica 1"), new ParCarreraMateria("Ciencias Químicas", "Química General")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias Matemáticas", "Análisis II"), new ParCarreraMateria("Ciencias de Datos", "Análisis II"), new ParCarreraMateria("Ciencias Físicas", "Matemática 3"), new ParCarreraMateria("Ciencias Químicas", "Análisis Matemático II")})*/
        };
        estudiantes = new String[] {"123/23", "321/24", "122/99", "314/81", "391/18", "478/19", "942/20", "291/18", "382/19", "892/22", "658/13", "217/12", "371/11", "294/20"};
    }
    
    void realizar_inscripciones(SistemaSIU sistema){
        sistema.inscribir(estudiantes[0], "Ciencias de Datos", "Algoritmos1");
        //sistema.inscribir(estudiantes[0], "Ciencias de Datos", "Análisis I");
        sistema.inscribir(estudiantes[1], "Ciencias de la Computación", "Intro a la Programación");
        /*sistema.inscribir(estudiantes[1], "Ciencias de la Computación", "Análisis I");
        sistema.inscribir(estudiantes[2], "Ciencias Biológicas", "Química General e Inorgánica 1");
        sistema.inscribir(estudiantes[2], "Ciencias de la Computación", "Técnicas de Diseño de Algoritmos");
        sistema.inscribir(estudiantes[3], "Ciencias Físicas", "Matemática 1");
        sistema.inscribir(estudiantes[4], "Ciencias Químicas", "Análisis Matemático I");
        sistema.inscribir(estudiantes[4], "Ciencias Químicas", "Química General");
        sistema.inscribir(estudiantes[5], "Ciencias Matemáticas", "Análisis I");
        sistema.inscribir(estudiantes[6], "Ciencias de Datos", "Análisis II");
        sistema.inscribir(estudiantes[6], "Ciencias Químicas", "Química General");
        sistema.inscribir(estudiantes[7], "Ciencias de la Computación", "Técnicas de Diseño de Algoritmos");
        sistema.inscribir(estudiantes[8], "Ciencias Físicas", "Matemática 3");
        sistema.inscribir(estudiantes[8], "Ciencias Biológicas", "Química General e Inorgánica 1");
        sistema.inscribir(estudiantes[8], "Ciencias de la Computación", "Intro a la Programación");
        sistema.inscribir(estudiantes[9], "Ciencias Químicas", "Análisis Matemático II");
        sistema.inscribir(estudiantes[9], "Ciencias Biológicas", "Química General e Inorgánica 1");*/
        sistema.inscribir(estudiantes[9], "Ciencias de la Computación", "Algoritmos");
        sistema.inscribir(estudiantes[10], "Ciencias de Datos", "Algoritmos2");
        /*sistema.inscribir(estudiantes[11], "Ciencias de Datos", "Algoritmos3");
        sistema.inscribir(estudiantes[11], "Ciencias Matemáticas", "Análisis II");*/
    }

    @Test
    void verificar_inscriptos(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);
    
        assertEquals(2, sistema.inscriptos("Algoritmos1", "Ciencias de Datos"));
        assertEquals(2, sistema.inscriptos("Algoritmos2", "Ciencias de Datos"));
        assertEquals(2, sistema.inscriptos("Algoritmos", "Ciencias de la Computación"));
    }

    

}
