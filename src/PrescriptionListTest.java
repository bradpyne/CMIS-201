import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionListTest {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void initIteration() {
        try {
            PrescriptionList preList = new PrescriptionList();
            Prescription pre1 = new Prescription("test", df.parse("2020-03-01"), 1, "Dr. John Smith");


            preList.add( pre1 );
            preList.initIteration();
            assertEquals( pre1, preList.next() );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void next() {
        try {
            PrescriptionList preList = new PrescriptionList();
            Prescription pre1 = new Prescription("6", df.parse("2020-03-01"), 1, "Dr. John Smith");
            Prescription pre2 = new Prescription("5", df.parse("2021-02-26"), 1, "Dr. John Smith");
            Prescription pre3 = new Prescription("4", df.parse("2022-02-26"), 1, "Dr. John Smith");
            Prescription pre4 = new Prescription("3", df.parse("2023-02-26"), 1, "Dr. John Smith");
            Prescription pre5 = new Prescription("2", df.parse("2024-02-26"), 1, "Dr. John Smith");
            Prescription pre6 = new Prescription("1", df.parse("2025-07-18"), 1, "Dr. John Smith");

            preList.add( pre1 );
            preList.add( pre2 );
            preList.add( pre5 );
            preList.add( pre6 );
            preList.add( pre4 );
            preList.add( pre3 );

            preList.initIteration();
            assertEquals( pre6, preList.next() );
            assertEquals( pre5, preList.next() );
            assertEquals( pre4, preList.next() );
            assertEquals( pre3, preList.next() );
            assertEquals( pre2, preList.next() );
            assertEquals( pre1, preList.next() );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void count() {
        try {
            PrescriptionList preList = new PrescriptionList();
            Prescription pre1 = new Prescription("6", df.parse("2020-03-01"), 1, "Dr. John Smith");
            Prescription pre2 = new Prescription("5", df.parse("2021-02-26"), 1, "Dr. John Smith");
            Prescription pre3 = new Prescription("4", df.parse("2022-02-26"), 1, "Dr. John Smith");
            Prescription pre4 = new Prescription("3", df.parse("2023-02-26"), 1, "Dr. John Smith");
            Prescription pre5 = new Prescription("2", df.parse("2024-02-26"), 1, "Dr. John Smith");
            Prescription pre6 = new Prescription("1", df.parse("2025-07-18"), 1, "Dr. John Smith");

            preList.add( pre1 );
            preList.add( pre2 );
            preList.add( pre5 );
            preList.add( pre6 );
            preList.add( pre4 );
            preList.add( pre3 );

            assertEquals( 6, preList.count() );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void add() {
        try {
            PrescriptionList preList = new PrescriptionList();
            Prescription pre1 = new Prescription("6", df.parse("2020-03-01"), 1, "Dr. John Smith");
            Prescription pre2 = new Prescription("5", df.parse("2021-02-26"), 1, "Dr. John Smith");
            Prescription pre3 = new Prescription("4", df.parse("2022-02-26"), 1, "Dr. John Smith");
            Prescription pre4 = new Prescription("3", df.parse("2023-02-26"), 1, "Dr. John Smith");
            Prescription pre5 = new Prescription("2", df.parse("2024-02-26"), 1, "Dr. John Smith");
            Prescription pre6 = new Prescription("1", df.parse("2025-07-18"), 1, "Dr. John Smith");

            preList.add( pre1 );
            preList.add( pre2 );
            preList.add( pre5 );
            preList.add( pre6 );
            preList.add( pre4 );
            preList.add( pre3 );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void comesBeforeTest() {
        try {
            PrescriptionList preList = new PrescriptionList();
            Prescription pre1 = new Prescription("ibuprofen", df.parse("2025-03-01"), 1, "Dr. John Smith");
            Prescription pre2 = new Prescription("benadril", df.parse("2025-02-26"), 1, "Dr. John Smith");

            assertTrue( preList.comesBeforeTest( pre1, pre2 ));
            assertFalse( preList.comesBeforeTest( pre2, pre1 ));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void importInteractions() {
        PrescriptionList plist = new PrescriptionList();
        plist.importInteractions("interactions.csv");

        assertTrue( plist.conflicts.findInteraction( plist.conflicts.hashString("eristostat","oxydrine"))) ;
        assertFalse( plist.conflicts.findInteraction( plist.conflicts.hashString("ibuprofen", "tylenol") ) );
    }

    @Test
    void checkInteraction() {
        try {
            PrescriptionList plist = new PrescriptionList();
            plist.conflicts.storeInteraction( plist.conflicts.makeInteraction( "a,b" ) );
            Prescription pre1 = new Prescription("a", df.parse("2025-03-01"), 1, "Dr. John Smith");
            Prescription pre2 = new Prescription("c", df.parse("2025-03-01"), 1, "Dr. John Smith");
            //Prescription pre3 = new Prescription("a", df.parse("2024-03-01"), 1, "Dr. John Smith");

            plist.add( Prescription.makePrescription("Lname,Fname,2000-01-01,b,2025-01-01,50,Sub") );
            assertEquals( "b",  plist.checkInteraction( pre1 ).getScriptName() );
            assertNull( plist.checkInteraction( pre2 ) );
        } catch ( ParseException e ) {
            throw new RuntimeException();
        }

    }
}