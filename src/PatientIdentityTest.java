import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
class PatientIdentityTest {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @org.junit.jupiter.api.Test
    void match() {
        try {
            PatientIdentity patID1 = new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10"));
            PatientIdentity patID2 = new PatientIdentity(new Name("Johnny", "Bravo"), dateFormatter.parse("1985-05-15"));
            PatientIdentity patID3 = new PatientIdentity(new Name("JoHnNy", "BRaVO"), dateFormatter.parse("1972-05-06"));

            assertTrue( patID1.match( new PatientIdentity( new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10") ) ) );
            assertFalse( patID2.match( patID3 ) );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        try {
            PatientIdentity patID1 = new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10"));

            assertEquals( "Patient full name: Floyd, Ernie.\nPatient date of birth: 1999-10-10", patID1.toString() );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void getName() {
        try {
            Name name1 = new Name( "Ernie", "Floyd" );
            PatientIdentity patID1 = new PatientIdentity( name1, dateFormatter.parse("1999-10-10"));

            assertEquals( name1, patID1.getName() );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void getDOB() {
        try {
            PatientIdentity patID1 = new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10"));

            assertEquals( dateFormatter.parse("1999-10-10"), patID1.getDOB() );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void isLessThan() {
        try {
            PatientIdentity patID1 = new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10"));
            PatientIdentity patID2 = new PatientIdentity(new Name("Johnny", "Bravo"), dateFormatter.parse("1985-05-15"));
            PatientIdentity patID3 = new PatientIdentity(new Name("JoHnNy", "BRaVO"), dateFormatter.parse("1972-05-06"));

            assertFalse( patID1.isLessThan( patID2 ) );
            assertTrue( patID2.isLessThan( patID1 ) );
            assertTrue( patID3.isLessThan( patID2 ) );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}