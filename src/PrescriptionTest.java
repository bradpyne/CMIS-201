import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionTest {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void getScriptName() {
        try {
            Prescription pre = new Prescription("ibuprofen", df.parse("2025-03-01"), 1, "Dr. John Smith");

            assertEquals("ibuprofen", pre.getScriptName() );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getIssueDate() {
        try {
            Prescription pre = new Prescription("ibuprofen", df.parse("2025-03-01"), 1, "Dr. John Smith");

            assertEquals("2025-03-01", df.format( pre.getIssueDate() ) );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getDosage() {
        try {
            Prescription pre = new Prescription("ibuprofen", df.parse("2025-03-01"), 1, "Dr. John Smith");

            assertEquals(1, pre.getDosage() );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getPrescriber() {
        try {
            Prescription pre = new Prescription("ibuprofen", df.parse("2025-03-01"), 1, "Dr. John Smith");

            assertEquals("Dr. John Smith", pre.getPrescriber() );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void makePrescription() {
        try {
            Prescription test = makePrescription("Smith,Maria,1953-06-16,heptapone,2023-02-11,50,Agrawal");
        }
    }
}