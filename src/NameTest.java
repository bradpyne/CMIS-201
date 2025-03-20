import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @org.junit.jupiter.api.Test
    void fullName() {
        Name name1 = new Name("Ernie", "Floyd");

        assertEquals( "Floyd, Ernie", name1.fullName() );
    }

    @org.junit.jupiter.api.Test
    void match() {
        Name name1 = new Name("Johnny", "Bravo");
        Name name2 = new Name("JoHnNy", "BRaVO");
        Name name3 = new Name("other", "name");

        assertTrue( name1.match( name2 ) );
        assertFalse( name1.match( name3 ) );
    }

    @org.junit.jupiter.api.Test
    void isLessThan() {
        Name name1 = new Name("Johnny", "Bravo");
        Name name2 = new Name("JoHNy", "BRaVO");
        Name name3 = new Name("other", "name");

        assertFalse( name1.isLessThan( name2 ) );
        assertTrue( name2.isLessThan( name3 ) );
    }
}