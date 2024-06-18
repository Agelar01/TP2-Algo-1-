package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class TrieTest<T> {

    @Test // Podría (y debería) separar los casos de test en acciones por separado y no todas juntas.
    public void test(){
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
        //assertEquals("asd33", dicc.obtener("bocanada"));
        assertEquals("asd4", dicc.obtener("boc"));
        assertEquals("asd5", dicc.obtener("meji"));
        assertEquals(dicc.tamaño(), 5);
        assertEquals(false, dicc.esta("bocanada"));
        assertEquals(true, dicc.esta("bocanadas"));
        assertEquals(false, dicc.esta("boquita"));
    }



}