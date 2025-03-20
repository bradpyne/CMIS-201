import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    private PatientIdentity patientID;
    private PrescriptionList prList;

    Patient(PatientIdentity patID) {
        patientID = patID;
        prList = new PrescriptionList();
    }

    public PatientIdentity getPatientIdentity() {
        return patientID;
    }

    public PrescriptionList getPrescriptionList() {
        return prList;
    }

    public String toCSV() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return ( patientID.getName().fullName() + ", " + df.format( patientID.getDOB() ) );
    }

    static Patient makePatient( String line ) {
        Patient newPat = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String[] tokens = line.split(",");

            newPat = new Patient(new PatientIdentity(new Name(tokens[1], tokens[0]), df.parse(tokens[2])));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newPat;
    }
}
