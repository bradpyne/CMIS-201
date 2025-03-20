import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class PatientListTest {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @org.junit.jupiter.api.Test
    void add() {
        PatientList patList = new PatientList();
        try {

            assertTrue( patList.add( new Patient( new PatientIdentity( new Name("Johnny", "Bravo"), dateFormatter.parse("1985-05-15") ) ) ) );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void find() {
        PatientList patList = new PatientList();
        try {
            Patient pat1 = new Patient(new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10")));
            Patient pat2 = new Patient(new PatientIdentity(new Name("Johnny", "Bravo"), dateFormatter.parse("1985-05-15")));
            Patient pat3 = new Patient(new PatientIdentity(new Name("JoHnNy", "BRaVO"), dateFormatter.parse("1972-05-06")));

            patList.add( pat1 );
            patList.add( pat2 );
            patList.add( pat3 );

            assertEquals( pat1, patList.find( new PatientIdentity( new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10") ) ) );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void initIteration() {
        PatientList patList = new PatientList();
        try {
            Patient pat1 = new Patient( new PatientIdentity( new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10") ) );

            patList.add( pat1 );

            patList.initIteration();

            assertEquals( pat1, patList.next() );
        } catch( ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @org.junit.jupiter.api.Test
    void next() {
        PatientList patList = new PatientList();
        try {
            Patient pat1 = new Patient(new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10")));
            Patient pat2 = new Patient(new PatientIdentity(new Name("Johnny", "Bravo"), dateFormatter.parse("1985-05-15")));
            Patient pat3 = new Patient(new PatientIdentity(new Name("JoHnNy", "BRaVO"), dateFormatter.parse("1972-05-06")));

            patList.add( pat1 );
            patList.add( pat2 );
            patList.add( pat3 );

            assertEquals( pat1, patList.find( new PatientIdentity( new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10") ) ) );

            patList.initIteration();
            Patient nextPat = patList.next();
            Patient nextPat2 = patList.next();

            assertEquals( pat3 , nextPat );
            assertEquals( pat2 , nextPat2 );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void saveToFile() {
        PatientList patList = new PatientList();
        try {
            Patient pat1 = new Patient(new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10")));
            Patient pat2 = new Patient(new PatientIdentity(new Name("Johnny", "Bravo"), dateFormatter.parse("1985-05-15")));
            Patient pat3 = new Patient(new PatientIdentity(new Name("JoHnNy", "BRaVO"), dateFormatter.parse("1972-05-06")));

            patList.add( pat1 );
            patList.add( pat2 );
            patList.add( pat3 );

            assertTrue( patList.saveToFile("test.csv") );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void importFromFile() {
        PatientList patList = new PatientList();

        assertTrue( patList.importFromFile("test2.csv") );
    }

    @Test
    void importPrescriptions() {
        PatientList patList = new PatientList();


    }
}