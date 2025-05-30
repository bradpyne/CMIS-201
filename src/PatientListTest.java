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

            Patient patNext = patList.next();
            assertEquals( pat1, patNext );
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

            assertTrue( pat1.getPatientIdentity().match(patList.find( new PatientIdentity( new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10") ) ).getPatientIdentity() ));

            patList.initIteration();
            Patient nextPat = patList.next();
            Patient nextPat2 = patList.next();

            assertEquals( pat3 , nextPat );
            assertEquals( pat2 , nextPat2 );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void mergeSort() {
        Patient[] pats = new Patient[ 16 ];
        PatientList patList = new PatientList();
        try {
            String[] lastNames = {"D", "A", "C", "B"};
            String[] firstNames = {"D", "A", "C", "B"};
            int index = 0;
            for( int i = 0; i < lastNames.length; i++ ) {
                for( int j = 0; j < firstNames.length; j++ ) {
                    pats[index] = ( new Patient( new PatientIdentity( new Name( firstNames[i], lastNames[j] ), dateFormatter.parse("2000-01-01"))));
                    index++;
                }
            }
            Patient[] sortedPats = patList.mergeSort( pats );
            assertEquals( "A, A", sortedPats[0].getPatientIdentity().getName().fullName() );
            assertEquals( "B, A", sortedPats[4].getPatientIdentity().getName().fullName() );
            assertEquals( "C, A", sortedPats[8].getPatientIdentity().getName().fullName() );
            assertEquals( "D, A", sortedPats[12].getPatientIdentity().getName().fullName() );
            assertEquals( "D, D", sortedPats[15].getPatientIdentity().getName().fullName() );
            //patList.diag();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void merge() {

    }

    @org.junit.jupiter.api.Test
    void saveToFile() {
        PatientList patList = new PatientList();

        patList.importFromFile("patients1000.csv");
        assertTrue( patList.saveToFile("test.csv") );
    }

    @org.junit.jupiter.api.Test
    void importFromFile() {
        PatientList patList = new PatientList();

        assertTrue( patList.importFromFile("patients1000.csv") );
    }

    //uses deprecated array implementation
    @Test
    void importAndMergeSort() {
        PatientList patList = new PatientList();

        assertTrue( patList.importAndMergeSort("patients1000.csv") );
//        patList.initIteration();
//        assertEquals("Alvarez, Barbara", patList.next().getPatientIdentity().getName().fullName() );
    }


    @Test
    void importPrescriptions() {
        PatientList patList = new PatientList();

        assertTrue( patList.importFromFile("patients1000.csv") );
        assertTrue( patList.importPrescriptions("prescriptions1000.csv"));

        patList.initIteration();
        PrescriptionList prList = patList.next().getPrescriptionList();
        prList.initIteration();

        //expected result: first patient Alvarez, Barbara - most recent script fantamycin issued sept 2023
        assertEquals("fantamycin", prList.next().getScriptName() );

    }

    @Test
    void recordInteractions() {
        PatientList patList = new PatientList();
        try {
            Patient pat1 = new Patient(new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10")));
            patList.add( pat1 );

            Prescription pre1 = new Prescription("alphapone", dateFormatter.parse("2025-03-01"), 1, "Dr. John Smith");
            Prescription pre2 = new Prescription("alphastat", dateFormatter.parse("2025-02-26"), 1, "Dr. John Smith");
            Prescription pre3 = new Prescription("alphachlor", dateFormatter.parse("2025-02-26"), 1, "Dr. John Smith");
            Prescription pre4 = new Prescription("alphasome", dateFormatter.parse("2025-02-26"), 1, "Dr. John Smith");
            Prescription pre5 = new Prescription("alphatine", dateFormatter.parse("2025-02-26"), 1, "Dr. John Smith");
            Prescription pre6 = new Prescription("alphadrill", dateFormatter.parse("2024-07-18"), 1, "Dr. John Smith");

            patList.find( pat1.getPatientIdentity() ).getPrescriptionList().importInteractions( "interactions.csv" );

            patList.find( pat1.getPatientIdentity() ).getPrescriptionList().add( pre1 );

            patList.recordInteraction( patList.find(pat1.getPatientIdentity() ), pre2 );
            patList.recordInteraction( patList.find(pat1.getPatientIdentity() ), pre3 );
            patList.recordInteraction( patList.find(pat1.getPatientIdentity() ), pre4 );
            patList.recordInteraction( patList.find(pat1.getPatientIdentity() ), pre5 );
            patList.recordInteraction( patList.find(pat1.getPatientIdentity() ), pre6 );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}