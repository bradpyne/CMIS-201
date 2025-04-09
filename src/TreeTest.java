import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {
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
            Patient test = patList.find( new PatientIdentity( new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10") ) );
            assertTrue( pat1.getPatientIdentity().match( patList.find( new PatientIdentity( new Name("Ernie", "Floyd"), dateFormatter.parse("1999-10-10") ) ).getPatientIdentity() ) );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
