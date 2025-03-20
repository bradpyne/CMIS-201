import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @org.junit.jupiter.api.Test
    void getPatientIdentity() {
        try {
            PatientIdentity patID1 = new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10"));
            PatientIdentity patID2 = new PatientIdentity(new Name("Johnny", "Bravo"), dateFormatter.parse("1985-05-15"));

            Patient pat1 = new Patient( patID1 );
            Patient pat2 = new Patient( patID2 );

            assertEquals( patID1, pat1.getPatientIdentity() );
            assertEquals( patID2, pat2.getPatientIdentity() );

        } catch ( ParseException e ) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getPrescriptionList() {
        try {
            Patient pat1 = new Patient( new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10")));
            Prescription pre1 = new Prescription("ibuprofen", dateFormatter.parse("2020-03-01"), 1, "Dr. John Smith");

            pat1.getPrescriptionList().add( pre1 );
            pat1.getPrescriptionList().initIteration();

            assertEquals( pre1, pat1.getPrescriptionList().next() );

        } catch ( ParseException e ) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void toCSV() {
        try {
            Patient pat1 = new Patient( new PatientIdentity(new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10")));

            assertEquals( "Floyd, Ernie, 10-10-1999", pat1.toCSV() );

        } catch ( ParseException e ) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void makePatient() {
        try {
            Patient pat1 = new Patient( new PatientIdentity(new Name("Ben", "Hur"), dateFormatter.parse("1959-11-18")));
            Patient pat2 = Patient.makePatient("Hur,Ben,11-18-1959");

            assertTrue( pat1.getPatientIdentity().match( pat2.getPatientIdentity() ) );

        } catch ( ParseException e ) {
            throw new RuntimeException(e);
        }
    }
}