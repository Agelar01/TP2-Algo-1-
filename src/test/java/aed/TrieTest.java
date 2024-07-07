package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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

      String [] claves = new String[] {"boc", "boca", "bocanadas", "bocas", "meji" };
      assertArrayEquals(claves, dicc.inorder());

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

   @Test
   public void test3() {
      Trie<String> dicc = new Trie<String>();
      dicc.definir("hola", "chau");
      dicc.definir("soy", "unTrie");

      dicc.definir("boca", "labios");
      dicc.definir("bocas", "beso");
      dicc.definir("bono", "U2");

      assertEquals("labios", dicc.obtener("boca"));
      assertEquals("beso", dicc.obtener("bocas"));

      dicc.eliminar("bocas");

      assertTrue(dicc.esta("hola"));
      assertTrue(dicc.esta("soy"));

      assertFalse(dicc.esta("bocas"));

      assertTrue(dicc.esta("bono"));
      assertEquals("U2", dicc.obtener("bono"));

      assertTrue(dicc.esta("boca"));
      assertEquals("labios", dicc.obtener("boca"));

   }

   @Test
   public void test4() {
      Trie<String> dicc = new Trie<String>();
      
      dicc.definir("boca", "labios");
      dicc.definir("bocas", "beso");
      dicc.definir("bono", "U2"); //raiz tiene un solo hijo

      assertEquals("labios", dicc.obtener("boca"));
      assertEquals("beso", dicc.obtener("bocas"));

      dicc.eliminar("boca");

      assertFalse(dicc.esta("boca"));
      
      assertTrue(dicc.esta("bono"));
      assertEquals("U2", dicc.obtener("bono"));

      assertTrue(dicc.esta("bocas"));
      assertEquals("beso", dicc.obtener("bocas"));

   }

}
