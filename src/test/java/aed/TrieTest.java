package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TrieTest<T> {
   public TrieTest() {
   }

   @Test
   public void test() {
      Trie<String> dicc = new Trie<String>();
      dicc.definir("boca", "asd");
      dicc.definir("bocas", "asd22");
      dicc.definir("bocanada", "asd33");
      dicc.definir("boc", "asd4");
      dicc.definir("meji", "asd5");
      dicc.definir("bocanadas", "asd6");
      dicc.eliminar("bocanada");
      assertEquals("asd", dicc.obtener("boca"));
      assertEquals("asd22", dicc.obtener("bocas"));
      assertEquals("asd4", dicc.obtener("boc"));
      assertEquals("asd5", dicc.obtener("meji"));
      assertEquals(dicc.tamaño(), 5);
      assertEquals(false, dicc.esta("bocanada"));
      assertEquals(true, dicc.esta("bocanadas"));
      assertEquals(false, dicc.esta("boquita"));
   }

   @Test
   public void test2() {
      Trie<Materia> carrera = new Trie<Materia>();
      carrera.definir("Ciencias de la computación", new Materia(new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Intro a la Programación"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos1")})));
      assertEquals(0, carrera.obtener("Ciencias de la computación").cantidadAlumnosInscriptos());
      assertEquals(true, carrera.esta("Ciencias de la computación"));

      carrera.eliminar("Ciencias de la computación");
      assertEquals(false, carrera.esta("Ciencias de la computación"));
   }
   
   /* 
   @Test
   public void test3() {
      Trie<Trie<Materia>> carrera = new Trie<Trie<Materia>>();
      carrera.definir("compu", new Trie<Materia>()).definir("Ciencias de la computación", new Materia(new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Intro a la Programación"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos1")})));
      assertEquals(0, carrera.obtener("Ciencias de la computación").cantidadAlumnosInscriptos());
      assertEquals(true, carrera.esta("Ciencias de la computación"));

      carrera.eliminar("Ciencias de la computación");
      assertEquals(false, carrera.esta("Ciencias de la computación"));
   }
   */
}
