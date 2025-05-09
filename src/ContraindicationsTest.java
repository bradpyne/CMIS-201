import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContraindicationsTest {
    @Test
    void makeInteraction() {
        Contraindications conflicts = new Contraindications();

        Contraindications.Interaction interaction = conflicts.makeInteraction("B,A");

        assertEquals("B", interaction.med1);
        assertEquals("A", interaction.med2);
    }

    @Test
    void hashString() {
        Contraindications conflicts = new Contraindications();

        String test = conflicts.hashString("A", "B");
        String test2 = conflicts.hashString("B", "A");

        assertEquals("a#b", test);
        assertEquals(test, test2);

        Contraindications.Interaction interaction = conflicts.makeInteraction("A,B");
        String test3 = conflicts.hashString( interaction );
        assertEquals("a#b", test3);
        assertEquals(test3, test2);
    }

    @Test
    void hashCodeTest() {
        Contraindications conflicts = new Contraindications();

        int test = conflicts.hashCode("AAAA");
        int test2 = conflicts.hashCode("ABABABBA");
        int test3 = conflicts.hashCode("ABBAABBAABBA");
        int test4 = conflicts.hashCode("aaaa");

        assertEquals(test2, test);
        assertEquals(test2, test3);
        assertEquals(test, test4);
    }

    @Test
    void storeInteractions() {
        Contraindications conflicts = new Contraindications();

        conflicts.storeInteraction( conflicts.makeInteraction("BBB,AAAA") );

    }

    @Test
    void findInteraction() {
        Contraindications conflicts = new Contraindications();
        conflicts.storeInteraction( conflicts.makeInteraction("B,A") );

        assertTrue(conflicts.findInteraction(conflicts.hashString("B","A") ) );
        assertTrue(conflicts.findInteraction(conflicts.hashString("a","b") ) );
    }

}
