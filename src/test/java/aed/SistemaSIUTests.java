package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class SistemaSIUTests {
    String[] estudiantes;
    InfoMateria[] infoMaterias;

    @BeforeEach
    void init() {
        // Este método reinicia los valores de las variables antes de cada test
        // Pedimos disculpas a las carreras no representadas
        infoMaterias = new InfoMateria[] {
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Intro a la Programación"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos1")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Algoritmos"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos2")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Técnicas de Diseño de Algoritmos"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos3")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Análisis I"), new ParCarreraMateria("Ciencias de Datos", "Análisis I"), new ParCarreraMateria("Ciencias Físicas", "Matemática 1"), new ParCarreraMateria("Ciencias Químicas", "Análisis Matemático I"), new ParCarreraMateria("Ciencias Matemáticas", "Análisis I") }),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias Biológicas", "Química General e Inorgánica 1"), new ParCarreraMateria("Ciencias Químicas", "Química General")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias Matemáticas", "Análisis II"), new ParCarreraMateria("Ciencias de Datos", "Análisis II"), new ParCarreraMateria("Ciencias Físicas", "Matemática 3"), new ParCarreraMateria("Ciencias Químicas", "Análisis Matemático II")})
        };
        estudiantes = new String[] {"123/23", "321/24", "122/99", "314/81", "391/18", "478/19", "942/20", "291/18", "382/19", "892/22", "658/13", "217/12", "371/11", "294/20"};
    }

    @Test
    void nuevo_sistema() {
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
    }

    void realizar_inscripciones(SistemaSIU sistema){
        sistema.inscribir(estudiantes[0], "Ciencias de Datos", "Algoritmos1");
        sistema.inscribir(estudiantes[0], "Ciencias de Datos", "Análisis I");
        sistema.inscribir(estudiantes[1], "Ciencias de la Computación", "Intro a la Programación");
        sistema.inscribir(estudiantes[1], "Ciencias de la Computación", "Análisis I");
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
        sistema.inscribir(estudiantes[9], "Ciencias Biológicas", "Química General e Inorgánica 1");
        sistema.inscribir(estudiantes[9], "Ciencias de la Computación", "Algoritmos");
        sistema.inscribir(estudiantes[10], "Ciencias de Datos", "Algoritmos2");
        sistema.inscribir(estudiantes[11], "Ciencias de Datos", "Algoritmos3");
        sistema.inscribir(estudiantes[11], "Ciencias Matemáticas", "Análisis II");
    }

    @Test
    void inscribir_estudiantes(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);
    }

    @Test
    void verificar_inscriptos(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);

        assertEquals(3, sistema.inscriptos("Algoritmos1", "Ciencias de Datos"));
        assertEquals(3, sistema.inscriptos("Intro a la Programación", "Ciencias de la Computación"));
        assertEquals(5, sistema.inscriptos("Análisis I", "Ciencias de la Computación"));
        assertEquals(5, sistema.inscriptos("Análisis I", "Ciencias de Datos"));
        assertEquals(5, sistema.inscriptos("Matemática 1", "Ciencias Físicas"));
        assertEquals(5, sistema.inscriptos("Análisis Matemático I", "Ciencias Químicas"));
        assertEquals(5, sistema.inscriptos("Análisis I", "Ciencias Matemáticas"));
        assertEquals(5, sistema.inscriptos("Química General e Inorgánica 1", "Ciencias Biológicas"));
        assertEquals(5, sistema.inscriptos("Química General", "Ciencias Químicas"));
        assertEquals(2, sistema.inscriptos("Algoritmos2", "Ciencias de Datos"));
        assertEquals(2, sistema.inscriptos("Algoritmos", "Ciencias de la Computación"));
        assertEquals(3, sistema.inscriptos("Técnicas de Diseño de Algoritmos", "Ciencias de la Computación"));
        assertEquals(3, sistema.inscriptos("Algoritmos3", "Ciencias de Datos"));
        assertEquals(4, sistema.inscriptos("Análisis II", "Ciencias Matemáticas"));
        assertEquals(4, sistema.inscriptos("Análisis Matemático II", "Ciencias Químicas"));
        assertEquals(4, sistema.inscriptos("Análisis II", "Ciencias de Datos"));
        assertEquals(4, sistema.inscriptos("Matemática 3", "Ciencias Físicas"));
    }

    void cargar_docentes(SistemaSIU sistema, int ay2, int ay1, int jtps, int profes){
        for (int i = 0; i < ay2; i++){
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY2, "Ciencias de Datos", "Algoritmos1");
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY2, "Ciencias Químicas", "Análisis Matemático I");
            if (i % 2 == 0){
                sistema.agregarDocente(SistemaSIU.CargoDocente.AY2, "Ciencias de la Computación", "Algoritmos");
                sistema.agregarDocente(SistemaSIU.CargoDocente.AY2, "Ciencias de Datos", "Algoritmos3");
            }
        }

        for (int i = 0; i < ay1; i++){
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Ciencias de Datos", "Algoritmos2");
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Ciencias Físicas", "Matemática 1");
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Ciencias de la Computación", "Técnicas de Diseño de Algoritmos");
            if (i % 2 == 0){
                sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Ciencias de la Computación", "Intro a la Programación");
            }
        }

        for (int i = 0; i < jtps; i++){
            sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Ciencias de Datos", "Algoritmos3");
            sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Ciencias Matemáticas", "Análisis I");
            if (i % 2 == 0){
                sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Ciencias de la Computación", "Técnicas de Diseño de Algoritmos");
                sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Ciencias de la Computación", "Algoritmos");
                sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Ciencias de la Computación", "Intro a la Programación");
            }
        }

        for (int i = 0; i < profes; i++){
            sistema.agregarDocente(SistemaSIU.CargoDocente.PROF, "Ciencias de la Computación", "Análisis I");
            sistema.agregarDocente(SistemaSIU.CargoDocente.PROF, "Ciencias de la Computación", "Intro a la Programación");
            if (i % 2 == 0){
                sistema.agregarDocente(SistemaSIU.CargoDocente.PROF, "Ciencias Químicas", "Química General");
                sistema.agregarDocente(SistemaSIU.CargoDocente.PROF, "Ciencias de Datos", "Algoritmos2");
            }
        }
    }

    @Test
    void agregar_docentes(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        int ay2 = 20, ay1 = 10, jtps = 5, profes = 2;
        cargar_docentes(sistema, ay2, ay1, jtps, profes);

        int[] plantelAlgo = sistema.plantelDocente("Algoritmos1", "Ciencias de Datos");
        assertArrayEquals(plantelAlgo, new int[] {2, 3, 5, 20});
        int[] plantelIP = sistema.plantelDocente("Intro a la Programación", "Ciencias de la Computación");
        assertArrayEquals(plantelIP, plantelAlgo);
        int[] plantelAlgo2 = sistema.plantelDocente("Algoritmos2", "Ciencias de Datos");
        assertArrayEquals(plantelAlgo2, new int[] {1, 3, 10, 10});
        plantelAlgo = sistema.plantelDocente("Algoritmos", "Ciencias de la Computación");
        assertArrayEquals(plantelAlgo, plantelAlgo2);
        int[] plantelTDA = sistema.plantelDocente("Técnicas de Diseño de Algoritmos", "Ciencias de la Computación");
        assertArrayEquals(plantelTDA, new int[] {0, 8, 10, 10});
        int[] plantelAlgo3 = sistema.plantelDocente("Algoritmos3", "Ciencias de Datos");
        assertArrayEquals(plantelAlgo3, plantelTDA);
        int[] plantelAnI = sistema.plantelDocente("Análisis I", "Ciencias de Datos");
        assertArrayEquals(plantelAnI, new int[] {2, 5, 10, 20});
        int[] plantelMat1 = sistema.plantelDocente("Matemática 1", "Ciencias Físicas");
        assertArrayEquals(plantelMat1, plantelAnI);
        int[] plantelAnMatI = sistema.plantelDocente("Análisis Matemático I", "Ciencias Químicas");
        assertArrayEquals(plantelAnMatI, plantelAnI);
        plantelAnI = sistema.plantelDocente("Análisis I", "Ciencias de la Computación");
        assertArrayEquals(plantelAnI, plantelAnMatI);
        int[] plantelQuimG = sistema.plantelDocente("Química General", "Ciencias Químicas");
        assertArrayEquals(plantelQuimG, new int[] {1, 0, 0, 0});
        int[] plantelQuimGIno = sistema.plantelDocente("Química General e Inorgánica 1", "Ciencias Biológicas");
        assertArrayEquals(plantelQuimGIno, plantelQuimG);
        int[] plantelAnMatII = sistema.plantelDocente("Análisis Matemático II", "Ciencias Químicas");
        assertArrayEquals(plantelAnMatII, new int[] {0, 0, 0, 0});
    }

    @Test
    void nadie_excede_cupo(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        assertFalse(sistema.excedeCupo("Algoritmos1", "Ciencias de Datos"));
        assertFalse(sistema.excedeCupo("Intro a la Programación", "Ciencias de la Computación"));
        assertFalse(sistema.excedeCupo("Análisis I", "Ciencias de la Computación"));
        assertFalse(sistema.excedeCupo("Análisis I", "Ciencias de Datos"));
        assertFalse(sistema.excedeCupo("Matemática 1", "Ciencias Físicas"));
        assertFalse(sistema.excedeCupo("Análisis Matemático I", "Ciencias Químicas"));
        assertFalse(sistema.excedeCupo("Análisis I", "Ciencias Matemáticas"));
        assertFalse(sistema.excedeCupo("Química General e Inorgánica 1", "Ciencias Biológicas"));
        assertFalse(sistema.excedeCupo("Química General", "Ciencias Químicas"));
        assertFalse(sistema.excedeCupo("Algoritmos2", "Ciencias de Datos"));
        assertFalse(sistema.excedeCupo("Análisis II", "Ciencias de Datos"));
    }

    @Test
    void colapsar_facultad(){
        int nuevos_estudiantes = 201;
        String[] nuevos_inscriptos = new String[nuevos_estudiantes];
        for (int i = 0; i < nuevos_estudiantes; i++){
            nuevos_inscriptos[i] = String.format("%d/%d", i, i % 20 + 10);
        }
        SistemaSIU sistema = new SistemaSIU(infoMaterias, nuevos_inscriptos);
        cargar_docentes(sistema, 20, 10, 5, 2);
        for (int i = 0; i < nuevos_estudiantes - 1; i++){
            if (i < 10){
                sistema.inscribir(nuevos_inscriptos[i], "Ciencias Químicas", "Química General");
            }
            if (i < 102){
                sistema.inscribir(nuevos_inscriptos[i], "Ciencias de Datos", "Algoritmos1");
                sistema.inscribir(nuevos_inscriptos[i], "Ciencias de Datos", "Algoritmos3");
            }
            sistema.inscribir(nuevos_inscriptos[i], "Ciencias de la Computación", "Algoritmos");
        }
        assertEquals(10, sistema.inscriptos("Química General e Inorgánica 1", "Ciencias Biológicas"));
        assertEquals(102, sistema.inscriptos("Algoritmos1", "Ciencias de Datos"));
        assertEquals(102, sistema.inscriptos("Técnicas de Diseño de Algoritmos", "Ciencias de la Computación"));
        assertEquals(200, sistema.inscriptos("Algoritmos", "Ciencias de la Computación"));
        assertEquals(0, sistema.inscriptos("Análisis I", "Ciencias de Datos"));
        
        assertTrue(sistema.excedeCupo("Intro a la Programación", "Ciencias de la Computación")); 
        // #inscriptos = 102, CUPO = 1500, plantelIP = plantelAlgo = {2 prof, 3 jtp, 5 ay1, 20 ay2} => el cupo de IP es cupo = 2*250 + 3*100 + 5*20 + 20*30 
        
        sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Ciencias de la Computación", "Intro a la Programación");
        assertFalse(sistema.excedeCupo("Algoritmos1", "Ciencias de Datos"));
        assertFalse(sistema.excedeCupo("Algoritmos2", "Ciencias de Datos"));
        sistema.inscribir(nuevos_inscriptos[200], "Ciencias de la Computación", "Algoritmos");
        
        assertTrue(sistema.excedeCupo("Algoritmos2", "Ciencias de Datos")); 
        // #inscriptos = 200, CUPO = 1050, plantelAlgo2 = {1, 3, 10, 10}, CUPO = 1*250 + 3*100 + 10*20 + 10*30
        
        assertTrue(sistema.excedeCupo("Técnicas de Diseño de Algoritmos", "Ciencias de la Computación")); 
        // #inscriptos = 102, CUPO = 1300, plantelTDA = {0, 8, 10, 10}, CUPO = 8*100 + 10*20 + 10*30, 
        
        assertTrue(sistema.excedeCupo("Química General e Inorgánica 1", "Ciencias Biológicas")); 
        // #inscriptos = 10, , CUPO = 250, plantelQuimG = {1, 0, 0, 0}
        
        assertFalse(sistema.excedeCupo("Análisis II", "Ciencias Matemáticas"));
    }

    @Test
    void listado_carreras(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        String[] carreras = new String[] {"Ciencias Biológicas", "Ciencias Físicas", "Ciencias Matemáticas", "Ciencias Químicas", "Ciencias de Datos", "Ciencias de la Computación"};
        assertArrayEquals(carreras, sistema.carreras());
    }

    @Test
    void listado_materias(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        String[] materiasBio = new String[] {"Química General e Inorgánica 1"};
        assertArrayEquals(materiasBio, sistema.materias("Ciencias Biológicas"));
        String[] materiasCompu = new String[] {"Algoritmos", "Análisis I", "Intro a la Programación", "Técnicas de Diseño de Algoritmos"};
        assertArrayEquals(materiasCompu, sistema.materias("Ciencias de la Computación"));
        String[] materiasDatos = new String[] {"Algoritmos1", "Algoritmos2", "Algoritmos3", "Análisis I", "Análisis II"};
        assertArrayEquals(materiasDatos, sistema.materias("Ciencias de Datos"));
        String[] materiasFisica = new String[] {"Matemática 1", "Matemática 3"};
        assertArrayEquals(materiasFisica, sistema.materias("Ciencias Físicas"));
        String[] materiasMate = new String[] {"Análisis I", "Análisis II"};
        assertArrayEquals(materiasMate, sistema.materias("Ciencias Matemáticas"));
        String[] materiasQuimica = new String[] {"Análisis Matemático I", "Análisis Matemático II", "Química General"};
        assertArrayEquals(materiasQuimica, sistema.materias("Ciencias Químicas"));
    }

    @Test
    void materias_inscriptas(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);

        assertEquals(2, sistema.materiasInscriptas(estudiantes[0]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[1]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[2]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[3]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[4]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[5]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[6]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[7]));
        assertEquals(3, sistema.materiasInscriptas(estudiantes[8]));
        assertEquals(3, sistema.materiasInscriptas(estudiantes[9]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[10]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[11]));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[12]));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[13]));
    }

    @Test
    void cerrar_materia(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);

        sistema.cerrarMateria("Algoritmos1", "Ciencias de Datos");
        assertEquals(1, sistema.materiasInscriptas(estudiantes[0]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[1]));
        String[] materiasCompu = new String[] {"Algoritmos", "Análisis I", "Técnicas de Diseño de Algoritmos"};
        assertArrayEquals(materiasCompu, sistema.materias("Ciencias de la Computación"));
        String[] materiasDatos = new String[] {"Algoritmos2", "Algoritmos3", "Análisis I", "Análisis II"};
        assertArrayEquals(materiasDatos, sistema.materias("Ciencias de Datos"));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[10]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[11]));
    }

    @Test
    void cerrar_facultad(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);
        cargar_docentes(sistema, 20, 10, 5, 2);

        sistema.cerrarMateria("Intro a la Programación", "Ciencias de la Computación");
        sistema.cerrarMateria("Análisis Matemático I", "Ciencias Químicas");
        String[] materiasCompu = new String[] {"Algoritmos", "Técnicas de Diseño de Algoritmos"};
        assertArrayEquals(materiasCompu, sistema.materias("Ciencias de la Computación"));
        String[] materiasMate = new String[] {"Análisis II"};
        assertArrayEquals(materiasMate, sistema.materias("Ciencias Matemáticas"));
        String[] materiasQuimica = new String[] {"Análisis Matemático II", "Química General"};
        assertArrayEquals(materiasQuimica, sistema.materias("Ciencias Químicas"));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[0]));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[1]));

        sistema.cerrarMateria("Matemática 3", "Ciencias Físicas");
        String[] sinMaterias = new String[] {};
        assertArrayEquals(sinMaterias, sistema.materias("Ciencias Físicas"));
        assertArrayEquals(sinMaterias, sistema.materias("Ciencias Matemáticas"));
        String[] materiasBio = new String[] {"Química General e Inorgánica 1"};
        assertArrayEquals(materiasBio, sistema.materias("Ciencias Biológicas"));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[8]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[11]));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[5]));

        sistema.cerrarMateria("Química General e Inorgánica 1", "Ciencias Biológicas");
        assertArrayEquals(sinMaterias, sistema.materias("Ciencias Biológicas"));
        assertArrayEquals(sinMaterias, sistema.materias("Ciencias Químicas"));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[9]));

        sistema.cerrarMateria("Algoritmos", "Ciencias de la Computación");
        assertEquals(1, sistema.materiasInscriptas(estudiantes[7]));
        sistema.cerrarMateria("Algoritmos3", "Ciencias de Datos");
        assertArrayEquals(sinMaterias, sistema.materias("Ciencias de la Computación"));
        assertArrayEquals(sinMaterias, sistema.materias("Ciencias de Datos"));

        for(int i = 0; i < estudiantes.length; i++){
            assertEquals(0, sistema.materiasInscriptas(estudiantes[i]));
        }
        String[] carreras = new String[] {"Ciencias Biológicas", "Ciencias Físicas", "Ciencias Matemáticas", "Ciencias Químicas", "Ciencias de Datos", "Ciencias de la Computación"};
        assertArrayEquals(carreras, sistema.carreras());
    }
}
